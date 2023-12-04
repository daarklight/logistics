package com.tsystems.logistics.logistics_vp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.api.gax.rpc.ServerStream;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.google.maps.routing.v2.*;
import com.tsystems.logistics.logistics_vp.exceptions.custom.GoogleMapsServiceException;
import com.tsystems.logistics.logistics_vp.service.interfaces.GoogleMapsDistanceService;
import com.tsystems.logistics.logistics_vp.service.pojo.DistanceMatrixPojo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GoogleMapsDistanceServiceImpl implements GoogleMapsDistanceService {

    @Value("${google.api.key}")
    private String API_KEY;

    @Override
    public List<Integer> calculateRideDurationAndDistance(String startPoint, String endPoint) throws InterruptedException {

        try {
            List<Integer> durationAndDistance = new ArrayList<>();
            final Integer[] rideDurationInSeconds = new Integer[1];
            final Integer[] rideDistanceInMeters = new Integer[1];

            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(API_KEY)
                    .build();
            DistanceMatrixApiRequest request = new DistanceMatrixApiRequest(context)
                    .origins(startPoint)
                    .destinations(endPoint)
                    .mode(TravelMode.DRIVING)
                    .units(Unit.METRIC)
                    .avoid(DirectionsApi.RouteRestriction.FERRIES);

            final CountDownLatch latch = new CountDownLatch(1);
            request.setCallback(new PendingResult.Callback<DistanceMatrix>() {
                @Override
                public void onResult(DistanceMatrix result) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String resultJsonResponseAsString = gson.toJson(result);

                    JsonObject durationResult = JsonParser.parseString(resultJsonResponseAsString)
                            .getAsJsonObject()
                            .get("rows")
                            .getAsJsonArray()
                            .get(0)
                            .getAsJsonObject()
                            .get("elements")
                            .getAsJsonArray()
                            .get(0)
                            .getAsJsonObject()
                            .get("duration")
                            .getAsJsonObject();
                    rideDurationInSeconds[0] = Integer.parseInt(durationResult.get("inSeconds").toString());

                    JsonObject distanceResult = JsonParser.parseString(resultJsonResponseAsString)
                            .getAsJsonObject()
                            .get("rows")
                            .getAsJsonArray()
                            .get(0)
                            .getAsJsonObject()
                            .get("elements")
                            .getAsJsonArray()
                            .get(0)
                            .getAsJsonObject()
                            .get("distance")
                            .getAsJsonObject();
                    rideDistanceInMeters[0] = Integer.parseInt(distanceResult.get("inMeters").toString());

                    latch.countDown();
                }

                @Override
                public void onFailure(Throwable e) {
                    System.out.println("Exception thrown: " + e);
                    latch.countDown();
                }
            });

            latch.await();

            durationAndDistance.add(rideDurationInSeconds[0]);
            durationAndDistance.add(rideDistanceInMeters[0]);
            return durationAndDistance;
        } catch (InterruptedException e) {
            throw new GoogleMapsServiceException("Problems with Google Maps Service");
        }
    }

    private List<String> routeMatrix(String firstOriginAddress, String firstFinalAddress, String secondFinalAddress)
            throws IOException {
        try {
            List<String> routeMatrixResponseList = new ArrayList<>();
            RoutesSettings routesSettings = RoutesSettings
                    .newBuilder()
                    .setHeaderProvider(() -> {
                        Map headers = new HashMap<>();
                        headers.put("X-Goog-FieldMask", "originIndex,destinationIndex,distance_meters,duration");
                        return headers;
                    })
                    .build();
            RoutesClient routesClient = RoutesClient.create(routesSettings);

            ServerStreamingCallable computeRouteMatrix = routesClient.computeRouteMatrixCallable();
            ServerStream stream = computeRouteMatrix.call(
                    ComputeRouteMatrixRequest
                            .newBuilder()
                            .addOrigins(RouteMatrixOrigin.newBuilder()
                                    .setWaypoint(Waypoint.newBuilder()
                                            .setAddress(firstOriginAddress)
                                            .build()))
                            .addDestinations(RouteMatrixDestination.newBuilder()
                                    .setWaypoint(Waypoint.newBuilder()
                                            .setAddress(
                                                    firstFinalAddress)
                                            .build())
                                    .build())
                            .addDestinations(RouteMatrixDestination.newBuilder()
                                    .setWaypoint(Waypoint.newBuilder()
                                            .setAddress(
                                                    secondFinalAddress)
                                            .build())
                                    .build())
                            .setTravelMode(RouteTravelMode.DRIVE)
                            .build());

            for (Object element : stream) {
                routeMatrixResponseList.add(element.toString());
            }
            return routeMatrixResponseList;
        } catch (IOException e) {
            throw new GoogleMapsServiceException("Problems with Google Maps Service");
        }
    }

    @Override
    public List<List<Integer>> getRouteMatrixResults(String firstOriginAddress, String firstFinalAddress,
                                                     String secondFinalAddress)
            throws IOException {
        List<List<Integer>> routeMatrixResults = new ArrayList<>();
        List<String> routeMatrixResponseList = routeMatrix(firstOriginAddress, firstFinalAddress, secondFinalAddress);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        List<DistanceMatrixPojo> allDistancesMatrices = routeMatrixResponseList.stream().map(
                elem -> {
                    try {
                        return mapper.readValue(elem.replace("duration {", "duration: {"), DistanceMatrixPojo.class);
                    } catch (JsonProcessingException e) {
                        //throw new RuntimeException(e);
                        throw new GoogleMapsServiceException("Problems with Google Maps Service response");
                    }
                }).collect(
                Collectors.toList());
        List<Integer> destinationIndexes = allDistancesMatrices.stream().map(elem -> Integer.parseInt(
                elem.getDestinationIndex())).collect(Collectors.toList());
        List<Integer> distancesInMeters = allDistancesMatrices.stream().map(elem -> Integer.parseInt(
                elem.getDistanceMeters())).collect(Collectors.toList());
        List<Integer> durationsInSeconds = allDistancesMatrices.stream().map(elem -> Integer.parseInt(
                elem.getDuration().getSeconds())).collect(Collectors.toList());

        routeMatrixResults.add(destinationIndexes);
        routeMatrixResults.add(distancesInMeters);
        routeMatrixResults.add(durationsInSeconds);
        return routeMatrixResults;
    }
}

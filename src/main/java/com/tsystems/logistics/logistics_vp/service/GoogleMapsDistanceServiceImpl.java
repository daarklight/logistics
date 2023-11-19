package com.tsystems.logistics.logistics_vp.service;

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
import com.tsystems.logistics.logistics_vp.service.interfaces.GoogleMapsDistanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
@Transactional
@RequiredArgsConstructor
public class GoogleMapsDistanceServiceImpl implements GoogleMapsDistanceService {

    @Value("${google.api.key}")
    private String API_KEY;

    @Override
    public List<Integer> calculateRideDurationAndDistance(String startPoint, String endPoint) throws InterruptedException {

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
    }
}

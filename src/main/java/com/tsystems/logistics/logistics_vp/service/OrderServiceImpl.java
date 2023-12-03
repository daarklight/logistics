package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.Cargo;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import com.tsystems.logistics.logistics_vp.mapper.OrderMapper;
import com.tsystems.logistics.logistics_vp.repository.CargoRepository;
import com.tsystems.logistics.logistics_vp.repository.DriverRepository;
import com.tsystems.logistics.logistics_vp.repository.OrderRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.CargoService;
import com.tsystems.logistics.logistics_vp.service.interfaces.GoogleMapsDistanceService;
import com.tsystems.logistics.logistics_vp.service.interfaces.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final CargoRepository cargoRepository;
    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;
    private final CargoService cargoService;
    private final GoogleMapsDistanceService mapsService;

    @Override
    public List<OrderDto> ordersFindAll() {
        return orderRepository.findAll().stream().map(order -> orderDto(order)).toList();
    }

    @Override
    public OrderDto orderCreate(CreateOrderDto orderDto) {
        Order order = Order.builder()
                .orderCustomerId(orderDto.getOrderCustomerId())
                .category(orderDto.getCategory())
                .weight(orderDto.getWeight())
                .status(OrderStatus.NEW)
                .limitDateTime(orderDto.getLimitDateTime())
                .build();
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdate(Integer orderId, UpdateOrderDto orderDto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setOrderCustomerId(orderDto.getOrderCustomerId());
        order.setCategory(orderDto.getCategory());
        order.setWeight(orderDto.getWeight());
        order.setStatus(OrderStatus.valueOf(orderDto.getStatus().toString()));
        order.setLimitDateTime(orderDto.getLimitDateTime());
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public void orderDelete(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDto orderFindById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        return orderDto(order);
    }

    @Override
    public List<OrderDto> ordersFindByOrderCustomerId(Integer orderCustomerId) {
        return orderRepository.findAllByOrderCustomerId(orderCustomerId).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindByCategory(String category) {
        return orderRepository.findAllByCategory(category).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindByStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByStatus(orderStatus).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public OrderDto orderFindByAssignedTruckNumber(String truckNumber) {
        Order order = orderRepository.findOrderByAssignedTruckNumber(truckNumber);
        return orderDto(order);
    }

    @Override
    public List<OrderDto> ordersFindEarlierThanStartDateTime(LocalDateTime startDateTime) {
        return orderRepository.findAllByStartDateTimeLessThanEqual(startDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindLaterThanStartDateTime(LocalDateTime startDateTime) {
        return orderRepository.findAllByStartDateTimeGreaterThanEqual(startDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindEarlierThanLimitDateTime(LocalDateTime limitDateTime) {
        return orderRepository.findAllByLimitDateTimeLessThanEqual(limitDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindLaterThanLimitDateTime(LocalDateTime limitDateTime) {
        return orderRepository.findAllByLimitDateTimeGreaterThanEqual(limitDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public OrderDto orderUpdateStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdateStartDateTime(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStartDateTime(LocalDateTime.now());
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdateDriverComment(Integer orderId, UpdateOrderDriverCommentDto orderDto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setDriverComment(orderDto.getDriverComment());
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdateAssignedTruckNumber(Integer orderId, UpdateOrderAssignedTruckNumberDto orderDto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setAssignedTruckNumber(orderDto.getAssignedTruckNumber());
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderFindByDriver(Integer personalNumber) {
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        Order order = driver.getCurrentOrderId();
        return orderDto(order);
    }

    @Override
    public String calculateAndUpdateRide(Integer orderId) throws InterruptedException, IOException {
        List<CargoDto> allCargoDtos = cargoService.cargosFindByOrderForCargoId(orderId);
        String resultResponse = null;
        if (allCargoDtos.size() == 1) {
            CargoDto cargoDto = allCargoDtos.get(0);
            Integer cargoId = cargoDto.getCargoId();
            String startPoint = cargoDto.getStartAddress() + ", " + cargoDto.getStartCity() + ", " + cargoDto.getStartState();
            String endPoint = cargoDto.getFinalAddress() + ", " + cargoDto.getFinalCity() + ", " + cargoDto.getFinalState();
            List<Integer> rideAndDistance = mapsService.calculateRideDurationAndDistance(startPoint, endPoint);
            int durationInMinutes = rideAndDistance.get(0) / 60;
            int distanceInKm = rideAndDistance.get(1) / 1000;
            resultResponse = "Duration, minutes: " + durationInMinutes + "; Distance, km: " + distanceInKm;
            // Save in DB:
            Cargo cargo = cargoRepository.findById(cargoId).orElseThrow();
            cargo.setWaypointIndex(0);
            cargo.setRideDistanceFromStartPoint(distanceInKm);
            cargo.setRideDistanceFromPreviousPoint(distanceInKm);
            cargo.setRideDurationFromStartPoint(durationInMinutes);
            cargo.setRideDurationFromPreviousPoint(durationInMinutes);
            cargoRepository.save(cargo);
        } else if (allCargoDtos.size() == 2) {
            CargoDto firstCargoDto = allCargoDtos.get(0);
            CargoDto secondCargoDto = allCargoDtos.get(1);
            Integer firstCargoId = firstCargoDto.getCargoId();
            Integer secondCargoId = secondCargoDto.getCargoId();
            String startPoint = firstCargoDto.getStartAddress() + ", " + firstCargoDto.getStartCity() + ", " +
                    firstCargoDto.getStartState();
            String firstEndPoint = firstCargoDto.getFinalAddress() + ", " + firstCargoDto.getFinalCity() + ", " +
                    firstCargoDto.getFinalState();
            String secondEndPoint = secondCargoDto.getFinalAddress() + ", " + secondCargoDto.getFinalCity() + ", " +
                    secondCargoDto.getFinalState();
            List<List<Integer>> rideAndDistance = mapsService.getRouteMatrixResults(startPoint, firstEndPoint, secondEndPoint);
            int firstWaypointIndex = rideAndDistance.get(0).get(0);
            int secondWaypointIndex = rideAndDistance.get(0).get(1);
            int firstDistanceInKmFromStartPoint = rideAndDistance.get(1).get(0) / 1000;
            int secondDistanceInKmFromStartPoint = rideAndDistance.get(1).get(1) / 1000;
            int firstDurationInMinutesFromStartPoint = rideAndDistance.get(2).get(0) / 60;
            int secondDurationInMinutesFromStartPoint = rideAndDistance.get(2).get(1) / 60;
            resultResponse = "For first cargo: " + "Waypoint index: " + firstWaypointIndex + "; Duration, minutes: " +
                    firstDurationInMinutesFromStartPoint + "; Distance, km: " + firstDistanceInKmFromStartPoint + "\n" +
                    "For second cargo: " + "Waypoint index: " + secondWaypointIndex + "; Duration, minutes: " +
                    secondDurationInMinutesFromStartPoint + "; Distance, km: " + secondDistanceInKmFromStartPoint;
            int rideDistanceFromPreviousPointForFirstCargo = (firstWaypointIndex == 0)
                    ? firstDistanceInKmFromStartPoint : Math.abs(secondDistanceInKmFromStartPoint - firstDistanceInKmFromStartPoint);
            int rideDurationFromPreviousPointForFirstCargo = (firstWaypointIndex == 0)
                    ? firstDurationInMinutesFromStartPoint : Math.abs(secondDurationInMinutesFromStartPoint -
                    firstDurationInMinutesFromStartPoint);
            int rideDistanceFromPreviousPointForSecondCargo = (secondWaypointIndex == 0)
                    ? secondDistanceInKmFromStartPoint : Math.abs(secondDistanceInKmFromStartPoint - firstDistanceInKmFromStartPoint);
            int rideDurationFromPreviousPointForSecondCargo = (secondWaypointIndex == 0)
                    ? secondDurationInMinutesFromStartPoint : Math.abs(secondDurationInMinutesFromStartPoint -
                    firstDurationInMinutesFromStartPoint);
            // Save in DB:
            Cargo firstCargo = cargoRepository.findById(firstCargoId).orElseThrow();
            firstCargo.setWaypointIndex(firstWaypointIndex);
            firstCargo.setRideDistanceFromStartPoint(firstDistanceInKmFromStartPoint);
            firstCargo.setRideDistanceFromPreviousPoint(rideDistanceFromPreviousPointForFirstCargo);
            firstCargo.setRideDurationFromStartPoint(firstDurationInMinutesFromStartPoint);
            firstCargo.setRideDurationFromPreviousPoint(rideDurationFromPreviousPointForFirstCargo);
            cargoRepository.save(firstCargo);
            Cargo secondCargo = cargoRepository.findById(secondCargoId).orElseThrow();
            secondCargo.setWaypointIndex(secondWaypointIndex);
            secondCargo.setRideDistanceFromStartPoint(secondDistanceInKmFromStartPoint);
            secondCargo.setRideDistanceFromPreviousPoint(rideDistanceFromPreviousPointForSecondCargo);
            secondCargo.setRideDurationFromStartPoint(secondDurationInMinutesFromStartPoint * 2);
            secondCargo.setRideDurationFromPreviousPoint(rideDurationFromPreviousPointForSecondCargo);
            cargoRepository.save(secondCargo);
        }
        log.info("Results of calculations: " + resultResponse);
        return resultResponse;
    }

    private OrderDto orderDto(Order order) {
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }
}

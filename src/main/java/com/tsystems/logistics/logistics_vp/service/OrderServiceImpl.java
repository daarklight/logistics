package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.Cargo;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.entity.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import com.tsystems.logistics.logistics_vp.exceptions.custom.ImpossibleCargoUpdateException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.ImpossibleOrderUpdateException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NoSuchOrderException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NonProperTruckCapacityException;
import com.tsystems.logistics.logistics_vp.mapper.OrderMapper;
import com.tsystems.logistics.logistics_vp.repository.CargoRepository;
import com.tsystems.logistics.logistics_vp.repository.DriverRepository;
import com.tsystems.logistics.logistics_vp.repository.OrderRepository;
import com.tsystems.logistics.logistics_vp.repository.TruckRepository;
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
    private final TruckRepository truckRepository;
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
                .weight(0)
                .status(OrderStatus.NEW)
                .limitDateTime(orderDto.getLimitDateTime())
                .build();
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdate(Integer orderId, UpdateOrderDto orderDto) {
        Order order = getOrderFromDb(orderId);
        OrderStatus orderStatus = order.getStatus();
        if (orderStatus == OrderStatus.NEW || orderStatus == OrderStatus.DECLINED_BY_DRIVERS) {
            order.setOrderCustomerId(orderDto.getOrderCustomerId());
            order.setCategory(orderDto.getCategory());
            //order.setWeight(orderDto.getWeight());
            //order.setStatus(OrderStatus.valueOf(orderDto.getStatus().toString()));
            order.setLimitDateTime(orderDto.getLimitDateTime());
            orderRepository.save(order);
            return orderDto(order);
        } else {
            throw new ImpossibleOrderUpdateException("It is impossible to update the order when status is not equal " +
                    "to NEW or DECLINED_BY_DRIVERS");
        }
    }

    @Override
    public void orderDelete(Integer orderId) {
        getOrderFromDb(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDto orderFindById(Integer orderId) {
        return orderDto(getOrderFromDb(orderId));
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
        Order order = getOrderFromDb(orderId);
        order.setStatus(OrderStatus.valueOf(status));
//        if(status.equals(OrderStatus.EXPECT_DRIVERS_CONFIRMATION)){
//
//        }
        //orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdateStartDateTime(Integer orderId) {
        Order order = getOrderFromDb(orderId);
        order.setStartDateTime(LocalDateTime.now());
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdateDriverComment(Integer orderId, UpdateOrderDriverCommentDto orderDto) {
        Order order = getOrderFromDb(orderId);
        order.setDriverComment(orderDto.getDriverComment());
        orderRepository.save(order);
        return orderDto(order);
    }

    @Override
    public OrderDto orderUpdateAssignedTruckNumber(Integer orderId, String truckNumber) {
        Order order = getOrderFromDb(orderId);
        Truck truck = truckRepository.findById(truckNumber).orElseThrow();
        if (order.getWeight() <= truck.getCapacity()) {
            order.setAssignedTruckNumber(truckNumber);
            truck.setBusy(Busy.YES);
            return orderDto(order);
        } else {
            throw new NonProperTruckCapacityException("The order is too heavy for this truck");
        }
    }

    @Override
    public OrderDto orderFindByDriver(Integer personalNumber) {
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        Order order = driver.getCurrentOrderId();
        return orderDto(order);
    }

//    private Order getOrderByCargoId(Integer cargoId){
//        orderRepository.findAll().stream().filter(elem -> elem.getCargos().)
//
//    }

    @Override
    public Order getOrderFromDb(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new NoSuchOrderException("This order does not exist in database"));
    }

    private OrderDto orderDto(Order order) {
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }
}

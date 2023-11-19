package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import com.tsystems.logistics.logistics_vp.mapper.OrderMapper;
import com.tsystems.logistics.logistics_vp.repository.OrderRepository;
import com.tsystems.logistics.logistics_vp.repository.customized.CustomizedOrderRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomizedOrderRepository customizedOrderRepository;

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
        return customizedOrderRepository.findAllByOrderCustomerId(orderCustomerId).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindByCategory(String category) {
        return customizedOrderRepository.findAllByCategory(category).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindByStatus(OrderStatus orderStatus) {
        return customizedOrderRepository.findAllByStatus(orderStatus).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public OrderDto orderFindByAssignedTruckNumber(String truckNumber) {
        Order order = customizedOrderRepository.findOrderByAssignedTruckNumber(truckNumber);
        return orderDto(order);
    }

    @Override
    public List<OrderDto> ordersFindEarlierThanStartDateTime(LocalDateTime startDateTime) {
        return customizedOrderRepository.findAllByStartDateTimeLessThanEqual(startDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindLaterThanStartDateTime(LocalDateTime startDateTime) {
        return customizedOrderRepository.findAllByStartDateTimeGreaterThanEqual(startDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindEarlierThanLimitDateTime(LocalDateTime limitDateTime) {
        return customizedOrderRepository.findAllByLimitDateTimeLessThanEqual(limitDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public List<OrderDto> ordersFindLaterThanLimitDateTime(LocalDateTime limitDateTime) {
        return customizedOrderRepository.findAllByLimitDateTimeGreaterThanEqual(limitDateTime).stream().map(
                order -> orderDto(order)).toList();
    }

    @Override
    public OrderDto orderUpdateStatus(Integer orderId, UpdateOrderStatusDto orderDto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.valueOf(orderDto.getStatus().toString()));
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

    private OrderDto orderDto(Order order) {
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }
}

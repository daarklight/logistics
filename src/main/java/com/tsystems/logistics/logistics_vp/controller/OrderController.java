package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.OrdersApi;
import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import com.tsystems.logistics.logistics_vp.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class OrderController implements OrdersApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderDto> orderCreate(CreateOrderDto createOrderDto) {
        OrderDto orderDto = orderService.orderCreate(createOrderDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderDto);
    }

    @Override
    public ResponseEntity<Void> orderDelete(Integer orderId) {
        orderService.orderDelete(orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<OrderDto> orderFindByAssignedTruckNumber(String assignedTruckNumber) {
        OrderDto orderDto = orderService.orderFindByAssignedTruckNumber(assignedTruckNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDto);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByCategory(String category) {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindByCategory(category);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByEarlierThanLimitDateTime(LocalDateTime limitDateTime) {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindEarlierThanLimitDateTime(limitDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByEarlierThanStartDateTime(LocalDateTime startDateTime) {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindEarlierThanStartDateTime(startDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<OrderDto> orderFindById(Integer orderId) {
        OrderDto resultOrderDto = orderService.orderFindById(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByLaterThanLimitDateTime(LocalDateTime limitDateTime) {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindLaterThanLimitDateTime(limitDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByLaterThanStartDateTime(LocalDateTime startDateTime) {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindLaterThanStartDateTime(startDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByOrderCustomerId(Integer orderCustomerId) {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindByOrderCustomerId(orderCustomerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByStatus(String status) {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindByStatus(OrderStatus.valueOf(status));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdate(Integer orderId, UpdateOrderDto updateOrderDto) {
        OrderDto resultOrderDto = orderService.orderUpdate(orderId, updateOrderDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateAssignedTruckNumber(Integer orderId, UpdateOrderAssignedTruckNumberDto updateOrderDto) {
        OrderDto resultOrderDto = orderService.orderUpdateAssignedTruckNumber(orderId, updateOrderDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateDriverComment(Integer orderId, UpdateOrderDriverCommentDto updateOrderDriverCommentDto) {
        OrderDto resultOrderDto = orderService.orderUpdateDriverComment(orderId, updateOrderDriverCommentDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateStartDateTime(Integer orderId) {
        OrderDto resultOrderDto = orderService.orderUpdateStartDateTime(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateStatus(Integer orderId, UpdateOrderStatusDto updateOrderStatusDto) {
        OrderDto resultOrderDto = orderService.orderUpdateStatus(orderId, updateOrderStatusDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<List<OrderDto>> ordersFindAll() {
        List<OrderDto> allResultOrderDtos = orderService.ordersFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }
}

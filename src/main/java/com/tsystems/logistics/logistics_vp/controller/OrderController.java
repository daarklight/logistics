package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.OrdersApi;
import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import com.tsystems.logistics.logistics_vp.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class OrderController implements OrdersApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderDto> orderCreate(CreateOrderDto createOrderDto) {
        log.info("Start to register new order");
        OrderDto orderDto = orderService.orderCreate(createOrderDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderDto);
    }

    @Override
    public ResponseEntity<Void> orderDelete(Integer orderId) {
        log.info("Start to delete order");
        orderService.orderDelete(orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<OrderDto> orderFindByAssignedTruckNumber(String assignedTruckNumber) {
        log.info("Start to find order by assigned truck number");
        OrderDto orderDto = orderService.orderFindByAssignedTruckNumber(assignedTruckNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDto);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByCategory(String category) {
        log.info("Start to filter orders by category");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindByCategory(category);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByEarlierThanLimitDateTime(LocalDateTime limitDateTime) {
        log.info("Start to filter orders earlier than limit date and time");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindEarlierThanLimitDateTime(limitDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByEarlierThanStartDateTime(LocalDateTime startDateTime) {
        log.info("Start to filter orders earlier than start date and time");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindEarlierThanStartDateTime(startDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<OrderDto> orderFindById(Integer orderId) {
        log.info("Start to find order by id");
        OrderDto resultOrderDto = orderService.orderFindById(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByLaterThanLimitDateTime(LocalDateTime limitDateTime) {
        log.info("Start to filter orders after limit date and time");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindLaterThanLimitDateTime(limitDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByLaterThanStartDateTime(LocalDateTime startDateTime) {
        log.info("Start to filter orders after start date and time");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindLaterThanStartDateTime(startDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByOrderCustomerId(Integer orderCustomerId) {
        log.info("Start to filter orders by customer id");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindByOrderCustomerId(orderCustomerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByStatus(String status) {
        log.info("Start to filter orders aby status");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindByStatus(OrderStatus.valueOf(status));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdate(Integer orderId, UpdateOrderDto updateOrderDto) {
        log.info("Start to update order");
        OrderDto resultOrderDto = orderService.orderUpdate(orderId, updateOrderDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateAssignedTruckNumber(Integer orderId, String truckNumber) {
        log.info("Start to update assigned truck for order");
        OrderDto resultOrderDto = orderService.orderUpdateAssignedTruckNumber(orderId, truckNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateDriverComment(Integer orderId, UpdateOrderDriverCommentDto updateOrderDriverCommentDto) {
        log.info("Start to update driver's comment");
        OrderDto resultOrderDto = orderService.orderUpdateDriverComment(orderId, updateOrderDriverCommentDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateStartDateTime(Integer orderId) {
        log.info("Start to update start date and time for order");
        OrderDto resultOrderDto = orderService.orderUpdateStartDateTime(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdateStatus(Integer orderId, String status) {
        log.info("Start to update order status");
        OrderDto resultOrderDto = orderService.orderUpdateStatus(orderId, status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

//    @Override
//    public ResponseEntity<OrderDto> orderUpdateStatus(Integer orderId, UpdateOrderStatusDto updateOrderStatusDto) {
//        log.info("Start to update order status");
//        OrderDto resultOrderDto = orderService.orderUpdateStatus(orderId, updateOrderStatusDto);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(resultOrderDto);
//    }

    @Override
    public ResponseEntity<List<OrderDto>> ordersFindAll() {
        log.info("Start to find all orders");
        List<OrderDto> allResultOrderDtos = orderService.ordersFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultOrderDtos);
    }

    @Override
    public ResponseEntity<OrderDto> orderFindByDriver(Integer personalNumber) {
        log.info("Start to find order by driver");
        OrderDto resultOrderDto = orderService.orderFindByDriver(personalNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultOrderDto);
    }

//    @Override
//    public ResponseEntity<String> orderCalculateRide(Integer orderId) {
//        log.info("Start to calculate and update ride for cargos of defined orders");
//        try {
//            orderService.calculateAndUpdateRide(orderId);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
}

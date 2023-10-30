package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.OrdersApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateOrderDto;
import com.tsystems.logistics.logistics_vp.code.model.OrderDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateOrderDto;
import com.tsystems.logistics.logistics_vp.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrdersApi {

    @Override
    public ResponseEntity<OrderDto> orderCreate(CreateOrderDto createOrderDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> orderDelete(Integer orderId) {
        return null;
    }

    @Override
    public ResponseEntity<OrderDto> orderFindByAssignedTruckNumber(String assignedTruckNumber) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByCategory(String fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByEarlierThanLimitDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByEarlierThanStartDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<OrderDto> orderFindById(Integer orderId) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByLaterThanLimitDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByLaterThanStartDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByOrderCustomerId(Integer orderCustomerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> orderFindByStatus(String fields) {
        return null;
    }

    @Override
    public ResponseEntity<OrderDto> orderUpdate(Integer orderId, UpdateOrderDto updateOrderDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderDto>> ordersFindAll() {
        return null;
    }
}

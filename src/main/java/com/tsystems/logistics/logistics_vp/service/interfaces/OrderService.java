package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<OrderDto> ordersFindAll();
    OrderDto orderCreate(CreateOrderDto orderDto);
    OrderDto orderUpdate(Integer orderId, UpdateOrderDto orderDto);
    void orderDelete(Integer orderId);
    OrderDto orderFindById(Integer orderId);
    List<OrderDto> ordersFindByOrderCustomerId(Integer orderCustomerId);
    List<OrderDto> ordersFindByCategory(String category);
    List<OrderDto> ordersFindByStatus(OrderStatus orderStatus);
    OrderDto orderFindByAssignedTruckNumber(String truckNumber);
    List<OrderDto> ordersFindEarlierThanStartDateTime(LocalDateTime startDateTime);
    List<OrderDto> ordersFindLaterThanStartDateTime(LocalDateTime startDateTime);
    List<OrderDto> ordersFindEarlierThanLimitDateTime(LocalDateTime limitDateTime);
    List<OrderDto> ordersFindLaterThanLimitDateTime(LocalDateTime limitDateTime);
    OrderDto orderUpdateStatus(Integer orderId, String status);
    OrderDto orderUpdateStartDateTime(Integer orderId);
    OrderDto orderUpdateDriverComment(Integer orderId, UpdateOrderDriverCommentDto orderDto);
    OrderDto orderUpdateAssignedTruckNumber(Integer orderId, String truckNumber);
    OrderDto orderFindByDriver(Integer personalNumber);
    Order getOrderFromDb(Integer orderId);
    OrderDto orderUnassignTruck(Integer orderId);
}

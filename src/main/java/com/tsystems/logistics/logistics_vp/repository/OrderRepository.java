package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByOrderCustomerId(Integer orderCustomerId);
    List<Order> findAllByCategory(String category);
    List<Order> findAllByStatus(OrderStatus orderStatus);
    Order findOrderByAssignedTruckNumber(String truckNumber);
    List<Order> findAllByStartDateTimeLessThanEqual(LocalDateTime startDateTime);
    List<Order> findAllByStartDateTimeGreaterThanEqual(LocalDateTime startDateTime);
    List<Order> findAllByLimitDateTimeLessThanEqual(LocalDateTime limitDateTime);
    List<Order> findAllByLimitDateTimeGreaterThanEqual(LocalDateTime limitDateTime);

    //
    //    @Override
    //    public ResponseEntity<List<OrderDto>> orderFindByDriver(Integer personalNumber) {
    //        log.info("Start to find order by driver");
    //        return null;
    //    }
}

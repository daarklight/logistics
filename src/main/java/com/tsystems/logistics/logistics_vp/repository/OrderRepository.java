package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

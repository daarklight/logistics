package com.tsystems.logistics.logistics_vp.repositories;

import com.tsystems.logistics.logistics_vp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

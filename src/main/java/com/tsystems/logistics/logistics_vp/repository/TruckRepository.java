package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, String> {
}

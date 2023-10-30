package com.tsystems.logistics.logistics_vp.repositories;

import com.tsystems.logistics.logistics_vp.entities.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, String> {
}

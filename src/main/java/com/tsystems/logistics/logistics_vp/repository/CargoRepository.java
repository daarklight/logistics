package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository <Cargo, Integer> {
}

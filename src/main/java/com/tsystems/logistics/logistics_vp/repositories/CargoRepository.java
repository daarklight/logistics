package com.tsystems.logistics.logistics_vp.repositories;

import com.tsystems.logistics.logistics_vp.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository <Cargo, Integer> {
}

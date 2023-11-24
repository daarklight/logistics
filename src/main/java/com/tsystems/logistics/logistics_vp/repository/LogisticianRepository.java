package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.Logistician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogisticianRepository extends JpaRepository<Logistician, Integer> {
    List<Logistician> findAllByNameAndSurname(String name, String surname);
}

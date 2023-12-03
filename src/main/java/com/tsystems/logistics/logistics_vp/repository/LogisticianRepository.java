package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entity.Logistician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogisticianRepository extends JpaRepository<Logistician, Integer> {
    List<Logistician> findAllByNameAndSurname(String name, String surname);
}

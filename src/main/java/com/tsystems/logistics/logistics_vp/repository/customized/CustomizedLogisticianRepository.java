package com.tsystems.logistics.logistics_vp.repository.customized;

import com.tsystems.logistics.logistics_vp.entity.Logistician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomizedLogisticianRepository extends JpaRepository <Logistician, Integer> {
    List<Logistician> findAllByNameAndSurname(String name, String surname);
}

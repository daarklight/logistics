package com.tsystems.logistics.logistics_vp.repository.customized;

import com.tsystems.logistics.logistics_vp.entity.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomizedTruckRepository extends JpaRepository<Truck, String> {
    List<Truck> findAllByCurrentCityAndCurrentState (String city, String state);
    List<Truck> findAllByBusy(Busy busy);
    List<Truck> findAllByTechnicalCondition(TechnicalCondition technicalCondition);
    List<Truck> findAllByCurrentCityAndCurrentStateAndCapacityGreaterThanEqual(String city, String state, Double capacity);
}

package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.entity.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository <Driver, Integer> {
    List<Driver> findAllByNameAndSurname(String name, String surname);
    List<Driver> findAllByBusy(Busy busy);
    List<Driver> findAllByCurrentCityAndCurrentState(String city, String state);
    List<Driver> findAllByCurrentTruckNumber(Truck truck);
    List<Driver> findAllByCurrentOrderId(Order currentOrderId);
    List<Driver> findAllByCurrentCityAndCurrentStateAndWorkingHoursInCurrentMonthLessThan(
            String city, String state, Integer workingHoursInCurrentMonth);
}

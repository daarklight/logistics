package com.tsystems.logistics.logistics_vp.repositories.customized;

import com.tsystems.logistics.logistics_vp.entities.Driver;
import com.tsystems.logistics.logistics_vp.entities.Order;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomizedDriverRepository extends JpaRepository <Driver, Integer> {
    List<Driver> findAllByNameAndSurname(String name, String surname);
    List<Driver> findAllByBusy(Busy busy);
    List<Driver> findAllByCurrentCityAndCurrentState(String city, String state);
    List<Driver> findAllByCurrentTruckNumber(Truck truck);
    List<Driver> findAllByCurrentOrderId(Order currentOrderId);
    List<Driver> findAllByCurrentCityAndCurrentStateAndWorkingHoursInCurrentMonthLessThan(
            String city, String state, Integer workingHoursInCurrentMonth);
}

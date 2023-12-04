package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.OrderAcceptance;

import java.util.List;

public interface DriverService {
    List<DriverDto> driversFindAll();
    DriverDto driverCreate(CreateDriverDto driverDto);
    DriverDto driverUpdateByLogistician(Integer personalNumber, UpdateDriverByLogisticianDto driverDto);
    DriverDto driverUpdateStatusByDriver(Integer personalNumber, UpdateDriverStatusByDriverDto driverDto);
    void driverDelete(Integer personalNumber);
    DriverDto driverFindByNumber(Integer personalNumber);
    List<DriverDto> driverFindByNameAndSurname(String name, String surname);
    List<DriverDto> driversFindByBusyStatus(Busy busy);
    List<DriverDto> driversFindAllByCurrentCityAndCurrentState(String city, String state);
    List<DriverDto> driversFindAllByCurrentTruckNumber(String truckNumber);
    List<DriverDto> driversFindAllByCurrentOrderId(Integer currentOrderId);
    DriverDto driverOrderAcceptance(Integer personalNumber, OrderAcceptance orderAcceptance);
    List<DriverDto> driversFindAllForOrder(Integer orderId, String city, String state, Integer hours);
    DriverDto driverFindByUsername(String username);
    DriverDto driverFindCodriver(Integer currentOrderId, Integer personalNumber);
    DriverDto driverUpdateCurrentOrder(Integer currentOrderId, Integer personalNumber);
    Driver getDriverFromDb(Integer personalNumber);
}

package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.CreateTruckDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByLogisticianDto;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;

import java.util.List;

public interface TruckService {
    void truckDelete(String number);
    TruckDto truckFindByNumber(String number);
    TruckDto truckCreate(CreateTruckDto truckDto);
    TruckDto truckUpdateByLogistician(String number, UpdateTruckByLogisticianDto truckDto);
    TruckDto truckUpdateByLogisticianUi(String number, TruckDto truckDto);
    TruckDto truckUpdateByDriver(String number, UpdateTruckByDriverDto truckDto);
    List<TruckDto> trucksFindAll();
    List<TruckDto> trucksFindAllByCurrentCityAndState(String city, String state);
    List<TruckDto> trucksFindByBusyStatus(Busy busy);
    List<TruckDto> trucksFindAllByTechnicalCondition(TechnicalCondition technicalCondition);
    List<TruckDto> findAllForOrder(Integer orderId, String city, String state, Double capacity);
}

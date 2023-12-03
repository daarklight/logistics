package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface CargoService {
    List<CargoDto> cargosFindAll();
    CargoDto cargoCreate(CreateCargoDto cargoDto);
    CargoDto cargoUpdateByLogistician(Integer cargoId, UpdateCargoByLogisticianDto cargoDto);
    CargoDto cargoUpdateByDriver(Integer cargoId, UpdateCargoByDriverDto cargoDto);
    void cargoDelete(Integer cargoId);
    CargoDto cargoFindById(Integer cargoId);
    List<CargoDto> cargosFindByOrderForCargoId(Integer orderId);
    List<CargoDto> cargosFindByName(String name);
    List<CargoDto> cargosFindAllByStartCityAndState(String city, String state);
    List<CargoDto> cargosFindAllByStartCityAndStateAndAddress(String city, String state, String address);
    List<CargoDto> cargosFindNonLoaded();
    List<CargoDto> cargosFindLoaded();
    List<CargoDto> cargosFindAllByFinalCityAndState(String city, String state);
    List<CargoDto> cargosFindAllByFinalCityAndStateAndAddress(String city, String state, String address);
    List<CargoDto> cargosFindNonUnloaded();
    List<CargoDto> cargosFindUnloaded();
    List<CargoDto> cargosFindEarlierThanExpectedCompletionDateTime(LocalDateTime expectedCompletionDateTime);
    List<CargoDto> cargosFindLaterThanExpectedCompletionDateTime(LocalDateTime expectedCompletionDateTime);
    List<CargoDto> cargosFindEarlierThanRealCompletionDateTime(LocalDateTime realCompletionDateTime);
    List<CargoDto> cargosFindLaterThanRealCompletionDateTime(LocalDateTime realCompletionDateTime);
    CargoDto cargoUpdateLoading(Integer cargoId);
    CargoDto cargoUpdateUnloading(Integer cargoId);
}

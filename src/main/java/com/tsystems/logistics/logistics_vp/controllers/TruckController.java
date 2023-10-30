package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.TrucksApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateTruckDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByLogisticianDto;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import com.tsystems.logistics.logistics_vp.services.interfaces.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TruckController implements TrucksApi {

    private final TruckService truckService;

    @Override
    public ResponseEntity<TruckDto> truckCreate(CreateTruckDto createTruckDto) {
        TruckDto truckDto = truckService.truckCreate(createTruckDto);
        return ResponseEntity
                .status(201)
                .body(truckDto);
    }

    @Override
    public ResponseEntity<Void> truckDelete(String number) {
        truckService.truckDelete(number);
        return ResponseEntity
                .status(204)
                .build();
    }

    @Override
    public ResponseEntity<TruckDto> truckFindByNumber(String number) {
        TruckDto resultTruckDto = truckService.truckFindByNumber(number);
        return ResponseEntity
                .ok(resultTruckDto);
    }

    @Override
    public ResponseEntity<TruckDto> truckUpdateByLogistician(String number, UpdateTruckByLogisticianDto updateTruckDto) {
        TruckDto truckDto = truckService.truckUpdateByLogistician(number, updateTruckDto);
        return ResponseEntity
                .status(200)
                .body(truckDto);
    }

    @Override
    public ResponseEntity<TruckDto> truckUpdateByDriver(String number, UpdateTruckByDriverDto updateTruckDto) {
        TruckDto truckDto = truckService.truckUpdateByDriver(number, updateTruckDto);
        return ResponseEntity
                .status(200)
                .body(truckDto);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindAll() {
        List<TruckDto> allTrucks = truckService.trucksFindAll();
        return ResponseEntity
                .ok(allTrucks);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByCurrentCityAndState(String city, String state) {
        List<TruckDto> allTrucks = truckService.trucksFindAllByCurrentCityAndCurrentState(city, state);
        return ResponseEntity
                .ok(allTrucks);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByBusyStatus(String busy) {
        List<TruckDto> allTrucks = truckService.trucksFindByBusyStatus(Busy.valueOf(busy));
        return ResponseEntity
                .ok(allTrucks);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByTechnicalCondition(String technicalConditionStatus) {
        List<TruckDto> allTrucks = truckService.trucksFindAllByTechnicalCondition(TechnicalCondition.valueOf(technicalConditionStatus));
        return ResponseEntity
                .ok(allTrucks);
    }

//    public ResponseEntity<TruckDto> truckUpdate(String number, UpdateTruckByLogisticianDto updateTruckDto) {
//        return null;
//    }
}

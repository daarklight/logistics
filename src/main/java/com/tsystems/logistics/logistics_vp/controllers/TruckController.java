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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TruckController implements TrucksApi {

    private final TruckService truckService;

    @Override
    public ResponseEntity<TruckDto> truckCreate(CreateTruckDto createTruckDto) {
        TruckDto resultTruckDto = truckService.truckCreate(createTruckDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultTruckDto);
    }

    @Override
    public ResponseEntity<Void> truckDelete(String number) {
        truckService.truckDelete(number);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
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
        TruckDto resultTruckDto = truckService.truckUpdateByLogistician(number, updateTruckDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultTruckDto);
    }

    @Override
    public ResponseEntity<TruckDto> truckUpdateByDriver(String number, UpdateTruckByDriverDto updateTruckDto) {
        TruckDto resultTruckDto = truckService.truckUpdateByDriver(number, updateTruckDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultTruckDto);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindAll() {
        List<TruckDto> allResultTruckDtos = truckService.trucksFindAll();
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByCurrentCityAndState(String city, String state) {
        List<TruckDto> allResultTruckDtos = truckService.trucksFindAllByCurrentCityAndState(city, state);
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindForOrder(Integer orderId, String city, String state, Double capacity) {
        List<TruckDto> allResultTruckDtos = truckService.findAllForOrder(orderId, city, state, capacity);
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByBusyStatus(String busy) {
        List<TruckDto> allResultTruckDtos = truckService.trucksFindByBusyStatus(Busy.valueOf(busy));
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByTechnicalCondition(String technicalConditionStatus) {
        List<TruckDto> allResultTruckDtos = truckService.trucksFindAllByTechnicalCondition(
                TechnicalCondition.valueOf(technicalConditionStatus));
        return ResponseEntity
                .ok(allResultTruckDtos);
    }
}

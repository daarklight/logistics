package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.TrucksApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateTruckDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByLogisticianDto;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import com.tsystems.logistics.logistics_vp.service.interfaces.TruckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class TruckController implements TrucksApi {

    private final TruckService truckService;

    @Override
    public ResponseEntity<TruckDto> truckCreate(CreateTruckDto createTruckDto) {
        log.info("Start to register new truck");
        TruckDto resultTruckDto = truckService.truckCreate(createTruckDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultTruckDto);
    }

    @Override
    public ResponseEntity<Void> truckDelete(String number) {
        log.info("Start to delete truck");
        truckService.truckDelete(number);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<TruckDto> truckFindByNumber(String number) {
        log.info("Start to find truck by number");
        TruckDto resultTruckDto = truckService.truckFindByNumber(number);
        return ResponseEntity
                .ok(resultTruckDto);
    }

    @Override
    public ResponseEntity<TruckDto> truckUpdateByLogistician(String number, UpdateTruckByLogisticianDto updateTruckDto) {
        log.info("Start to update truck byu logistician");
        TruckDto resultTruckDto = truckService.truckUpdateByLogistician(number, updateTruckDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultTruckDto);
    }

    @Override
    public ResponseEntity<TruckDto> truckUpdateByDriver(String number, UpdateTruckByDriverDto updateTruckDto) {
        log.info("Start to update truck by driver");
        TruckDto resultTruckDto = truckService.truckUpdateByDriver(number, updateTruckDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultTruckDto);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindAll() {
        log.info("Start to find all trucks");
        List<TruckDto> allResultTruckDtos = truckService.trucksFindAll();
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByCurrentCityAndState(String city, String state) {
        log.info("Start to filter trucks by current city and state");
        List<TruckDto> allResultTruckDtos = truckService.trucksFindAllByCurrentCityAndState(city, state);
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindForOrder(Integer orderId, String city, String state, Double capacity) {
        log.info("Start to find proper trucks for order");
        List<TruckDto> allResultTruckDtos = truckService.findAllForOrder(orderId, city, state, capacity);
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByBusyStatus(String busy) {
        log.info("Start to filter trucks by busy status");
        List<TruckDto> allResultTruckDtos = truckService.trucksFindByBusyStatus(Busy.valueOf(busy));
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<List<TruckDto>> trucksFindByTechnicalCondition(String technicalConditionStatus) {
        log.info("Start to filter trucks by technical condition");
        List<TruckDto> allResultTruckDtos = truckService.trucksFindAllByTechnicalCondition(
                TechnicalCondition.valueOf(technicalConditionStatus));
        return ResponseEntity
                .ok(allResultTruckDtos);
    }

    @Override
    public ResponseEntity<TruckDto> truckFindByDriver(Integer personalNumber) {
        log.info("Start to find truck by driver");
        TruckDto resultTruckDto = truckService.truckFindByDriver(personalNumber);
        return ResponseEntity
                .ok(resultTruckDto);
    }
}

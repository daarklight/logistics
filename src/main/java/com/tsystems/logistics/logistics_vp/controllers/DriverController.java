package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.DriversApi;
import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.services.interfaces.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DriverController implements DriversApi {

    private final DriverService driverService;

    @Override
    public ResponseEntity<DriverDto> driverCreate(CreateDriverDto createDriverDto) {
        DriverDto resultDriverDto = driverService.driverCreate(createDriverDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<Void> driverDelete(Integer personalNumber) {
        driverService.driverDelete(personalNumber);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<DriverDto> driverFindById(Integer personalNumber) {
        DriverDto resultDriverDto = driverService.driverFindByNumber(personalNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driverFindByNameAndSurname(String name, String surname) {
        List<DriverDto> resultDriverDto = driverService.driverFindByNameAndSurname(name, surname);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<DriverDto> driverUpdateByLogistician(Integer personalNumber, UpdateDriverByLogisticianDto updateDriverDto) {
        DriverDto resultDriverDto = driverService.driverUpdateByLogistician(personalNumber, updateDriverDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<DriverDto> driverUpdateStatusByDriver(Integer personalNumber, UpdateDriverStatusByDriverDto updateDriverDto) {
        DriverDto resultDriverDto = driverService.driverUpdateStatusByDriver(personalNumber, updateDriverDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindAll() {
        List<DriverDto> allResultDriverDtos = driverService.driversFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByBusyStatus(String busyStatus) {
        List<DriverDto> allResultDriverDtos = driverService.driversFindByBusyStatus(Busy.valueOf(busyStatus));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByCurrentCityAndState(String city, String state) {
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllByCurrentCityAndCurrentState(city, state);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByCurrentOrderId(Integer currentOrderId) {
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllByCurrentOrderId(currentOrderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByCurrentTruckNumber(String currentTruckNumber) {
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllByCurrentTruckNumber(currentTruckNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindForOrder(Integer orderId, String city, String state,
                                                               Integer workingHoursInCurrentMonth) {
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllForOrder(orderId, city, state, workingHoursInCurrentMonth);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<DriverDto> driversUpdateOrderAcceptance(
            Integer personalNumber, UpdateDriverOrderAcceptanceDto updateDriverOrderAcceptanceDto) {
        DriverDto resultDriverDto = driverService.driverOrderAcceptance(personalNumber, updateDriverOrderAcceptanceDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }
}

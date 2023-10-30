package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.DriversApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.DriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateDriverByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateDriverByLogisticianDto;
import com.tsystems.logistics.logistics_vp.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DriverController implements DriversApi {

    @Override
    public ResponseEntity<DriverDto> driverCreate(CreateDriverDto createDriverDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> driverDelete(Integer personalNumber) {
        return null;
    }

    @Override
    public ResponseEntity<DriverDto> driverFindById(Integer personalNumber) {
        return null;
    }

    @Override
    public ResponseEntity<List<DriverDto>> driverFindByNameAndSurname(String name, String surname) {
        return null;
    }

    @Override
    public ResponseEntity<DriverDto> driverUpdateByLogistician(Integer personalNumber, UpdateDriverByLogisticianDto updateDriverDto) {
        return null;
    }

    @Override
    public ResponseEntity<DriverDto> driverUpdateByDriver(Integer personalNumber, UpdateDriverByDriverDto updateDriverDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindAll() {
        return null;
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByBusyStatus(String busyStatus) {
        return null;
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByCurrentCityAndState(String city, String state) {
        return null;
    }

    @Override
    public ResponseEntity<DriverDto> driversFindByCurrentOrderId(Integer currentOrderId) {
        return null;
    }

    @Override
    public ResponseEntity<DriverDto> driversFindByCurrentTruckNumber(String currentTruckNumber) {
        return null;
    }
}

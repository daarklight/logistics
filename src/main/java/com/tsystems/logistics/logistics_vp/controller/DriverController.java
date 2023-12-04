package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.DriversApi;
import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.OrderAcceptance;
import com.tsystems.logistics.logistics_vp.service.interfaces.DriverService;
import com.tsystems.logistics.logistics_vp.service.interfaces.OrderService;
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
public class DriverController implements DriversApi {

    private final DriverService driverService;

    @Override
    public ResponseEntity<DriverDto> driverCreate(CreateDriverDto createDriverDto) {
        log.info("Start to register new driver");
        DriverDto resultDriverDto = driverService.driverCreate(createDriverDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<Void> driverDelete(Integer personalNumber) {
        log.info("Start to delete driver");
        driverService.driverDelete(personalNumber);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<DriverDto> driverFindById(Integer personalNumber) {
        log.info("Start to find driver by id");
        DriverDto resultDriverDto = driverService.driverFindByNumber(personalNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driverFindByNameAndSurname(String name, String surname) {
        log.info("Start to find driver by name and surname");
        List<DriverDto> resultDriverDto = driverService.driverFindByNameAndSurname(name, surname);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<DriverDto> driverUpdateByLogistician(Integer personalNumber, UpdateDriverByLogisticianDto updateDriverDto) {
        log.info("Start to update driver by logistician");
        DriverDto resultDriverDto = driverService.driverUpdateByLogistician(personalNumber, updateDriverDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<DriverDto> driverUpdateStatusByDriver(Integer personalNumber, UpdateDriverStatusByDriverDto updateDriverDto) {
        log.info("Start to update driver status by driver");
        DriverDto resultDriverDto = driverService.driverUpdateStatusByDriver(personalNumber, updateDriverDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindAll() {
        log.info("Start to find all drivers");
        List<DriverDto> allResultDriverDtos = driverService.driversFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByBusyStatus(String busyStatus) {
        log.info("Start to filter drivers by busy status");
        List<DriverDto> allResultDriverDtos = driverService.driversFindByBusyStatus(Busy.valueOf(busyStatus));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByCurrentCityAndState(String city, String state) {
        log.info("Start to filter drivers by current city and state");
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllByCurrentCityAndCurrentState(city, state);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByCurrentOrderId(Integer currentOrderId) {
        log.info("Start to filter drivers by id of current order");
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllByCurrentOrderId(currentOrderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindByCurrentTruckNumber(String currentTruckNumber) {
        log.info("Start to filter drivers by number of current truck");
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllByCurrentTruckNumber(currentTruckNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<List<DriverDto>> driversFindForOrder(Integer orderId, String city, String state,
                                                               Integer workingHoursInCurrentMonth) {
        log.info("Start to find drivers for order");
        List<DriverDto> allResultDriverDtos = driverService.driversFindAllForOrder(orderId, city, state, workingHoursInCurrentMonth);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultDriverDtos);
    }

    @Override
    public ResponseEntity<DriverDto> driversUpdateOrderAcceptance(Integer personalNumber, String orderAcceptance) {
        log.info("Start to update order acceptance from driver");
        DriverDto resultDriverDto = driverService.driverOrderAcceptance(personalNumber, OrderAcceptance.valueOf(orderAcceptance));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<DriverDto> driverFindByUsername(String username) {
        log.info("Start to find driver by username");
        DriverDto resultDriverDto = driverService.driverFindByUsername(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<DriverDto> driversFindCodriver(Integer currentOrderId, Integer personalNumber) {
        log.info("Start to find co-driver for defined driver");
        DriverDto resultDriverDto = driverService.driverFindCodriver(currentOrderId, personalNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }

    @Override
    public ResponseEntity<DriverDto> driverUpdateCurrentOrder(Integer orderId, Integer personalNumber) {
        log.info("Start to update current order for defined driver");
        DriverDto resultDriverDto = driverService.driverUpdateCurrentOrder(orderId, personalNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultDriverDto);
    }
}

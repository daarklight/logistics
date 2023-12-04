package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.CargosApi;
import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.service.interfaces.CargoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class CargoController implements CargosApi {

    private final CargoService cargoService;

    @Override
    public ResponseEntity<CargoDto> cargoCreate(CreateCargoDto createCargoDto) {
        log.info("Start to register new cargo");
        CargoDto resultCargoDto = null;
        try {
            resultCargoDto = cargoService.cargoCreate(createCargoDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<Void> cargoDelete(Integer cargoId) {
        log.info("Start to delete cargo");
        cargoService.cargoDelete(cargoId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByCargoName(String cargoName) {
        log.info("Start to find cargo by name");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindByName(cargoName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByEarlierThanExpectedCompletionDateTime(
            LocalDateTime expectedCompletionDateTime) {
        log.info("Start to filter cargo earlier than expected completion date and time");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindEarlierThanExpectedCompletionDateTime(
                expectedCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByEarlierThanRealCompletionDateTime(LocalDateTime realCompletionDateTime) {
        log.info("Start to filter cargo earlier than real completion date and time");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindEarlierThanRealCompletionDateTime(
                realCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByFinalCityAndStateAndAddress(String city, String state, String address) {
        log.info("Start to filter cargo by final city, state and address");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByFinalCityAndStateAndAddress(city, state, address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByFinalCityAndState(String city, String state) {
        log.info("Start to filter cargo by final city and state");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByFinalCityAndState(city, state);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<CargoDto> cargoFindById(Integer cargoId) {
        log.info("Start to find cargo by id");
        CargoDto resultCargoDto = cargoService.cargoFindById(cargoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLaterThanExpectedCompletionDateTime(LocalDateTime expectedCompletionDateTime) {
        log.info("Start to filter cargo after expected completion date and time");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindLaterThanExpectedCompletionDateTime(
                expectedCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLaterThanRealCompletionDateTime(LocalDateTime realCompletionDateTime) {
        log.info("Start to filter cargo after real completion date and time");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindLaterThanRealCompletionDateTime(
                realCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLoaded() {
        log.info("Start to filter loaded cargo");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindLoaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByNonLoaded() {
        log.info("Start to filter non-loaded cargo");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindNonLoaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByNonUnloaded() {
        log.info("Start to filter non-unloaded cargo");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindNonUnloaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByOrderId(Integer orderForCargoId) {
        log.info("Start to filter cargo by order id");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindByOrderForCargoId(orderForCargoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByStartCityAndStateAndAddress(String city, String state, String address) {
        log.info("Start to filter cargo by start city, state, address");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByStartCityAndStateAndAddress(city, state, address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByStartCityAndState(String city, String state) {
        log.info("Start to filter cargo by start city and state");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByStartCityAndState(city, state);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByUnloaded() {
        log.info("Start to filter unloaded cargo");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindUnloaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateByLogistician(Integer cargoId, UpdateCargoByLogisticianDto updateCargoDto) {
        log.info("Start to update cargo by logistician");
        CargoDto resultCargoDto = cargoService.cargoUpdateByLogistician(cargoId, updateCargoDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateByDriver(Integer cargoId, UpdateCargoByDriverDto updateCargoDto) {
        log.info("Start to update cargo by driver");
        CargoDto resultCargoDto = cargoService.cargoUpdateByDriver(cargoId, updateCargoDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargosFindAll() {
        log.info("Start to find all cargos");
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateLoading(Integer cargoId) {
        log.info("Start to update cargo loaded status");
        CargoDto resultCargoDto = cargoService.cargoUpdateLoading(cargoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateUnloading(Integer cargoId) {
        log.info("Start to update cargo unloaded status");
        CargoDto resultCargoDto = cargoService.cargoUpdateUnloading(cargoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }
}

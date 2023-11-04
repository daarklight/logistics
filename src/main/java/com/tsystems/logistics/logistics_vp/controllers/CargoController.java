package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.CargosApi;
import com.tsystems.logistics.logistics_vp.code.model.CargoDto;
import com.tsystems.logistics.logistics_vp.code.model.CreateCargoDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByLogisticianDto;
import com.tsystems.logistics.logistics_vp.services.interfaces.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CargoController implements CargosApi {

    private final CargoService cargoService;

    @Override
    public ResponseEntity<CargoDto> cargoCreate(CreateCargoDto createCargoDto) {
        CargoDto resultCargoDto = cargoService.cargoCreate(createCargoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<Void> cargoDelete(Integer cargoId) {
        cargoService.cargoDelete(cargoId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByCargoName(String cargoName) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindByName(cargoName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByEarlierThanExpectedCompletionDateTime(
            LocalDateTime expectedCompletionDateTime) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindEarlierThanExpectedCompletionDateTime(
                expectedCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByEarlierThanRealCompletionDateTime(LocalDateTime realCompletionDateTime) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindEarlierThanRealCompletionDateTime(
                realCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByFinalCityAndStateAndAddress(String city, String state, String address) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByFinalCityAndStateAndAddress(city, state, address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByFinalCityAndState(String city, String state) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByFinalCityAndState(city, state);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<CargoDto> cargoFindById(Integer cargoId) {
        CargoDto resultCargoDto = cargoService.cargoFindById(cargoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLaterThanExpectedCompletionDateTime(LocalDateTime expectedCompletionDateTime) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindLaterThanExpectedCompletionDateTime(
                expectedCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLaterThanRealCompletionDateTime(LocalDateTime realCompletionDateTime) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindLaterThanRealCompletionDateTime(
                realCompletionDateTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLoaded() {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindLoaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByNonLoaded() {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindNonLoaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByNonUnloaded() {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindNonUnloaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByOrderId(Integer orderForCargoId) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindByOrderForCargoId(orderForCargoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByStartCityAndStateAndAddress(String city, String state, String address) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByStartCityAndStateAndAddress(city, state, address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByStartCityAndState(String city, String state) {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAllByStartCityAndState(city, state);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByUnloaded() {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindUnloaded();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateByLogistician(Integer cargoId, UpdateCargoByLogisticianDto updateCargoDto) {
        CargoDto resultCargoDto = cargoService.cargoUpdateByLogistician(cargoId, updateCargoDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateByDriver(Integer cargoId, UpdateCargoByDriverDto updateCargoDto) {
        CargoDto resultCargoDto = cargoService.cargoUpdateByDriver(cargoId, updateCargoDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCargoDto);
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargosFindAll() {
        List<CargoDto> allResultCargoDtos = cargoService.cargosFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCargoDtos);
    }
}

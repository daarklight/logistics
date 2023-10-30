package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.CargosApi;
import com.tsystems.logistics.logistics_vp.code.model.CargoDto;
import com.tsystems.logistics.logistics_vp.code.model.CreateCargoDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByLogisticianDto;
import com.tsystems.logistics.logistics_vp.repositories.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CargoController implements CargosApi {

    @Override
    public ResponseEntity<CargoDto> cargoCreate(CreateCargoDto createCargoDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> cargoDelete(Integer cargoId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByCargoName(String fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByEarlierThanExpectedCompletionDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByEarlierThanRealCompletionDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByFinalAddress(String fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByFinalCityAndState(String city, String state) {
        return null;
    }

    @Override
    public ResponseEntity<CargoDto> cargoFindById(Integer cargoId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLaterThanExpectedCompletionDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLaterThanRealCompletionDateTime(OffsetDateTime fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByLoaded() {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByNonLoaded() {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByNonUnloaded() {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByOrderId(Integer orderForCargoId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByStartAddress(String fields) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByStartCityAndState(String city, String state) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargoFindByUnloaded() {
        return null;
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateByLogistician(Integer cargoId, UpdateCargoByLogisticianDto updateCargoDto) {
        return null;
    }

    @Override
    public ResponseEntity<CargoDto> cargoUpdateByDriver(Integer cargoId, UpdateCargoByDriverDto updateCargoDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<CargoDto>> cargosFindAll() {
        return null;
    }
}

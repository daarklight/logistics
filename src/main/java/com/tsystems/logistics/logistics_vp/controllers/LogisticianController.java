package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.LogisticiansApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateLogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.LogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateLogisticianDto;
import com.tsystems.logistics.logistics_vp.services.interfaces.LogisticianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogisticianController implements LogisticiansApi {

    private final LogisticianService logisticianService;

    @Override
    public ResponseEntity<LogisticianDto> logisticianCreate(CreateLogisticianDto createLogisticianDto) {
        LogisticianDto logisticianDto = logisticianService.logisticianCreate(createLogisticianDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(logisticianDto);
    }

    @Override
    public ResponseEntity<Void> logisticianDelete(Integer personalNumber) {
        logisticianService.deleteLogistician(personalNumber);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<LogisticianDto> logisticianFindById(Integer personalNumber) {
        LogisticianDto resultLogisticianDto = logisticianService.logisticianFindByNumber(personalNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultLogisticianDto);
    }

    @Override
    public ResponseEntity<List<LogisticianDto>> logisticianFindByNameAndSurname(String name, String surname) {
        List<LogisticianDto> resultLogisticianDto = logisticianService.logisticianFindByNameAndSurname(name, surname);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultLogisticianDto);
    }

    @Override
    public ResponseEntity<LogisticianDto> logisticianUpdate(Integer personalNumber, UpdateLogisticianDto updateLogisticianDto) {
        LogisticianDto logisticianDto = logisticianService.logisticianUpdate(personalNumber, updateLogisticianDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logisticianDto);
    }

    @Override
    public ResponseEntity<List<LogisticianDto>> logisticiansFindAll() {
        List<LogisticianDto> allLogisticians = logisticianService.logisticiansFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allLogisticians);
    }
}

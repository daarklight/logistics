package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.LogisticiansApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateLogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.LogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateLogisticianDto;
import com.tsystems.logistics.logistics_vp.service.interfaces.LogisticianService;
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
public class LogisticianController implements LogisticiansApi {

    private final LogisticianService logisticianService;

    @Override
    public ResponseEntity<LogisticianDto> logisticianCreate(CreateLogisticianDto createLogisticianDto) {
        log.info("Start to register new logistician");
        LogisticianDto logisticianDto = logisticianService.logisticianCreate(createLogisticianDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(logisticianDto);
    }

    @Override
    public ResponseEntity<Void> logisticianDelete(Integer personalNumber) {
        log.info("Start to delete new logistician");
        logisticianService.deleteLogistician(personalNumber);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<LogisticianDto> logisticianFindById(Integer personalNumber) {
        log.info("Start to find logistician by id");
        LogisticianDto resultLogisticianDto = logisticianService.logisticianFindByNumber(personalNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultLogisticianDto);
    }

    @Override
    public ResponseEntity<List<LogisticianDto>> logisticianFindByNameAndSurname(String name, String surname) {
        log.info("Start to find logistician by name and surname");
        List<LogisticianDto> resultLogisticianDto = logisticianService.logisticianFindByNameAndSurname(name, surname);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultLogisticianDto);
    }

    @Override
    public ResponseEntity<LogisticianDto> logisticianUpdate(Integer personalNumber, UpdateLogisticianDto updateLogisticianDto) {
        log.info("Start to update logistician");
        LogisticianDto logisticianDto = logisticianService.logisticianUpdate(personalNumber, updateLogisticianDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logisticianDto);
    }

    @Override
    public ResponseEntity<List<LogisticianDto>> logisticiansFindAll() {
        log.info("Start to find all logisticians");
        List<LogisticianDto> allLogisticians = logisticianService.logisticiansFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allLogisticians);
    }

    @Override
    public ResponseEntity<LogisticianDto> logisticianFindByUsername(String username) {
        log.info("Start to find logistician by username");
        LogisticianDto resultLogisticianDto = logisticianService.logisticianFindByUsername(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultLogisticianDto);
    }
}

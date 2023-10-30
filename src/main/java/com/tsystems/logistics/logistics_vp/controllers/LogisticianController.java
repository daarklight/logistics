package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.LogisticiansApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateLogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.LogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateLogisticianDto;
import com.tsystems.logistics.logistics_vp.repositories.LogisticianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogisticianController implements LogisticiansApi {

    @Override
    public ResponseEntity<LogisticianDto> logisticianCreate(CreateLogisticianDto createLogisticianDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logisticianDelete(Integer personalNumber) {
        return null;
    }

    @Override
    public ResponseEntity<LogisticianDto> logisticianFindById(Integer personalNumber) {
        return null;
    }

    @Override
    public ResponseEntity<List<LogisticianDto>> logisticianFindByNameAndSurname(String name, String surname) {
        return null;
    }

    @Override
    public ResponseEntity<LogisticianDto> logisticianUpdate(Integer personalNumber, UpdateLogisticianDto updateLogisticianDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<LogisticianDto>> logisticiansFindAll() {
        return null;
    }
}

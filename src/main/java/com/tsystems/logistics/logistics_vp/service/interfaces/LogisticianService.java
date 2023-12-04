package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.CreateLogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.LogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateLogisticianDto;
import com.tsystems.logistics.logistics_vp.entity.Logistician;

import java.util.List;

public interface LogisticianService {
    List<LogisticianDto> logisticiansFindAll();
    LogisticianDto logisticianCreate(CreateLogisticianDto logisticianDto);
    LogisticianDto logisticianUpdate(Integer personalNumber, UpdateLogisticianDto logisticianDto);
    void deleteLogistician(Integer personalNumber);
    LogisticianDto logisticianFindByNumber(Integer personalNumber);
    List<LogisticianDto> logisticianFindByNameAndSurname(String name, String surname);
    LogisticianDto logisticianFindByUsername(String username);
    Logistician getLogisticianFromDb(Integer personalNumber);
}

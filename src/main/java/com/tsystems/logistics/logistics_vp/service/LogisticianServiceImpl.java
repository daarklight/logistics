package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.CreateLogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.LogisticianDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateLogisticianDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entity.Logistician;
import com.tsystems.logistics.logistics_vp.mapper.LogisticianMapper;
import com.tsystems.logistics.logistics_vp.repository.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.repository.LogisticianRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.LogisticianService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LogisticianServiceImpl implements LogisticianService {

    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final LogisticianRepository logisticianRepository;

    @Override
    public List<LogisticianDto> logisticiansFindAll() {
        return logisticianRepository.findAll().stream().map(logistician -> logisticianDto(logistician)).toList();
    }

    @Override
    public LogisticianDto logisticianCreate(CreateLogisticianDto logisticianDto) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findById(
                logisticianDto.getLogisticianAuthenticationId()).orElseThrow();
        Logistician logistician = Logistician.builder()
                .logisticianAuthenticationId(authenticationInfo)
                .name(logisticianDto.getName())
                .surname(logisticianDto.getSurname())
                .build();
        logisticianRepository.save(logistician);
        return logisticianDto(logistician);
    }

    @Override
    public LogisticianDto logisticianUpdate(Integer personalNumber, UpdateLogisticianDto logisticianDto) {
        Logistician logistician = logisticianRepository.findById(personalNumber).orElseThrow();
        logistician.setName(logisticianDto.getName());
        logistician.setSurname(logisticianDto.getSurname());
        return logisticianDto(logistician);
    }

    @Override
    public void deleteLogistician(Integer personalNumber) {
        logisticianRepository.deleteById(personalNumber);

    }

    @Override
    public LogisticianDto logisticianFindByNumber(Integer personalNumber) {
        Logistician logistician = logisticianRepository.findById(personalNumber).orElseThrow();
        return logisticianDto(logistician);
    }

    @Override
    public List<LogisticianDto> logisticianFindByNameAndSurname(String name, String surname) {
        return logisticianRepository.findAllByNameAndSurname(name, surname).stream().map(
                logistician -> logisticianDto(logistician)).toList();
    }

    @Override
    public LogisticianDto logisticianFindByUsername(String username) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findByUsername(username);
        Logistician logistician = authenticationInfo.getLogistician();
        return logisticianDto(logistician);
    }

    private LogisticianDto logisticianDto(Logistician logistician) {
        return LogisticianMapper.INSTANCE.logisticianToLogisticianDto(logistician);
    }
}

package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.entity.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.DriverStatus;
import com.tsystems.logistics.logistics_vp.enums.OrderAcceptance;
import com.tsystems.logistics.logistics_vp.mapper.DriverMapper;
import com.tsystems.logistics.logistics_vp.repository.*;
import com.tsystems.logistics.logistics_vp.repository.customized.CustomizedDriverRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.DriverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final DriverRepository driverRepository;
    private final CustomizedDriverRepository customizedDriverRepository;
    private final TruckRepository truckRepository;
    private final OrderRepository orderRepository;

    public List<DriverDto> driversFindAll() {
        return driverRepository.findAll().stream().map(driver -> driverDto(driver)).toList();
    }

    public DriverDto driverCreate(CreateDriverDto driverDto) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findById(driverDto.getDriverAuthenticationId()).orElseThrow();
        Driver driver = Driver.builder()
                .driverAuthenticationId(authenticationInfo)
                .name(driverDto.getName())
                .surname(driverDto.getSurname())
                .phone(driverDto.getPhone())
                .email(driverDto.getEmail())
                .workExperience(driverDto.getWorkExperience())
                .workingHoursInCurrentMonth(driverDto.getWorkingHoursInCurrentMonth())
                .status(DriverStatus.REST)
                .busy(Busy.NO)
                .currentCity(driverDto.getCurrentCity())
                .currentState(driverDto.getCurrentState())
                .build();
        driverRepository.save(driver);
        return driverDto(driver);
    }

    @Override
    public DriverDto driverUpdateByLogistician(Integer personalNumber, UpdateDriverByLogisticianDto driverDto) {
        Truck truck = truckRepository.findById(driverDto.getCurrentTruckNumber()).orElseThrow();
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        driver.setName(driverDto.getName());
        driver.setSurname(driverDto.getSurname());
        driver.setPhone(driverDto.getPhone());
        driver.setEmail(driverDto.getEmail());
        driver.setWorkExperience(driverDto.getWorkExperience());
        driver.setCurrentCity(driverDto.getCurrentCity());
        driver.setCurrentState(driverDto.getCurrentState());
        driver.setCurrentTruckNumber(truck);
        driverRepository.save(driver);
        return driverDto(driver);
    }

    @Override
    public DriverDto driverUpdateStatusByDriver(Integer personalNumber, UpdateDriverStatusByDriverDto driverDto) {
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        driver.setStatus(DriverStatus.valueOf(driverDto.getStatus().toString()));
        driverRepository.save(driver);
        return driverDto(driver);
    }

    public void driverDelete(Integer personalNumber) {
        driverRepository.deleteById(personalNumber);
    }

    @Override
    public DriverDto driverFindByNumber(Integer personalNumber) {
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        return driverDto(driver);
    }

    @Override
    public List<DriverDto> driverFindByNameAndSurname(String name, String surname) {
        return customizedDriverRepository.findAllByNameAndSurname(name, surname).stream().map(
                driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindByBusyStatus(Busy busy) {
        return customizedDriverRepository.findAllByBusy(busy).stream().map(driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllByCurrentCityAndCurrentState(String city, String state) {
        return customizedDriverRepository.findAllByCurrentCityAndCurrentState(city, state)
                .stream().map(driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllByCurrentTruckNumber(String truckNumber) {
        Truck truck = truckRepository.findById(truckNumber).orElseThrow();
        return customizedDriverRepository.findAllByCurrentTruckNumber(truck).stream().map(driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllByCurrentOrderId(Integer currentOrderId) {
        Order order = orderRepository.findById(currentOrderId).orElseThrow();
        return customizedDriverRepository.findAllByCurrentOrderId(order).stream().map(
                driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllForOrder(Integer orderId, String city, String state, Integer hours) {
        return customizedDriverRepository.findAllByCurrentCityAndCurrentStateAndWorkingHoursInCurrentMonthLessThan(
                city, state, hours).stream().map(driver -> driverDto(driver))
                .filter(dto -> dto.getBusy().toString().equals("NO")).toList();
    }

    @Override
    public DriverDto driverOrderAcceptance(Integer personalNumber, UpdateDriverOrderAcceptanceDto updateDriverOrderAcceptanceDto) {
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        driver.setOrderAcceptance(OrderAcceptance.valueOf(updateDriverOrderAcceptanceDto.getOrderAcceptance().toString()));
        driverRepository.save(driver);
        return driverDto(driver);
    }

    private DriverDto driverDto(Driver driver) {
        return DriverMapper.INSTANCE.driverToDriverDto(driver);
    }
}

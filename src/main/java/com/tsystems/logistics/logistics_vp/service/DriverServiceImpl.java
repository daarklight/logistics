package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.*;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.DriverStatus;
import com.tsystems.logistics.logistics_vp.enums.OrderAcceptance;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import com.tsystems.logistics.logistics_vp.exceptions.custom.BusyDriverException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NoProperDriversException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NoSuchDriverException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.TooManyDriversException;
import com.tsystems.logistics.logistics_vp.mapper.DriverMapper;
import com.tsystems.logistics.logistics_vp.repository.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.repository.DriverRepository;
import com.tsystems.logistics.logistics_vp.repository.OrderRepository;
import com.tsystems.logistics.logistics_vp.repository.TruckRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.DriverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class DriverServiceImpl implements DriverService {

    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final DriverRepository driverRepository;
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
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        driver.setName(driverDto.getName());
        driver.setSurname(driverDto.getSurname());
        driver.setPhone(driverDto.getPhone());
        driver.setEmail(driverDto.getEmail());
        driver.setWorkExperience(driverDto.getWorkExperience());
        driver.setCurrentCity(driverDto.getCurrentCity());
        driver.setCurrentState(driverDto.getCurrentState());
        driverRepository.save(driver);
        return driverDto(driver);
    }

    @Override
    public DriverDto driverUpdateStatusByDriver(Integer personalNumber, UpdateDriverStatusByDriverDto driverDto) {
        Driver driver = getDriverFromDb(personalNumber);
        driver.setStatus(DriverStatus.valueOf(driverDto.getStatus().toString()));
        driverRepository.save(driver);
        return driverDto(driver);
    }

    public void driverDelete(Integer personalNumber) {
        getDriverFromDb(personalNumber);
        driverRepository.deleteById(personalNumber);
    }

    @Override
    public DriverDto driverFindByNumber(Integer personalNumber) {
        getDriverFromDb(personalNumber);
        return driverDto(getDriverFromDb(personalNumber));
    }

    @Override
    public List<DriverDto> driverFindByNameAndSurname(String name, String surname) {
        return driverRepository.findAllByNameAndSurname(name, surname).stream().map(
                driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindByBusyStatus(Busy busy) {
        return driverRepository.findAllByBusy(busy).stream().map(driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllByCurrentCityAndCurrentState(String city, String state) {
        return driverRepository.findAllByCurrentCityAndCurrentState(city, state)
                .stream().map(driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllByCurrentTruckNumber(String truckNumber) {
        Truck truck = truckRepository.findById(truckNumber).orElseThrow();
        return driverRepository.findAllByCurrentTruckNumber(truck).stream().map(driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllByCurrentOrderId(Integer currentOrderId) {
        Order order = orderRepository.findById(currentOrderId).orElseThrow();
        return driverRepository.findAllByCurrentOrderId(order).stream().map(
                driver -> driverDto(driver)).toList();
    }

    @Override
    public List<DriverDto> driversFindAllForOrder(Integer orderId, String city, String state, Integer hours) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        int totalOnRoadRideDurationInMinutes = order.getCargos().stream()
                .mapToInt(elem -> elem.getRideDurationFromStartPoint()).sum();
        int calculatedAcceptableHoursForTwoDrivers =
                calculateAcceptableDriverHours(2, totalOnRoadRideDurationInMinutes);
        List<DriverDto> properDriversDtos = driverRepository
                .findAllByCurrentCityAndCurrentStateAndWorkingHoursInCurrentMonthLessThan(city, state,
                        calculatedAcceptableHoursForTwoDrivers).stream()
                .map(driver -> driverDto(driver))
                .filter(dto -> dto.getBusy().toString().equals("NO")).toList();
        if (properDriversDtos.size() > 0) {
            return properDriversDtos;
        } else {
            throw new NoProperDriversException("No proper drivers were found for this order");
        }
    }

    @Override
    public DriverDto driverOrderAcceptance(Integer personalNumber, OrderAcceptance orderAcceptance) {
        Driver driver = getDriverFromDb(personalNumber);
        driver.setOrderAcceptance(OrderAcceptance.valueOf(orderAcceptance.toString()));
        int orderId = driver.getCurrentOrderId().getOrderId();
        if (orderAcceptance.equals(OrderAcceptance.YES)) {
            // ADD LOGIC THAT FOR CASE OF CODRIVERS => ORDER STATUS IS CONFIRMED ONLY WHEN BOTH ACCEPT THE ORDER
            // ALSO ADD LOGIC WHEN TRUCK BECOMES BUSY

            driver.getCurrentOrderId().setStatus(OrderStatus.CONFIRMED);
            //driver.setBusy(Busy.YES);
            log.info(String.format("Driver %s %s confirmed order# %s",
                    driver.getName(), driver.getSurname(), driver.getCurrentOrderId().getOrderId()));
        } else {
            driver.getCurrentOrderId().setStatus(OrderStatus.DECLINED_BY_DRIVERS);
            driver.setCurrentOrderId(null);
            driver.setOrderAcceptance(null);
            log.info(String.format("Driver %s %s declined order# %s",
                    driver.getName(), driver.getSurname(), orderId));
        }
        //driverRepository.save(driver);
        return driverDto(driver);
    }

    @Override
    public DriverDto driverFindByUsername(String username) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findByUsername(username);
        Driver driver = authenticationInfo.getDriver();
        return driverDto(driver);
    }

    @Override
    public DriverDto driverFindCodriver(Integer currentOrderId, Integer personalNumber) {
        getDriverFromDb(personalNumber);
        List<DriverDto> allDriversForOrder = driversFindAllByCurrentOrderId(currentOrderId);
        DriverDto driverDto = new DriverDto();
        if (allDriversForOrder.size() == 2) {
            driverDto = allDriversForOrder.stream().filter(elem -> !elem.getPersonalNumber().equals(personalNumber))
                    .collect(Collectors.toList()).get(0);
        }
        return driverDto;
    }

    @Override
    public DriverDto driverUpdateCurrentOrder(Integer orderId, Integer personalNumber) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Driver driver = getDriverFromDb(personalNumber);
        if (driver.getBusy().equals(Busy.NO)) {
            if (order.getDrivers().size() < 2) {
                driver.setCurrentOrderId(order);
                driver.setBusy(Busy.YES);
                log.info(String.format("Driver %s %s was preliminary assigned for order# %s",
                        driver.getName(), driver.getSurname(), orderId));
                return driverDto(driver);
            } else {
                throw new TooManyDriversException("It is impossible to put more than two drivers for one order");
            }
        } else {
            throw new BusyDriverException("It is impossible to assign busy driver");
        }
    }

    @Override
    public Driver getDriverFromDb(Integer personalNumber) {
        return driverRepository.findById(personalNumber).orElseThrow(() ->
                new NoSuchDriverException("This driver does not exist in database"));
    }

    // This method calculates maximum spent hours that driver can have to ensure that he has enough hours to take the order
    public int calculateAcceptableDriverHours(int numberOfDrivers, int onRoadRideMinutes) {
        // 8 hours is approximate estimation of time that is necessary:
        // - to move the driver from driver/truck location to customer location to take stuff;
        // - to load and unload all cargos (for simplicity we consider that it does not depend on number of cargos)
        int totalOrderHours = onRoadRideMinutes/60 + 8;

        LocalDate today = LocalDate.now();
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        long restDaysInThisMonth = DAYS.between(today, endOfMonth);
        // standard driver work shift is taken as 8 hours
        int totalMonthDriversHours = (int) restDaysInThisMonth*8*numberOfDrivers;
        int acceptableHours = 0;
        if (totalMonthDriversHours >= totalOrderHours) {
            acceptableHours = 176 - totalOrderHours/numberOfDrivers;
        } else {
            acceptableHours = 176 - totalMonthDriversHours;
        }
        return acceptableHours;
    }

    private DriverDto driverDto(Driver driver) {
        return DriverMapper.INSTANCE.driverToDriverDto(driver);
    }
}

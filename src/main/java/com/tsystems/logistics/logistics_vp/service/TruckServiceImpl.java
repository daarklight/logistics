package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.CreateTruckDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByLogisticianDto;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.entity.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import com.tsystems.logistics.logistics_vp.exceptions.custom.ImpossibleBusyTruckDeleteException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NoProperTrucksException;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NoSuchTruckException;
import com.tsystems.logistics.logistics_vp.mapper.TruckMapper;
import com.tsystems.logistics.logistics_vp.repository.DriverRepository;
import com.tsystems.logistics.logistics_vp.repository.TruckRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.TruckService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TruckServiceImpl implements TruckService {

    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;

    @Override
    public List<TruckDto> trucksFindAll() {
        return truckRepository.findAll().stream().map(truck -> truckDto(truck)).toList();
    }

    @Override
    public List<TruckDto> trucksFindAllByCurrentCityAndState(String city, String state) {
        return truckRepository.findAllByCurrentCityAndCurrentState(city, state)
                .stream().map(truck -> truckDto(truck)).toList();
    }

    @Override
    public List<TruckDto> findAllForOrder(Integer orderId, String city, String state, Double capacity) {
        List<TruckDto> properTrucksDtos = truckRepository
                .findAllByCurrentCityAndCurrentStateAndCapacityGreaterThanEqual(city, state, capacity)
                .stream().map(truck -> truckDto(truck)).filter(dto -> dto.getBusy().toString().equals("NO")).toList();
        if (properTrucksDtos.size() > 0) {
            return properTrucksDtos;
        } else {
            throw new NoProperTrucksException("No proper trucks were found for this order");
        }
    }

    @Override
    public List<TruckDto> trucksFindByBusyStatus(Busy busy) {
        return truckRepository.findAllByBusy(busy).stream().map(truck -> truckDto(truck)).toList();
    }

    @Override
    public List<TruckDto> trucksFindAllByTechnicalCondition(TechnicalCondition technicalCondition) {
        return truckRepository.findAllByTechnicalCondition(technicalCondition).stream().map(truck -> truckDto(truck)).toList();
    }

    @Override
    public void truckDelete(String number) {
        Truck truck = getTruckFromDb(number);
        Busy truckBusyStatus = truck.getBusy();
        if (truckBusyStatus == Busy.NO) {
            truckRepository.deleteById(number);
        } else {
            throw new ImpossibleBusyTruckDeleteException("It is impossible to delete busy truck");
        }

    }

    @Override
    public TruckDto truckFindByNumber(String number) {
        return truckDto(getTruckFromDb(number));
    }

    @Override
    public TruckDto truckCreate(CreateTruckDto truckDto) {
        Truck truck = Truck.builder()
                .number(truckDto.getNumber())
                .model(truckDto.getModel())
                .capacity(truckDto.getCapacity())
                .totalOperatingTime(truckDto.getTotalOperatingTime())
                .technicalCondition(TechnicalCondition.OK)
                .busy(Busy.NO)
                .currentCity(truckDto.getCurrentCity())
                .currentState(truckDto.getCurrentState())
                .build();
        truckRepository.save(truck);
        return truckDto(truck);
    }

    @Override
    public TruckDto truckUpdateByLogistician(String number, UpdateTruckByLogisticianDto truckDto) {
        Truck truck = getTruckFromDb(number);
        truck.setModel(truckDto.getModel());
        truck.setCapacity(truckDto.getCapacity());
        truck.setTotalOperatingTime(truckDto.getTotalOperatingTime());
        truck.setTechnicalCondition(TechnicalCondition.valueOf(truckDto.getTechnicalCondition().toString()));
        truck.setCurrentCity(truckDto.getCurrentCity());
        truck.setCurrentState(truckDto.getCurrentState());
        truckRepository.save(truck);
        return truckDto(truck);
    }

    @Override
    public TruckDto truckUpdateByDriver(String number, UpdateTruckByDriverDto truckDto) {
        Truck truck = getTruckFromDb(number);
        truck.setModel(number);
        truck.setTechnicalCondition(TechnicalCondition.valueOf(truckDto.getTechnicalCondition().toString()));
        return truckDto(truck);
    }

    @Override
    public TruckDto truckFindByDriver(Integer personalNumber) {
        Driver driver = driverRepository.findById(personalNumber).orElseThrow();
        Truck truck = driver.getCurrentTruckNumber();
        return truckDto(truck);
    }

    @Override
    public Truck getTruckFromDb(String number) {
        return truckRepository.findById(number).orElseThrow(() ->
                new NoSuchTruckException("This truck does not exist in database"));
    }

    private TruckDto truckDto(Truck truck) {
        return TruckMapper.INSTANCE.truckToTruckDto(truck);
    }
}

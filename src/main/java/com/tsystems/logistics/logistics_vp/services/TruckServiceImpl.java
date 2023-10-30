package com.tsystems.logistics.logistics_vp.services;

import com.tsystems.logistics.logistics_vp.code.model.CreateTruckDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateTruckByLogisticianDto;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import com.tsystems.logistics.logistics_vp.mappers.TruckMapper;
import com.tsystems.logistics.logistics_vp.repositories.CustomizedTruckRepository;
import com.tsystems.logistics.logistics_vp.repositories.TruckRepository;
import com.tsystems.logistics.logistics_vp.services.interfaces.TruckService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;
    private final CustomizedTruckRepository customizedTruckRepository;

    public List<TruckDto> trucksFindAll() {
        return truckRepository.findAll().stream().map(truck -> truckDto(truck)).toList();
    }

    public List<TruckDto> trucksFindAllByCurrentCityAndCurrentState(String city, String state) {
        return customizedTruckRepository.findAllByCurrentCityAndCurrentState(city, state)
                .stream().map(truck -> truckDto(truck)).toList();
    }

    public List<TruckDto> trucksFindByBusyStatus(Busy busy){
        return customizedTruckRepository.findAllByBusy(busy).stream().map(truck -> truckDto(truck)).toList();
    }

    public List<TruckDto> trucksFindAllByTechnicalCondition(TechnicalCondition technicalCondition){
        return customizedTruckRepository.findAllByTechnicalCondition(technicalCondition).stream().map(truck -> truckDto(truck)).toList();
    }


    public void truckDelete(String number) {
        truckRepository.deleteById(number);
    }

    public TruckDto truckFindByNumber(String number) {
        Truck truck = truckRepository.findById(number).orElseThrow();
        return truckDto(truck);
    }

    public TruckDto truckCreate(CreateTruckDto truckDto) {
        Truck truck = new Truck();
        truck.setNumber(truckDto.getNumber());
        truck.setModel(truckDto.getModel());
        truck.setCapacity(truckDto.getCapacity());
        truck.setTotalOperatingTime(truckDto.getTotalOperatingTime());
        truck.setTechnicalCondition(TechnicalCondition.OK);
        truck.setBusy(Busy.NO);
        truck.setCurrentCity(truckDto.getCurrentCity());
        truck.setCurrentState(truckDto.getCurrentState());
        truckRepository.save(truck);
        return truckDto(truck);
    }

    public TruckDto truckUpdateByLogistician(String number, UpdateTruckByLogisticianDto truckDto) {
        Truck truck = truckRepository.findById(number).orElseThrow();
        truck.setModel(number);
        truck.setModel(truckDto.getModel());
        truck.setCapacity(truckDto.getCapacity());
        truck.setTotalOperatingTime(truckDto.getTotalOperatingTime());
        truck.setTechnicalCondition(TechnicalCondition.valueOf(truckDto.getTechnicalCondition().toString()));
        truck.setCurrentCity(truckDto.getCurrentCity());
        truck.setCurrentState(truckDto.getCurrentState());
        truckRepository.save(truck);
        return truckDto(truck);
    }

    public TruckDto truckUpdateByDriver(String number, UpdateTruckByDriverDto truckDto) {
        Truck truck = truckRepository.findById(number).orElseThrow();
        truck.setModel(number);
        truck.setTechnicalCondition(TechnicalCondition.valueOf(truckDto.getTechnicalCondition().toString()));
        return truckDto(truck);
    }

    private TruckDto truckDto(Truck truck) {
        return TruckMapper.INSTANCE.truckToTruckDto(truck);
    }
}

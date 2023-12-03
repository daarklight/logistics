package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.*;
import com.tsystems.logistics.logistics_vp.entity.Cargo;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.enums.Loaded;
import com.tsystems.logistics.logistics_vp.enums.Unloaded;
import com.tsystems.logistics.logistics_vp.mapper.CargoMapper;
import com.tsystems.logistics.logistics_vp.repository.CargoRepository;
import com.tsystems.logistics.logistics_vp.repository.OrderRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.CargoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<CargoDto> cargosFindAll() {
        return cargoRepository.findAll().stream().map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public CargoDto cargoCreate(CreateCargoDto cargoDto) {
        Order order = orderRepository.findById(cargoDto.getOrderForCargoId()).orElseThrow();
        Cargo cargo = Cargo.builder()
                .orderForCargoId(order)
                .cargoName(cargoDto.getCargoName())
                .weight(cargoDto.getWeight())
                .startCity(cargoDto.getStartCity())
                .startState(cargoDto.getStartState())
                .startAddress(cargoDto.getStartAddress())
                .loaded(Loaded.NO)
                .finalCity(cargoDto.getFinalCity())
                .finalState(cargoDto.getFinalState())
                .finalAddress(cargoDto.getFinalAddress())
                .unloaded(Unloaded.NO)
                .build();
        cargoRepository.save(cargo);
        return cargoDto(cargo);
    }

    @Override
    public CargoDto cargoUpdateByLogistician(Integer cargoId, UpdateCargoByLogisticianDto cargoDto) {
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow();
        cargo.setCargoName(cargoDto.getCargoName());
        cargo.setWeight(cargoDto.getWeight());
        cargo.setStartCity(cargoDto.getStartCity());
        cargo.setStartState(cargoDto.getStartState());
        cargo.setStartAddress(cargoDto.getStartAddress());
        cargo.setFinalCity(cargoDto.getFinalCity());
        cargo.setFinalState(cargoDto.getFinalState());
        cargo.setFinalAddress(cargoDto.getFinalAddress());
        cargoRepository.save(cargo);
        return cargoDto(cargo);
    }

    @Override
    public CargoDto cargoUpdateByDriver(Integer cargoId, UpdateCargoByDriverDto cargoDto) {
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow();
        cargo.setLoaded(Loaded.valueOf(cargoDto.getLoaded().toString()));
        cargo.setUnloaded(Unloaded.valueOf(cargoDto.getLoaded().toString()));
        cargoRepository.save(cargo);
        return cargoDto(cargo);
    }

    @Override
    public void cargoDelete(Integer cargoId) {
        cargoRepository.deleteById(cargoId);
    }

    @Override
    public CargoDto cargoFindById(Integer cargoId) {
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow();
        return cargoDto(cargo);
    }

    @Override
    public List<CargoDto> cargosFindByOrderForCargoId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        return cargoRepository.findAllByOrderForCargoId(order).stream().map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindByName(String name) {
        return cargoRepository.findAllByCargoName(name).stream().map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindAllByStartCityAndState(String city, String state) {
        return cargoRepository.findAllByStartCityAndStartState(city, state).stream().map(
                cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindAllByStartCityAndStateAndAddress(String city, String state, String address) {
        return cargoRepository.findAllByStartCityAndStartStateAndStartAddress(city, state, address).stream().map(
                cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindNonLoaded() {
        return cargoRepository.findAll().stream().map(cargo -> cargoDto(cargo))
                .filter(dto -> dto.getLoaded().toString().equals("NO")).toList();
    }

    @Override
    public List<CargoDto> cargosFindLoaded() {
        return cargoRepository.findAll().stream().map(cargo -> cargoDto(cargo))
                .filter(dto -> dto.getLoaded().toString().equals("YES")).toList();
    }

    @Override
    public List<CargoDto> cargosFindAllByFinalCityAndState(String city, String state) {
        return cargoRepository.findAllByFinalCityAndFinalState(city, state).stream()
                .map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindAllByFinalCityAndStateAndAddress(String city, String state, String address) {
        return cargoRepository.findAllByFinalCityAndFinalStateAndFinalAddress(city, state, address).stream()
                .map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindNonUnloaded() {
        return cargoRepository.findAll().stream().map(cargo -> cargoDto(cargo))
                .filter(dto -> dto.getUnloaded().toString().equals("NO")).toList();
    }

    @Override
    public List<CargoDto> cargosFindUnloaded() {
        return cargoRepository.findAll().stream().map(cargo -> cargoDto(cargo))
                .filter(dto -> dto.getUnloaded().toString().equals("YES")).toList();
    }

    @Override
    public List<CargoDto> cargosFindEarlierThanExpectedCompletionDateTime(LocalDateTime expectedCompletionDateTime) {
        return cargoRepository.findAllByExpectedCompletionDateTimeLessThanEqual(expectedCompletionDateTime).stream()
                .map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindLaterThanExpectedCompletionDateTime(LocalDateTime expectedCompletionDateTime) {
        return cargoRepository.findAllByExpectedCompletionDateTimeGreaterThanEqual(expectedCompletionDateTime).stream()
                .map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindEarlierThanRealCompletionDateTime(LocalDateTime realCompletionDateTime) {
        return cargoRepository.findAllByRealCompletionDateTimeLessThanEqual(realCompletionDateTime).stream()
                .map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public List<CargoDto> cargosFindLaterThanRealCompletionDateTime(LocalDateTime realCompletionDateTime) {
        return cargoRepository.findAllByRealCompletionDateTimeGreaterThanEqual(realCompletionDateTime).stream()
                .map(cargo -> cargoDto(cargo)).toList();
    }

    private CargoDto cargoDto(Cargo cargo) {
        return CargoMapper.INSTANCE.cargoToCargoDto(cargo);
    }

    @Override
    public CargoDto cargoUpdateLoading(Integer cargoId) {
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow();
        cargo.setLoaded(Loaded.YES);
        cargoRepository.save(cargo);
        return cargoDto(cargo);
    }

    @Override
    public CargoDto cargoUpdateUnloading(Integer cargoId) {
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow();
        cargo.setUnloaded(Unloaded.YES);
        cargoRepository.save(cargo);
        return cargoDto(cargo);
    }
}

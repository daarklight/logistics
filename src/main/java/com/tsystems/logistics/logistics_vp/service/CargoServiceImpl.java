package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.CargoDto;
import com.tsystems.logistics.logistics_vp.code.model.CreateCargoDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByLogisticianDto;
import com.tsystems.logistics.logistics_vp.entity.Cargo;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.entity.Truck;
import com.tsystems.logistics.logistics_vp.enums.*;
import com.tsystems.logistics.logistics_vp.exceptions.custom.*;
import com.tsystems.logistics.logistics_vp.mapper.CargoMapper;
import com.tsystems.logistics.logistics_vp.repository.CargoRepository;
import com.tsystems.logistics.logistics_vp.repository.OrderRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.CargoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
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
        Cargo cargo;
        if (order.getCargos().size() < 2) {
            cargo = Cargo.builder()
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

            Double totalWeightOfAllCargosForOrder = order.getCargos().stream().mapToDouble(elem ->
                    elem.getWeight()).sum() + cargo.getWeight();
            if (totalWeightOfAllCargosForOrder < 22) {
                if (order.getCargos().size() == 0) {
                    cargoRepository.save(cargo);
                    order.setWeight(cargo.getWeight());
                } else if (order.getCargos().size() == 1 && order.getCargos().get(0).getStartAddress().equals(cargo.getStartAddress())
                        && order.getCargos().get(0).getStartCity().equals(cargo.getStartCity())
                        && order.getCargos().get(0).getStartState().equals(cargo.getStartState())) {
                    cargoRepository.save(cargo);
                    order.setWeight(totalWeightOfAllCargosForOrder);
                } else {
                    throw new IncorrectCargoStartPointException("Start point of the second cargo has to be the same " +
                            "as for the first cargo");
                }
            } else {
                throw new TooHeavyCargosException("It is impossible to register the order with total weight of cargos " +
                        "more than 22 tons");
            }
        } else {
            throw new TooManyCargosException("It is impossible to put more than two cargos for one order");
        }
        return cargoDto(cargo);
    }

    @Override
    public CargoDto cargoUpdateByLogistician(Integer cargoId, UpdateCargoByLogisticianDto cargoDto) {
        Cargo cargo = getCargoFromDb(cargoId);
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
        Cargo cargo = getCargoFromDb(cargoId);
        cargo.setLoaded(Loaded.valueOf(cargoDto.getLoaded().toString()));
        cargo.setUnloaded(Unloaded.valueOf(cargoDto.getLoaded().toString()));
        cargoRepository.save(cargo);
        return cargoDto(cargo);
    }

    @Override
    public void cargoDelete(Integer cargoId) {
        getCargoFromDb(cargoId);
        cargoRepository.deleteById(cargoId);
    }

    @Override
    public CargoDto cargoFindById(Integer cargoId) {
        return cargoDto(getCargoFromDb(cargoId));
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
        List<Driver> driversForOrder = cargo.getOrderForCargoId().getDrivers();
        Truck truckForOrder = driversForOrder.get(0).getCurrentTruckNumber();
        if (cargo.getLoaded().equals(Loaded.YES)) {
            cargo.setUnloaded(Unloaded.YES);
            cargo.setRealCompletionDateTime(LocalDateTime.now());

            Order order = cargo.getOrderForCargoId();
            List<Cargo> allCargosForOrder = cargoRepository.findAll().stream()
                    .filter(elem -> elem.getOrderForCargoId().equals(order))
                    .toList();
            List<Cargo> allUnloadedCargosForOrder = allCargosForOrder.stream()
                    .filter(elem -> elem.getUnloaded().equals(Unloaded.YES))
                    .toList();
            if (allUnloadedCargosForOrder.size() == allCargosForOrder.size()) {
                String cargoFinalCity = cargo.getFinalCity();
                String cargoFinalState = cargo.getFinalState();

                order.setStatus(OrderStatus.COMPLETED);
                driversForOrder.stream().forEach(driver -> driver.setBusy(Busy.NO));
                truckForOrder.setBusy(Busy.NO);
                driversForOrder.stream().forEach(driver -> driver.setCurrentCity(cargoFinalCity));
                driversForOrder.stream().forEach(driver -> driver.setCurrentState(cargoFinalState));
                truckForOrder.setCurrentCity(cargoFinalCity);
                truckForOrder.setCurrentState(cargoFinalState);
                log.info("Order status was changed to COMPLETED after the last cargo was unloaded");
            }
            return cargoDto(cargo);
        } else {
            throw new UnloadingNonLoadedCargoException("It's impossible to unload non-loaded cargo");
        }
    }

    @Override
    public Cargo getCargoFromDb(Integer cargoId) {
        return cargoRepository.findById(cargoId).orElseThrow(() ->
                new NoSuchCargoException("This cargo does not exist in database"));
    }

    private CargoDto cargoDto(Cargo cargo) {
        return CargoMapper.INSTANCE.cargoToCargoDto(cargo);
    }
}

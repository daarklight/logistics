package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.CargoDto;
import com.tsystems.logistics.logistics_vp.code.model.CreateCargoDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByDriverDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCargoByLogisticianDto;
import com.tsystems.logistics.logistics_vp.entity.Cargo;
import com.tsystems.logistics.logistics_vp.entity.Driver;
import com.tsystems.logistics.logistics_vp.entity.Order;
import com.tsystems.logistics.logistics_vp.entity.Truck;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.Loaded;
import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import com.tsystems.logistics.logistics_vp.enums.Unloaded;
import com.tsystems.logistics.logistics_vp.exceptions.custom.*;
import com.tsystems.logistics.logistics_vp.mapper.CargoMapper;
import com.tsystems.logistics.logistics_vp.repository.CargoRepository;
import com.tsystems.logistics.logistics_vp.repository.OrderRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.CargoService;
import com.tsystems.logistics.logistics_vp.service.interfaces.GoogleMapsDistanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final OrderRepository orderRepository;
    private final GoogleMapsDistanceService mapsService;

    @Override
    public List<CargoDto> cargosFindAll() {
        return cargoRepository.findAll().stream().map(cargo -> cargoDto(cargo)).toList();
    }

    @Override
    public CargoDto cargoCreate(CreateCargoDto cargoDto) throws IOException, InterruptedException {
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
                    order.setNumberOfCargos(1);
                    order.setWeight(cargo.getWeight());
                    calculateAndUpdateRide(order.getOrderId());
                } else if (order.getCargos().size() == 1 && order.getCargos().get(0).getStartAddress().equals(cargo.getStartAddress())
                        && order.getCargos().get(0).getStartCity().equals(cargo.getStartCity())
                        && order.getCargos().get(0).getStartState().equals(cargo.getStartState())) {
                    cargoRepository.save(cargo);
                    order.setNumberOfCargos(2);
                    order.setWeight(totalWeightOfAllCargosForOrder);
                    calculateAndUpdateRide(order.getOrderId());
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
        Double updateCargoWeight = cargo.getWeight();
        OrderStatus orderStatus = cargo.getOrderForCargoId().getStatus();
        Order order = cargo.getOrderForCargoId();
        Cargo otherCargo;
        Double otherCargoWeight = 0.0;
        if (order.getCargos().size() == 2) {
            otherCargo = order.getCargos().stream().filter(elem -> elem.getCargoId() != cargoId)
                    .toList().get(0);
            otherCargoWeight = otherCargo.getWeight();
        }
        Double totalOrderWeight = updateCargoWeight + otherCargoWeight;
        if (orderStatus == OrderStatus.NEW || orderStatus == OrderStatus.DECLINED_BY_DRIVERS) {
            if (totalOrderWeight < 22) {
                cargo.setCargoName(cargoDto.getCargoName());
                cargo.setWeight(cargoDto.getWeight());
                cargo.setStartCity(cargoDto.getStartCity());
                cargo.setStartState(cargoDto.getStartState());
                cargo.setStartAddress(cargoDto.getStartAddress());
                cargo.setFinalCity(cargoDto.getFinalCity());
                cargo.setFinalState(cargoDto.getFinalState());
                cargo.setFinalAddress(cargoDto.getFinalAddress());
                // Update order total weight as well:
                order.setWeight(totalOrderWeight);
                return cargoDto(cargo);
            } else {
                throw new TooHeavyCargosException("It is impossible to update the cargo, because updated total weight " +
                        "of the order is more than 22 tons");
            }
        } else {
            throw new ImpossibleCargoUpdateException("It is impossible to update the cargo when order status is not " +
                    "equal to NEW or DECLINED_BY_DRIVERS");
        }
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
        Cargo cargo = getCargoFromDb(cargoId);
        Order order = cargo.getOrderForCargoId();
        order.setStatus(OrderStatus.ON_ROAD);
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
                driversForOrder.stream().forEach(driver -> driver.setOrderAcceptance(null));
                driversForOrder.stream().forEach(driver -> driver.setCurrentTruckNumber(null));
                driversForOrder.stream().forEach(driver -> driver.setCurrentOrderId(null));
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

    private String calculateAndUpdateRide(Integer orderId) throws InterruptedException, IOException {
        List<CargoDto> allCargoDtos = cargosFindByOrderForCargoId(orderId);
        String resultResponse = null;
        if (allCargoDtos.size() == 1) {
            CargoDto cargoDto = allCargoDtos.get(0);
            Integer cargoId = cargoDto.getCargoId();
            String startPoint = cargoDto.getStartAddress() + ", " + cargoDto.getStartCity() + ", " + cargoDto.getStartState();
            String endPoint = cargoDto.getFinalAddress() + ", " + cargoDto.getFinalCity() + ", " + cargoDto.getFinalState();
            List<Integer> rideAndDistance = mapsService.calculateRideDurationAndDistance(startPoint, endPoint);
            if (rideAndDistance.get(0) != null) {
                int durationInMinutes = rideAndDistance.get(0) / 60;
                int distanceInKm = rideAndDistance.get(1) / 1000;
                resultResponse = "Duration, minutes: " + durationInMinutes + "; Distance, km: " + distanceInKm;
                // Save in DB:
                Cargo cargo = cargoRepository.findById(cargoId).orElseThrow();
                cargo.setWaypointIndex(0);
                cargo.setRideDistanceFromStartPoint(distanceInKm);
                cargo.setRideDistanceFromPreviousPoint(distanceInKm);
                cargo.setRideDurationFromStartPoint(durationInMinutes);
                cargo.setRideDurationFromPreviousPoint(durationInMinutes);
                cargoRepository.save(cargo);
            } else {
                throw new GoogleMapsIncorrectDataException("Incorrect input data for Google Maps Service");
            }
        } else if (allCargoDtos.size() == 2) {
            CargoDto firstCargoDto = allCargoDtos.get(0);
            CargoDto secondCargoDto = allCargoDtos.get(1);
            Integer firstCargoId = firstCargoDto.getCargoId();
            Integer secondCargoId = secondCargoDto.getCargoId();
            String startPoint = firstCargoDto.getStartAddress() + ", " + firstCargoDto.getStartCity() + ", " +
                    firstCargoDto.getStartState();
            String firstEndPoint = firstCargoDto.getFinalAddress() + ", " + firstCargoDto.getFinalCity() + ", " +
                    firstCargoDto.getFinalState();
            String secondEndPoint = secondCargoDto.getFinalAddress() + ", " + secondCargoDto.getFinalCity() + ", " +
                    secondCargoDto.getFinalState();
            List<List<Integer>> rideAndDistance = mapsService.getRouteMatrixResults(startPoint, firstEndPoint, secondEndPoint);
            if (rideAndDistance.get(0).get(0) != null) {
                int firstWaypointIndex = rideAndDistance.get(0).get(0);
                int secondWaypointIndex = rideAndDistance.get(0).get(1);
                int firstDistanceInKmFromStartPoint = rideAndDistance.get(1).get(0) / 1000;
                int secondDistanceInKmFromStartPoint = rideAndDistance.get(1).get(1) / 1000;
                int firstDurationInMinutesFromStartPoint = rideAndDistance.get(2).get(0) / 60;
                int secondDurationInMinutesFromStartPoint = rideAndDistance.get(2).get(1) / 60;
                resultResponse = "For first cargo: " + "Waypoint index: " + firstWaypointIndex + "; Duration, minutes: " +
                        firstDurationInMinutesFromStartPoint + "; Distance, km: " + firstDistanceInKmFromStartPoint + "\n" +
                        "For second cargo: " + "Waypoint index: " + secondWaypointIndex + "; Duration, minutes: " +
                        secondDurationInMinutesFromStartPoint + "; Distance, km: " + secondDistanceInKmFromStartPoint;
                int rideDistanceFromPreviousPointForFirstCargo = (firstWaypointIndex == 0)
                        ? firstDistanceInKmFromStartPoint : Math.abs(secondDistanceInKmFromStartPoint - firstDistanceInKmFromStartPoint);
                int rideDurationFromPreviousPointForFirstCargo = (firstWaypointIndex == 0)
                        ? firstDurationInMinutesFromStartPoint : Math.abs(secondDurationInMinutesFromStartPoint -
                        firstDurationInMinutesFromStartPoint);
                int rideDistanceFromPreviousPointForSecondCargo = (secondWaypointIndex == 0)
                        ? secondDistanceInKmFromStartPoint : Math.abs(secondDistanceInKmFromStartPoint - firstDistanceInKmFromStartPoint);
                int rideDurationFromPreviousPointForSecondCargo = (secondWaypointIndex == 0)
                        ? secondDurationInMinutesFromStartPoint : Math.abs(secondDurationInMinutesFromStartPoint -
                        firstDurationInMinutesFromStartPoint);
                // Save in DB:
                Cargo firstCargo = cargoRepository.findById(firstCargoId).orElseThrow();
                firstCargo.setWaypointIndex(firstWaypointIndex);
                firstCargo.setRideDistanceFromStartPoint(firstDistanceInKmFromStartPoint);
                firstCargo.setRideDistanceFromPreviousPoint(rideDistanceFromPreviousPointForFirstCargo);
                firstCargo.setRideDurationFromStartPoint(firstDurationInMinutesFromStartPoint);
                firstCargo.setRideDurationFromPreviousPoint(rideDurationFromPreviousPointForFirstCargo);
                cargoRepository.save(firstCargo);
                Cargo secondCargo = cargoRepository.findById(secondCargoId).orElseThrow();
                secondCargo.setWaypointIndex(secondWaypointIndex);
                secondCargo.setRideDistanceFromStartPoint(secondDistanceInKmFromStartPoint);
                secondCargo.setRideDistanceFromPreviousPoint(rideDistanceFromPreviousPointForSecondCargo);
                secondCargo.setRideDurationFromStartPoint(secondDurationInMinutesFromStartPoint);
                secondCargo.setRideDurationFromPreviousPoint(rideDurationFromPreviousPointForSecondCargo);
                cargoRepository.save(secondCargo);
            } else {
                throw new GoogleMapsIncorrectDataException("Incorrect input data for Google Maps Service");
            }
        }
        log.info("Results of Google Maps Service calculations: " + resultResponse);
        return resultResponse;
    }

    private CargoDto cargoDto(Cargo cargo) {
        return CargoMapper.INSTANCE.cargoToCargoDto(cargo);
    }
}

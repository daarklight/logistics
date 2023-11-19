package com.tsystems.logistics.logistics_vp.repository.customized;

import com.tsystems.logistics.logistics_vp.entity.Cargo;
import com.tsystems.logistics.logistics_vp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomizedCargoRepository extends JpaRepository <Cargo, Integer> {
    List<Cargo> findAllByOrderForCargoId(Order order);
    List<Cargo> findAllByCargoName(String cargoName);
    List<Cargo> findAllByStartCityAndStartState(String city, String state);
    List<Cargo> findAllByStartCityAndStartStateAndStartAddress(String city, String state, String address);
    List<Cargo> findAllByFinalCityAndFinalState(String city, String state);
    List<Cargo> findAllByFinalCityAndFinalStateAndFinalAddress(String city, String state, String address);
    List<Cargo> findAllByExpectedCompletionDateTimeLessThanEqual(LocalDateTime expectedCompletionDateTime);
    List<Cargo> findAllByExpectedCompletionDateTimeGreaterThanEqual(LocalDateTime expectedCompletionDateTime);
    List<Cargo> findAllByRealCompletionDateTimeLessThanEqual(LocalDateTime realCompletionDateTime);
    List<Cargo> findAllByRealCompletionDateTimeGreaterThanEqual(LocalDateTime realCompletionDateTime);
}

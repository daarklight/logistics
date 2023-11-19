package com.tsystems.logistics.logistics_vp.validator;

import com.tsystems.logistics.logistics_vp.code.model.CargoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CargoValidator extends ValidatorPatterns implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        CargoDto cargoDto = (CargoDto) object;

        Integer orderForCargoId = cargoDto.getOrderForCargoId();
        String cargoName = cargoDto.getCargoName();
        Double weight = cargoDto.getWeight();
        String startCity = cargoDto.getStartCity();
        String startState = cargoDto.getStartState();
        String startAddress = cargoDto.getStartAddress();
        String finalCity = cargoDto.getFinalCity();
        String finalState = cargoDto.getFinalState();
        String finalAddress = cargoDto.getFinalAddress();

        if (orderForCargoId == null) {
            errors.rejectValue("driverAuthenticationId", "INCORRECT_ORDER_FOR_CARGO_ID_PATTERN",
                    "Order for Cargo id should be positive integer number");
        } else {
            if (orderForCargoId.toString().length() > 6) {
                errors.rejectValue("driverAuthenticationId", "INCORRECT_ORDER_FOR_CARGO_ID",
                        "Order for Cargo id should not exceed 6 digits");
            }
        }
        if (!cargoName.matches(NAME_SURNAME_PATTERN)) {
            errors.rejectValue("cargoName", "INCORRECT_CARGO_NAME_PATTERN",
                    "Cargo name should consist of letters and can contain backspaces and/or digits");
        }
        if (cargoName.isEmpty() || cargoName.length() > 40) {
            errors.rejectValue("cargoName", "INCORRECT_CARGO_NAME",
                    "Cargo name should not be blank and should not exceed 40 symbols");
        }
        if (weight == null) {
            errors.rejectValue("weight", "INCORRECT_WEIGHT_PATTERN",
                    "Cargo weight should be decimal number");
        } else {
            if (weight > 30.0) {
                errors.rejectValue("weight", "INCORRECT_WEIGHT",
                        "Cargo weight should not exceed 30.0 tons");
            }
        }
        if (!startCity.matches(CITY_AND_ADDRESS_PATTERN)) {
            errors.rejectValue("startCity", "INCORRECT_START_CITY_PATTERN",
                    "Start city should consist of letters and can contain backspaces and/or digits");
        }
        if (startCity.isEmpty() || startCity.length() > 30) {
            errors.rejectValue("startCity", "INCORRECT_START_CITY",
                    "Start city should be non-blank and should not exceed 30 symbols");
        }
        if (!startState.matches(STATE_PATTERN)) {
            errors.rejectValue("startState", "INCORRECT_START_STATE_PATTERN",
                    "Start state should consist of letters and can contain backspaces");
        }
        if (startState.isEmpty() || startState.length() > 30) {
            errors.rejectValue("startState", "INCORRECT_START_STATE",
                    "Start state should be non-blank and should not exceed 30 symbols");
        }
        if (!startAddress.matches(CITY_AND_ADDRESS_PATTERN)) {
            errors.rejectValue("startAddress", "INCORRECT_START_ADDRESS_PATTERN",
                    "Start address should consist of letters and can contain backspaces and/or digits");
        }
        if (startAddress.isEmpty() || startCity.length() > 50) {
            errors.rejectValue("startAddress", "INCORRECT_START_ADDRESS",
                    "Start address should be non-blank and should not exceed 50 symbols");
        }
        if (!finalCity.matches(CITY_AND_ADDRESS_PATTERN)) {
            errors.rejectValue("finalCity", "INCORRECT_FINAL_CITY_PATTERN",
                    "Final city should consist of letters and can contain backspaces and/or digits");
        }
        if (finalCity.isEmpty() || finalCity.length() > 30) {
            errors.rejectValue("finalCity", "INCORRECT_FINAL_CITY",
                    "Final city should be non-blank and should not exceed 30 symbols");
        }
        if (!finalState.matches(STATE_PATTERN)) {
            errors.rejectValue("finalState", "INCORRECT_FINAL_STATE_PATTERN",
                    "Final state should consist of letters and can contain backspaces");
        }
        if (finalState.isEmpty() || finalState.length() > 30) {
            errors.rejectValue("finalState", "INCORRECT_FINAL_STATE",
                    "Final state should be non-blank and should not exceed 30 symbols");
        }
        if (!finalAddress.matches(CITY_AND_ADDRESS_PATTERN)) {
            errors.rejectValue("finalAddress", "INCORRECT_FINAL_ADDRESS_PATTERN",
                    "Final address should consist of letters and can contain backspaces and/or digits");
        }
        if (finalAddress.isEmpty() || finalCity.length() > 50) {
            errors.rejectValue("finalAddress", "INCORRECT_FINAL_ADDRESS",
                    "Final address should be non-blank and should not exceed 50 symbols");
        }
    }
}

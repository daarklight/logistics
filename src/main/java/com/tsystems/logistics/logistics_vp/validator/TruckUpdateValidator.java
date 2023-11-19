package com.tsystems.logistics.logistics_vp.validator;

import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TruckUpdateValidator extends ValidatorPatterns implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        TruckDto truckDto = (TruckDto) object;

        String model = truckDto.getModel();
        Integer totalOperatingTime = truckDto.getTotalOperatingTime();
        String currentCity = truckDto.getCurrentCity();
        String currentState = truckDto.getCurrentState();

        if (model.isEmpty() || model.length() > 30) {
            errors.rejectValue("model", "INCORRECT_MODEL",
                    "Model should be non-blank and should not exceed 30 symbols");
        }
        if (truckDto.getCapacity() == null) {
            errors.rejectValue("capacity", "INCORRECT_CAPACITY_PATTERN",
                    "Capacity should be decimal number");
        } else {
            if (truckDto.getCapacity() < 1.0 || truckDto.getCapacity() > 30.0) {
                errors.rejectValue("capacity", "INCORRECT_CAPACITY",
                        "Capacity should be between 1.0 and 30.0 tons");
            }
        }
        if (totalOperatingTime == null) {
            errors.rejectValue("totalOperatingTime", "INCORRECT_OPERATING_TIME_PATTERN",
                    "Total operating time should be positive integer number");
        } else {
            if (totalOperatingTime < 0 || totalOperatingTime > 30) {
                errors.rejectValue("totalOperatingTime", "INCORRECT_OPERATING_TIME",
                        "Total operating time should be between 0 and 30 years");
            }
        }
        if (!currentCity.matches(STATE_PATTERN)) {
            errors.rejectValue("currentCity", "INCORRECT_CURRENT_CITY_PATTERN",
                    "City should consist of letters and can contain backspaces and/or digits");
        }
        if (currentCity.isEmpty() || currentCity.length() > 30) {
            errors.rejectValue("currentCity", "INCORRECT_CURRENT_CITY",
                    "City should be non-blank and should not exceed 30 symbols");
        }
        if (!currentState.matches(STATE_PATTERN)) {
            errors.rejectValue("currentState", "INCORRECT_CURRENT_STATE_PATTERN",
                    "State should consist of letters and can contain backspaces");
        }
        if (currentState.isEmpty() || currentState.length() > 30) {
            errors.rejectValue("currentState", "INCORRECT_CURRENT_STATE_VALUE",
                    "State should be non-blank and should not exceed 30 symbols");
        }
    }
}

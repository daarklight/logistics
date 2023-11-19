package com.tsystems.logistics.logistics_vp.validator;

import com.tsystems.logistics.logistics_vp.code.model.CreateDriverDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DriverValidator extends ValidatorPatterns implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        CreateDriverDto driverDto = (CreateDriverDto) object;

        Integer driverAuthenticationId = driverDto.getDriverAuthenticationId();
        String name = driverDto.getName();
        String surname = driverDto.getSurname();
        String phone = driverDto.getPhone();
        String email = driverDto.getEmail();
        Integer workExperience = driverDto.getWorkExperience();
        Integer workingHoursInCurrentMonth = driverDto.getWorkingHoursInCurrentMonth();
        String currentCity = driverDto.getCurrentCity();
        String currentState = driverDto.getCurrentState();

        if (driverAuthenticationId == null) {
            errors.rejectValue("driverAuthenticationId", "INCORRECT_AUTHENTICATION_ID_PATTERN",
                    "Driver authentication id should be positive integer number");
        } else {
            if (driverAuthenticationId.toString().length() > 7) {
                errors.rejectValue("driverAuthenticationId", "INCORRECT_AUTHENTICATION_ID",
                        "Driver authentication id should not exceed 6 digits");
            }
        }
        if (!name.matches(NAME_SURNAME_PATTERN)) {
            errors.rejectValue("name", "INCORRECT_NAME_PATTERN",
                    "Name should consist of letters and can contain backspaces and/or digits");
        }
        if (name.isEmpty() || name.length() > 35) {
            errors.rejectValue("name", "INCORRECT_NAME",
                    "Name should not be blank and should not exceed 35 symbols");
        }
        if (!surname.matches(NAME_SURNAME_PATTERN)) {
            errors.rejectValue("surname", "INCORRECT_SURNAME_PATTERN",
                    "Surname should consist of letters and can contain backspaces and/or digits");
        }
        if (surname.isEmpty() || surname.length() > 35) {
            errors.rejectValue("surname", "INCORRECT_SURNAME",
                    "Surname should not be blank and should not exceed 35 symbols");
        }
        if (!phone.matches(PHONE_PATTERN)) {
            errors.rejectValue("phone", "INCORRECT_PHONE_PATTERN",
                    "Phone number cannot consist of previously defined symbols");
        }
        if (phone.isEmpty() || phone.length() > 19) {
            errors.rejectValue("phone", "INCORRECT_PHONE",
                    "Phone number should not be blank and should not exceed 19 symbols");
        }
        if (!email.matches(EMAIL_PATTERN)) {
            errors.rejectValue("email", "INCORRECT_EMAIL_PATTERN",
                    "Email can contain only letters, digits, underscores, dashes, dots, @");
        }
        if (workExperience == null) {
            errors.rejectValue("workExperience", "INCORRECT_WORK_EXPERIENCE_PATTERN",
                    "Work experience should be positive integer number");
        } else {
            if (workExperience < 1 || workExperience > 57) {
                errors.rejectValue("workExperience", "INCORRECT_WORK_EXPERIENCE",
                        "Work experience should be between 1 and 57 years year");
            }
        }
        if (workingHoursInCurrentMonth == null) {
            errors.rejectValue("workingHoursInCurrentMonth", "INCORRECT_WORKING_HOURS_PATTERN",
                    "Working hours in current month should be positive integer number");
        } else {
            if (workingHoursInCurrentMonth.toString().length() > 7) {
                errors.rejectValue("workingHoursInCurrentMonth", "INCORRECT_WORKING_HOURS",
                        "Working hours in current month should be between 0 and 176 hours");
            }
        }
        if (!currentCity.matches(CITY_AND_ADDRESS_PATTERN)) {
            errors.rejectValue("currentCity", "INCORRECT_CURRENT_CITY_PATTERN",
                    "City should consist of letters and can contain backspaces and/or digits");
        }
        if (currentCity.isEmpty() || currentCity.length() > 30) {
            errors.rejectValue("currentCity", "INCORRECT_CURRENT_CITY",
                    "Current city should be non-blank and should not exceed 30 symbols");
        }
        if (!currentState.matches(STATE_PATTERN)) {
            errors.rejectValue("currentState", "INCORRECT_CURRENT_STATE_PATTERN",
                    "State should consist of letters and can contain backspaces");
        }
        if (currentState.isEmpty() || currentState.length() > 30) {
            errors.rejectValue("currentState", "INCORRECT_CURRENT_STATE",
                    "Current state should be non-blank and should not exceed 30 symbols");
        }
    }
}

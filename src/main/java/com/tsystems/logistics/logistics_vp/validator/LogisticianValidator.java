package com.tsystems.logistics.logistics_vp.validator;

import com.tsystems.logistics.logistics_vp.code.model.CreateLogisticianDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LogisticianValidator extends ValidatorPatterns implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        CreateLogisticianDto logisticianDto = (CreateLogisticianDto) object;

        Integer logisticianAuthenticationId = logisticianDto.getLogisticianAuthenticationId();
        String name = logisticianDto.getName();
        String surname = logisticianDto.getSurname();

        if (logisticianAuthenticationId == null) {
            errors.rejectValue("logisticianAuthenticationId", "INCORRECT_LOGISTICIAN_AUTHENTICATION_ID_PATTERN",
                    "Logistician authentication id should be positive integer number");
        } else {
            if (logisticianAuthenticationId.toString().length() > 7) {
                errors.rejectValue("logisticianAuthenticationId", "INCORRECT_LOGISTICIAN_AUTHENTICATION_ID",
                        "Logistician authentication id should not exceed 7 digits");
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
    }
}

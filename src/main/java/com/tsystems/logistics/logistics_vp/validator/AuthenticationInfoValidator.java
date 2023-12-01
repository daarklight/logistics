package com.tsystems.logistics.logistics_vp.validator;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AuthenticationInfoValidator extends ValidatorPatterns implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        AuthenticationInfoDto authenticationInfoDto = (AuthenticationInfoDto) object;

        Integer id = authenticationInfoDto.getId();
        String username = authenticationInfoDto.getUsername();

        if (id == null) {
            errors.rejectValue("id", "INCORRECT_ID_PATTERN",
                    "Authentication Info id should be positive integer number");
        } else {
            if (id.toString().length() > 6) {
                errors.rejectValue("id", "INCORRECT_ID",
                        "Authentication Info id should not exceed 6 digits");
            }
        }
        if (!username.toString().matches(USERNAME_PATTERN)) {
            errors.rejectValue("id", "INCORRECT_USERNAME_PATTERN",
                    "Authentication Info username can contain only letters, digits and underscores");
        }
        if (username.toString().length() > 30) {
            errors.rejectValue("id", "INCORRECT_LOGIN",
                    "Authentication Info username should not exceed 30 symbols");
        }
    }
}

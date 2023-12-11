package com.tsystems.logistics.logistics_vp.validator;

import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator extends ValidatorPatterns implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        CreateCustomerDto customerDto = (CreateCustomerDto) object;

        Integer customerAuthenticationId = customerDto.getCustomerAuthenticationId();
        String customerName = customerDto.getCustomerName();
        String phone = customerDto.getPhone();
        String email = customerDto.getEmail();

        if (customerAuthenticationId == null) {
            errors.rejectValue("customerAuthenticationId", "INCORRECT_CUSTOMER_AUTHENTICATION_ID_PATTERN",
                    "Customer authentication id should be positive integer number");
        } else {
            if (customerAuthenticationId.toString().length() > 7) {
                errors.rejectValue("customerAuthenticationId", "INCORRECT_CUSTOMER_AUTHENTICATION_ID",
                        "Customer authentication id should not exceed 7 digits");
            }
        }
        if (!customerName.matches(NAME_SURNAME_PATTERN)) {
            errors.rejectValue("customerName", "INCORRECT_CUSTOMER_NAME_PATTERN",
                    "Customer name should consist of letters and can contain backspaces and/or digits");
        }
        if (customerName.isEmpty() || customerName.length() > 50) {
            errors.rejectValue("customerName", "INCORRECT_CUSTOMER_NAME",
                    "Customer name should not be blank and should not exceed 50 symbols");
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
        if (email.isEmpty() || email.length() > 70) {
            errors.rejectValue("email", "INCORRECT_EMAIL",
                    "Email  should not be blank and should not exceed 70 symbols");
        }
    }
}

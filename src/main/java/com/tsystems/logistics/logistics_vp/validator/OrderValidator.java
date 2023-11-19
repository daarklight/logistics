package com.tsystems.logistics.logistics_vp.validator;

import com.tsystems.logistics.logistics_vp.code.model.OrderDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class OrderValidator extends ValidatorPatterns implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        OrderDto orderDto = (OrderDto) object;

        Integer orderCustomerId = orderDto.getOrderCustomerId();
        String category = orderDto.getCategory();
        Double weight = orderDto.getWeight();
        LocalDateTime limitDateTime = orderDto.getLimitDateTime();

        if (orderCustomerId == null) {
            errors.rejectValue("orderCustomerId", "INCORRECT_ORDER_CUSTOMER_ID_PATTERN",
                    "Order Customer id should be positive integer number");
        } else {
            if (orderCustomerId.toString().length() > 7) {
                errors.rejectValue("orderCustomerId", "INCORRECT_ORDER_CUSTOMER_ID",
                        "Order Customer id should not exceed 7 digits");
            }
        }
        if (!category.matches(NAME_SURNAME_PATTERN)) {
            errors.rejectValue("category", "INCORRECT_CATEGORY_PATTERN",
                    "Category should consist of letters and can contain backspaces and/or digits");
        }
        if (category.isEmpty() || category.length() > 40) {
            errors.rejectValue("category", "INCORRECT_CATEGORY",
                    "Category should not be blank and should not exceed 40 symbols");
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
        if (!limitDateTime.toString().matches(LOCAL_DATE_TIME_PATTERN)) {
            errors.rejectValue("limitDateTime", "INCORRECT_LIMIT_DATE_TIME_PATTERN",
                    "Limit date-time cannot consist of previously defined symbols");
        }
    }
}

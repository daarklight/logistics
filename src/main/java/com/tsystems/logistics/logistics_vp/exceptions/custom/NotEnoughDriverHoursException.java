package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NotEnoughDriverHoursException extends RuntimeException {
    private String message;

    public NotEnoughDriverHoursException(String message) {
        this.message = message;
    }
}

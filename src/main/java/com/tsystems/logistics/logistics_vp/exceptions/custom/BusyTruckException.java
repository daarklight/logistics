package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class BusyTruckException extends RuntimeException {
    private String message;

    public BusyTruckException(String message) {
        this.message = message;
    }
}

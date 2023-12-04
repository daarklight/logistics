package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoSuchTruckException extends RuntimeException {
    private String message;

    public NoSuchTruckException(String message) {
        this.message = message;
    }
}

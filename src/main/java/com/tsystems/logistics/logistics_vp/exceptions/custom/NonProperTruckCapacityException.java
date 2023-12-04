package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NonProperTruckCapacityException extends RuntimeException{
    private String message;

    public NonProperTruckCapacityException(String message) {
        this.message = message;
    }
}

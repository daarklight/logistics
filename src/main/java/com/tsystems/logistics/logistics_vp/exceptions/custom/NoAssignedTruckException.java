package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoAssignedTruckException extends RuntimeException {
    private String message;

    public NoAssignedTruckException(String message) {
        this.message = message;
    }
}

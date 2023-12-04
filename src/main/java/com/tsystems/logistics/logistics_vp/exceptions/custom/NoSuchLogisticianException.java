package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoSuchLogisticianException extends RuntimeException {
    private String message;

    public NoSuchLogisticianException(String message) {
        this.message = message;
    }
}

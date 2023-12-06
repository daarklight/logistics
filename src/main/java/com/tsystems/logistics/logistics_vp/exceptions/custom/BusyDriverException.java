package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class BusyDriverException extends RuntimeException {
    private String message;

    public BusyDriverException(String message) {
        this.message = message;
    }
}

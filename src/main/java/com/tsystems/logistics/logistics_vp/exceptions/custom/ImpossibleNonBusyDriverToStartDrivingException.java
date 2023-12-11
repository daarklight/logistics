package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class ImpossibleNonBusyDriverToStartDrivingException extends RuntimeException {
    private String message;

    public ImpossibleNonBusyDriverToStartDrivingException(String message) {
        this.message = message;
    }
}

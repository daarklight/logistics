package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class ImpossibleBusyTruckDeleteException extends RuntimeException {
    private String message;

    public ImpossibleBusyTruckDeleteException(String message) {
        this.message = message;
    }
}

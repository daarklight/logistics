package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class ImpossibleBusyDriverDeleteException extends RuntimeException {
    private String message;

    public ImpossibleBusyDriverDeleteException(String message) {
        this.message = message;
    }
}

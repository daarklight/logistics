package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class UnloadingNonLoadedCargoException extends RuntimeException {
    private String message;

    public UnloadingNonLoadedCargoException(String message) {
        this.message = message;
    }
}

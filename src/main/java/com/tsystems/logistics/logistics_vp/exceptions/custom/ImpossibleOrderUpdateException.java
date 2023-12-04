package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class ImpossibleOrderUpdateException extends RuntimeException {
    private String message;

    public ImpossibleOrderUpdateException(String message) {
        this.message = message;
    }
}

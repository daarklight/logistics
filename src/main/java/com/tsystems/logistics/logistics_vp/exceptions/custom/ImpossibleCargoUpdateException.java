package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class ImpossibleCargoUpdateException extends RuntimeException {
    private String message;

    public ImpossibleCargoUpdateException(String message) {
        this.message = message;
    }
}

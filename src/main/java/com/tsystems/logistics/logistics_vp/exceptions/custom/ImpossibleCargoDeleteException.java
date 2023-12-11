package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class ImpossibleCargoDeleteException extends RuntimeException {
    private String message;

    public ImpossibleCargoDeleteException(String message) {
        this.message = message;
    }
}

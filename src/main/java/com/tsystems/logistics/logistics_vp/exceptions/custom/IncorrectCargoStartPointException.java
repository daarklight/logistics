package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class IncorrectCargoStartPointException extends RuntimeException{
    private String message;

    public IncorrectCargoStartPointException(String message) {
        this.message = message;
    }
}

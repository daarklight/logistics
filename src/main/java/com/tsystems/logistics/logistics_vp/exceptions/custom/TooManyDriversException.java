package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class TooManyDriversException extends RuntimeException{
    private String message;

    public TooManyDriversException(String message) {
        this.message = message;
    }
}

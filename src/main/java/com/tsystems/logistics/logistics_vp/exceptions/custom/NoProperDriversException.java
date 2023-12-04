package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoProperDriversException extends RuntimeException {
    private String message;

    public NoProperDriversException(String message) {
        this.message = message;
    }
}

package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoSuchOrderException extends RuntimeException {
    private String message;

    public NoSuchOrderException(String message) {
        this.message = message;
    }
}

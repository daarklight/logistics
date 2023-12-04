package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoSuchCargoException extends RuntimeException {
    private String message;

    public NoSuchCargoException(String message) {
        this.message = message;
    }
}

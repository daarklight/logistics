package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class NoSuchDriverException extends RuntimeException {
    private String message;

    public NoSuchDriverException(String message) {
        this.message = message;
    }
}

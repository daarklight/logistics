package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoProperTrucksException extends RuntimeException {
    private String message;

    public NoProperTrucksException(String message) {
        this.message = message;
    }
}

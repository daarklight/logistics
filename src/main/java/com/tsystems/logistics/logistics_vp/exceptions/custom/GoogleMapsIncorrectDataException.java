package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class GoogleMapsIncorrectDataException extends RuntimeException{
    private String message;

    public GoogleMapsIncorrectDataException(String message) {
        this.message = message;
    }
}

package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class GoogleMapsServiceException extends RuntimeException{
    private String message;

    public GoogleMapsServiceException(String message) {
        this.message = message;
    }
}

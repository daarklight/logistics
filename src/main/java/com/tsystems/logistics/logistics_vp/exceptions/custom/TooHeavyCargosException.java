package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class TooHeavyCargosException extends RuntimeException{
    private String message;

    public TooHeavyCargosException(String message) {
        this.message = message;
    }
}

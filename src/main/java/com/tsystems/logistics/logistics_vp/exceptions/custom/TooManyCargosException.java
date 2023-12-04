package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class TooManyCargosException extends RuntimeException{
    private String message;

    public TooManyCargosException(String message) {
        this.message = message;
    }
}

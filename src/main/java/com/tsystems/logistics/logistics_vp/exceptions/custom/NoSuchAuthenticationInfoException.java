package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoSuchAuthenticationInfoException extends RuntimeException {
    private String message;

    public NoSuchAuthenticationInfoException(String message) {
        this.message = message;
    }
}

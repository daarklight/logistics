package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class NoSuchCustomerException extends RuntimeException {
    private String message;

    public NoSuchCustomerException(String message) {
        this.message = message;
    }
}

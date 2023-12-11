package com.tsystems.logistics.logistics_vp.exceptions.custom;

import lombok.Getter;

@Getter
public class ImpossibleAssignTruckWhenOtherTruckAssignedException extends RuntimeException {
    private String message;

    public ImpossibleAssignTruckWhenOtherTruckAssignedException(String message) {
        this.message = message;
    }
}

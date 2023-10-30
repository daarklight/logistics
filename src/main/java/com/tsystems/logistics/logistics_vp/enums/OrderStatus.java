package com.tsystems.logistics.logistics_vp.enums;

public enum OrderStatus {

    NEW("NEW"),
    EXPECT_DRIVERS_CONFIRMATION("EXPECT_DRIVERS_CONFIRMATION"),
    CONFIRMED("CONFIRMED"),
    DECLINED_BY_DRIVERS("DECLINED_BY_DRIVERS"),
    ON_ROAD("ON_ROAD"),
    COMPLETED("COMPLETED");

    private String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return orderStatus;
    }
}

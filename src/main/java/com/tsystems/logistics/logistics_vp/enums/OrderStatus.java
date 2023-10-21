package com.tsystems.logistics.logistics_vp.enums;

public enum OrderStatus {

    NEW("new"),
    EXPECT_DRIVERS_CONFIRMATION("expect driver(s) confirmation"),
    CONFIRMED("confirmed"),
    DECLINED_BY_DRIVERS("declined by driver(s)"),
    ON_ROAD("on road"),
    COMPLETED("completed");

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

package com.tsystems.logistics.logistics_vp.enums;

public enum OrderAcceptance {
    YES("YES"),
    NO("NO");

    private String orderAcceptance;

    OrderAcceptance(String orderAcceptance) {
        this.orderAcceptance = orderAcceptance;
    }

    public String getOrderAcceptance() {
        return orderAcceptance;
    }

    public void setOrderAcceptance(String orderAcceptance) {
        this.orderAcceptance = orderAcceptance;
    }

    @Override
    public String toString() {
        return orderAcceptance;
    }
}

package com.tsystems.logistics.logistics_vp.enums;

public enum DriverStatus {
    REST("REST"),
    DRIVING("DRIVING");

    private String driverStatus;

    DriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    @Override
    public String toString() {
        return driverStatus;
    }
}

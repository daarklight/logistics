package com.tsystems.logistics.logistics_vp.enums;

public enum Role {
    ADMIN("ADMIN"),
    LOGISTICIAN("LOGISTICIAN"),
    DRIVER("DRIVER"),
    CUSTOMER("CUSTOMER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}

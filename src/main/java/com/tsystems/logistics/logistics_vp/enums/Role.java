package com.tsystems.logistics.logistics_vp.enums;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_LOGISTICIAN("ROLE_LOGISTICIAN"),
    ROLE_DRIVER("ROLE_DRIVER"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");

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

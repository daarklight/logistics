package com.tsystems.logistics.logistics_vp.enums;

public enum TechnicalCondition {
    OK("OK"),
    NOK("NOK");

    TechnicalCondition(String technicalCondition) {
        this.technicalCondition = technicalCondition;
    }

    private String technicalCondition;

    public String getTechnicalCondition() {
        return technicalCondition;
    }

    public void setTechnicalCondition(String technicalCondition) {
        this.technicalCondition = technicalCondition;
    }

    @Override
    public String toString() {
        return technicalCondition;
    }
}

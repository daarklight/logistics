package com.tsystems.logistics.logistics_vp.enums;

public enum Unloaded {
    YES("YES"),
    NO("NO");

    private String unloaded;

    Unloaded(String unloaded) {
        this.unloaded = unloaded;
    }

    public String getUnloaded() {
        return unloaded;
    }

    public void setUnloaded(String unloaded) {
        this.unloaded = unloaded;
    }

    @Override
    public String toString() {
        return unloaded;
    }
}

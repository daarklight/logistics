package com.tsystems.logistics.logistics_vp.enums;

public enum Busy {
    YES("YES"),
    NO("NO");

    private String busy;

    Busy(String busy) {
        this.busy = busy;
    }

    public String getBusy() {
        return busy;
    }

    public void setBusy(String busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        return busy;
    }
}

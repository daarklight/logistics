package com.tsystems.logistics.logistics_vp.enums;

public enum Loaded {
    YES("yes"),
    NO("no");

    private String loaded;

    Loaded(String loaded) {
        this.loaded = loaded;
    }

    public String getLoaded() {
        return loaded;
    }

    public void setLoaded(String loaded) {
        this.loaded = loaded;
    }

    @Override
    public String toString() {
        return loaded;
    }
}

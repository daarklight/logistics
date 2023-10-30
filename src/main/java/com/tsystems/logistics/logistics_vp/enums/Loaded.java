package com.tsystems.logistics.logistics_vp.enums;

public enum Loaded {
    YES("YES"),
    NO("NO");

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

package com.tsystems.logistics.logistics_vp.enums;

public enum Unloaded {
    YES("yes"),
    NO("no");

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

//    Unloaded(String loaded) {
//        this.loaded = loaded;
//    }
//
//    public String getLoaded() {
//        return loaded;
//    }
//
//    public void setLoaded(String loaded) {
//        this.loaded = loaded;
//    }
//
//    @Override
//    public String toString() {
//        return loaded;
//    }
}

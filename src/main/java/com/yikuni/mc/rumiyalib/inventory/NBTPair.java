package com.yikuni.mc.rumiyalib.inventory;

public class NBTPair {
    private String namespace;
    private String key;
    private String value;

    public NBTPair(String namespace, String key, String value) {
        this.namespace = namespace;
        this.key = key;
        this.value = value;
    }

    public NBTPair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setValue(String value) {


        this.value = value;
    }
}

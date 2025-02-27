package com.ctw.workstation;

public abstract class BuildableObject {
    private int id;

    public BuildableObject(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

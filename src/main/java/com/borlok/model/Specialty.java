package com.borlok.model;

public class Specialty {
    private int id;
    private String name;

    public Specialty() {
        id = 0;
        name = "";
    }

    public Specialty(String name) {
        this();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

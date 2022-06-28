package com.tcscorp.searchablelistviewapp;

import java.io.Serializable;

public class Fruit implements Serializable {
    private final String name;
    private final String description;

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

package it.unipr.ingegneria.entities;

public class Vineyard {
    private String name;

    public Vineyard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Vineyard setName(String name) {
        this.name = name;
        return this;
    }
}
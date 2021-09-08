package com.gmail.pokedex.Model;

import java.io.Serializable;

public class Weakness implements Serializable {
    private String type;
    private Double effective;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getEffective() {
        return effective;
    }

    public void setEffective(Double effective) {
        this.effective = effective;
    }

    public Weakness() {
    }

    public Weakness(String type, Double effective) {
        this.type = type;
        this.effective = effective;
    }
}

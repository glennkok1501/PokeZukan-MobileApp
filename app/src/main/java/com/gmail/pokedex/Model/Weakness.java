package com.gmail.pokedex.Model;

import java.io.Serializable;

public class Weakness implements Serializable {
    private String type;
    private int effective;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    public Weakness() {
    }

    public Weakness(String type, int effective) {
        this.type = type;
        this.effective = effective;
    }
}

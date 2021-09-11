package com.gmail.pokezukan.Model;

import java.io.Serializable;

public class Gender implements Serializable {
    private Double male;
    private Double female;

    public Double getMale() {
        return male;
    }

    public void setMale(Double male) {
        this.male = male;
    }

    public Double getFemale() {
        return female;
    }

    public void setFemale(Double female) {
        this.female = female;
    }

    public Gender() {
    }

    public Gender(Double male, Double female) {
        this.male = male;
        this.female = female;
    }
}

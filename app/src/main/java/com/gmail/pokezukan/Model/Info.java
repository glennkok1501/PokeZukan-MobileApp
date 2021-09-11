package com.gmail.pokezukan.Model;

import java.io.Serializable;
import java.util.List;

public class Info implements Serializable {
    private List<String> type;
    private String species;
    private Double height;
    private Double weight;
    private List<String> abilities;

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public Info() {
    }

    public Info(List<String> type, String species, Double height, Double weight, List<String> abilities) {
        this.type = type;
        this.species = species;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
    }
}

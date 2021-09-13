package com.gmail.pokezukan.Model;

import java.io.Serializable;

public class Ability implements Serializable {
    private String name;
    private String effect;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ability() {
    }

    public Ability(String name, String effect, String description) {
        this.name = name;
        this.effect = effect;
        this.description = description;
    }
}

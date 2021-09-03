package com.gmail.pokedex.Model;

import java.io.Serializable;
import java.util.List;

public class Breeding implements Serializable {
    private List<String> egg_groups;
    private Gender gender;
    private String egg_cycle;

    public List<String> getEgg_groups() {
        return egg_groups;
    }

    public void setEgg_groups(List<String> egg_groups) {
        this.egg_groups = egg_groups;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEgg_cycle() {
        return egg_cycle;
    }

    public void setEgg_cycle(String egg_cycle) {
        this.egg_cycle = egg_cycle;
    }

    public Breeding() {
    }

    public Breeding(List<String> egg_groups, Gender gender, String egg_cycle) {
        this.egg_groups = egg_groups;
        this.gender = gender;
        this.egg_cycle = egg_cycle;
    }
}

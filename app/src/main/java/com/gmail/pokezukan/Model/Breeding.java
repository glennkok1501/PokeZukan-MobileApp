package com.gmail.pokezukan.Model;

import java.io.Serializable;
import java.util.List;

public class Breeding implements Serializable {
    private int base_happiness;
    private List<String> egg_groups;
    private Gender gender;
    private PokemonEggCycle egg_cycle;

    public int getBase_happiness() {
        return base_happiness;
    }

    public void setBase_happiness(int base_happiness) {
        this.base_happiness = base_happiness;
    }

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

    public PokemonEggCycle getEgg_cycle() {
        return egg_cycle;
    }

    public void setEgg_cycle(PokemonEggCycle egg_cycle) {
        this.egg_cycle = egg_cycle;
    }

    public Breeding() {
    }

    public Breeding(int base_happiness, List<String> egg_groups, Gender gender, PokemonEggCycle egg_cycle) {
        this.base_happiness = base_happiness;
        this.egg_groups = egg_groups;
        this.gender = gender;
        this.egg_cycle = egg_cycle;
    }
}

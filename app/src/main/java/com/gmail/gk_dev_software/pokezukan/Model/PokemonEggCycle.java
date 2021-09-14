package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;

public class PokemonEggCycle implements Serializable {

    private int hatch_counter;
    private int steps;

    public int getHatch_counter() {
        return hatch_counter;
    }

    public void setHatch_counter(int hatch_counter) {
        this.hatch_counter = hatch_counter;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public PokemonEggCycle() {
    }

    public PokemonEggCycle(int hatch_counter, int steps) {
        this.hatch_counter = hatch_counter;
        this.steps = steps;
    }
}

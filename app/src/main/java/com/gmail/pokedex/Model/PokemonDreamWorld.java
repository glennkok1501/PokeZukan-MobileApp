package com.gmail.pokedex.Model;

import java.io.Serializable;

public class PokemonDreamWorld implements Serializable {
    private String front_default;

    private String front_female;

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    public String getFront_female() {
        return front_female;
    }

    public void setFront_female(String front_female) {
        this.front_female = front_female;
    }

    public PokemonDreamWorld() {
    }

    public PokemonDreamWorld(String front_default, String front_female) {
        this.front_default = front_default;
        this.front_female = front_female;
    }
}

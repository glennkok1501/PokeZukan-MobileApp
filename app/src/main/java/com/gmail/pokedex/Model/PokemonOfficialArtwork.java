package com.gmail.pokedex.Model;

import java.io.Serializable;

public class PokemonOfficialArtwork implements Serializable {
    private String front_default;

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }

    public PokemonOfficialArtwork() {
    }

    public PokemonOfficialArtwork(String front_default) {
        this.front_default = front_default;
    }
}

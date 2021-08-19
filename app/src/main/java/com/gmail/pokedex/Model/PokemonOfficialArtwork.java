package com.gmail.pokedex.Model;

public class PokemonOfficialArtwork {
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

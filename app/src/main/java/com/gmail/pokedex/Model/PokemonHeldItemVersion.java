package com.gmail.pokedex.Model;

import com.gmail.pokedex.Model.NamedAPIResource;

import java.io.Serializable;

public class PokemonHeldItemVersion implements Serializable {
    private NamedAPIResource version;

    private int rarity;

    public NamedAPIResource getVersion() {
        return version;
    }

    public void setVersion(NamedAPIResource version) {
        this.version = version;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public PokemonHeldItemVersion() {
    }

    public PokemonHeldItemVersion(NamedAPIResource version, int rarity) {
        this.version = version;
        this.rarity = rarity;
    }
}

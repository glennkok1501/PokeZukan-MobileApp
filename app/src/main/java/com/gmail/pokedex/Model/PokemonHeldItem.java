package com.gmail.pokedex.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonHeldItem implements Serializable {
    private NamedAPIResource item;

    private ArrayList<PokemonHeldItemVersion> version_details;

    public NamedAPIResource getItem() {
        return item;
    }

    public void setItem(NamedAPIResource item) {
        this.item = item;
    }

    public ArrayList<PokemonHeldItemVersion> getVersion_details() {
        return version_details;
    }

    public void setVersion_details(ArrayList<PokemonHeldItemVersion> version_details) {
        this.version_details = version_details;
    }

    public PokemonHeldItem() {
    }

    public PokemonHeldItem(NamedAPIResource item, ArrayList<PokemonHeldItemVersion> version_details) {
        this.item = item;
        this.version_details = version_details;
    }
}

package com.gmail.pokedex.Model;

public class PokemonType {
    private int slot;

    private NamedAPIResource type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public NamedAPIResource getType() {
        return type;
    }

    public void setType(NamedAPIResource type) {
        this.type = type;
    }

    public PokemonType() {
    }

    public PokemonType(int slot, NamedAPIResource type) {
        this.slot = slot;
        this.type = type;
    }
}
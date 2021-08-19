package com.gmail.pokedex.Model;

import java.io.Serializable;

public class PokemonAbility implements Serializable {
    private boolean is_hidden;

    private int slot;

    private NamedAPIResource ability;

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public NamedAPIResource getAbility() {
        return ability;
    }

    public void setAbility(NamedAPIResource ability) {
        this.ability = ability;
    }

    public PokemonAbility() {
    }

    public PokemonAbility(boolean is_hidden, int slot, NamedAPIResource ability) {
        this.is_hidden = is_hidden;
        this.slot = slot;
        this.ability = ability;
    }
}

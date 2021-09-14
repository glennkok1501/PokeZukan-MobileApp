package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;

public class PokemonAbility implements Serializable {
    private String name;
    private boolean hidden;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean is_hidden() {
        return hidden;
    }

    public void set_hidden(boolean is_hidden) {
        this.hidden = is_hidden;
    }

    public PokemonAbility() {
    }

    public PokemonAbility(String name, boolean hidden) {
        this.name = name;
        this.hidden = hidden;
    }
}

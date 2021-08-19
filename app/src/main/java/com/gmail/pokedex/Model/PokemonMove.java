package com.gmail.pokedex.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonMove implements Serializable {
    private NamedAPIResource move;

    private ArrayList<PokemonMoveVersion> version_group_details;

    public NamedAPIResource getMove() {
        return move;
    }

    public void setMove(NamedAPIResource move) {
        this.move = move;
    }

    public ArrayList<PokemonMoveVersion> getVersion_group_details() {
        return version_group_details;
    }

    public void setVersion_group_details(ArrayList<PokemonMoveVersion> version_group_details) {
        this.version_group_details = version_group_details;
    }

    public PokemonMove() {
    }

    public PokemonMove(NamedAPIResource move, ArrayList<PokemonMoveVersion> version_group_details) {
        this.move = move;
        this.version_group_details = version_group_details;
    }
}

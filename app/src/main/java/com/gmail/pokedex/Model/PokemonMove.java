package com.gmail.pokedex.Model;

public class PokemonMove {
    private NamedAPIResource move;

    private PokemonMoveVersion version_group_details;

    public NamedAPIResource getMove() {
        return move;
    }

    public void setMove(NamedAPIResource move) {
        this.move = move;
    }

    public PokemonMoveVersion getVersion_group_details() {
        return version_group_details;
    }

    public void setVersion_group_details(PokemonMoveVersion version_group_details) {
        this.version_group_details = version_group_details;
    }

    public PokemonMove() {
    }

    public PokemonMove(NamedAPIResource move, PokemonMoveVersion version_group_details) {
        this.move = move;
        this.version_group_details = version_group_details;
    }
}

package com.gmail.pokedex.Model;

import java.io.Serializable;

public class PokemonOther implements Serializable {
    private PokemonDreamWorld dream_world;

    private PokemonOfficialArtwork official_artwork;

    public PokemonDreamWorld getDream_world() {
        return dream_world;
    }

    public void setDream_world(PokemonDreamWorld dream_world) {
        this.dream_world = dream_world;
    }

    public PokemonOfficialArtwork getOfficial_artwork() {
        return official_artwork;
    }

    public void setOfficial_artwork(PokemonOfficialArtwork official_artwork) {
        this.official_artwork = official_artwork;
    }

    public PokemonOther() {
    }

    public PokemonOther(PokemonDreamWorld dream_world, PokemonOfficialArtwork official_artwork) {
        this.dream_world = dream_world;
        this.official_artwork = official_artwork;
    }
}

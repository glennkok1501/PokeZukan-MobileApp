package com.gmail.pokedex.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class PokemonMainRVModel implements Serializable {

    private int id;

    private String name;

    private String artwork;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public PokemonMainRVModel() {
    }

    public PokemonMainRVModel(int id, String name, String artwork) {
        this.id = id;
        this.name = name;
        this.artwork = artwork;
    }
}

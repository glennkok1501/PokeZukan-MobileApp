package com.gmail.pokedex.Model;

import java.io.Serializable;

public class RawPokemonModel implements Serializable {
    private int id;

    private int dexId;

    private String name;

    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDexId() {
        return dexId;
    }

    public void setDexId(int dexId) {
        this.dexId = dexId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RawPokemonModel() {
    }

    public RawPokemonModel(int dexId, String name, String data) {
        this.dexId = dexId;
        this.name = name;
        this.data = data;
    }

    public RawPokemonModel(int id, int dexId, String name, String data) {
        this.id = id;
        this.dexId = dexId;
        this.name = name;
        this.data = data;
    }
}

package com.gmail.pokedex.Model;

import java.io.Serializable;

public class Sprites implements Serializable {

    private String small;
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        small = small.replace("https://img.pokemondb.net/artwork/", "https://raw.githubusercontent.com/glennkok1501/SimpleDexAPI/main/data/images/").replace(".jpg", ".png");
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {

        large = large.replace("https://img.pokemondb.net/artwork/large/", "https://raw.githubusercontent.com/glennkok1501/SimpleDexAPI/main/data/images/").replace(".jpg", ".png");
        this.large = large;
    }

    public Sprites() {
    }

    public Sprites(String small, String large) {
        this.small = small;
        this.large = large;
    }
}

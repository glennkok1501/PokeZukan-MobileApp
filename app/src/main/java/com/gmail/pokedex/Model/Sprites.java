package com.gmail.pokedex.Model;

import java.io.Serializable;

public class Sprites implements Serializable {

    private String small;
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public Sprites() {
    }

    public Sprites(String small, String large) {
        this.small = small;
        this.large = large;
    }
}

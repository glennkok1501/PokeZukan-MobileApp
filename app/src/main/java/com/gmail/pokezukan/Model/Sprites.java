package com.gmail.pokezukan.Model;

import java.io.Serializable;

public class Sprites implements Serializable {

    private String home;
    private String artwork;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public Sprites() {
    }

    public Sprites(String home, String artwork) {
        this.home = home;
        this.artwork = artwork;
    }
}

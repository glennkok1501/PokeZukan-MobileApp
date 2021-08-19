package com.gmail.pokedex.Model;

import java.io.Serializable;

public class NamedAPIResource implements Serializable {
    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public NamedAPIResource() {
    }

    public NamedAPIResource(String name, String url) {
        this.name = name;
        this.url = url;
    }
}

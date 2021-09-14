package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;

public class PokemonBrief implements Serializable {
    private int id;
    private String name;
    private String icon;
    private String link;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PokemonBrief(int id, String name, String icon, String link) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.link = link;
    }

    public PokemonBrief() {
    }
}

package com.gmail.pokedex.Model;

import java.io.Serializable;

public class AbilityBrief implements Serializable {
    private String name;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AbilityBrief() {
    }

    public AbilityBrief(String name, String link) {
        this.name = name;
        this.link = link;
    }
}

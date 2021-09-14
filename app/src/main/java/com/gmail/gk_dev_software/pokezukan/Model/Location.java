package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;
import java.util.List;

public class Location implements Serializable {
    private List<String> game;
    private List<String> area;

    public List<String> getGame() {
        return game;
    }

    public void setGame(List<String> game) {
        this.game = game;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

    public Location() {
    }

    public Location(List<String> game, List<String> area) {
        this.game = game;
        this.area = area;
    }
}

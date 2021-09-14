package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private int id;
    private String name;
    private String entry;
    private String location;
    private Info info;
    private Training training;
    private Breeding breeding;
    private Stats base_stats;
    private List<Weakness> weaknesses;
    private Sprites sprites;
    private String moves;

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

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Breeding getBreeding() {
        return breeding;
    }

    public void setBreeding(Breeding breeding) {
        this.breeding = breeding;
    }

    public Stats getBase_stats() {
        return base_stats;
    }

    public void setBase_stats(Stats base_stats) {
        this.base_stats = base_stats;
    }

    public List<Weakness> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<Weakness> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    public Pokemon() {
    }

    public Pokemon(int id, String name, String entry, String location, Info info, Training training, Breeding breeding, Stats base_stats, List<Weakness> weaknesses, Sprites sprites, String moves) {
        this.id = id;
        this.name = name;
        this.entry = entry;
        this.location = location;
        this.info = info;
        this.training = training;
        this.breeding = breeding;
        this.base_stats = base_stats;
        this.weaknesses = weaknesses;
        this.sprites = sprites;
        this.moves = moves;
    }
}

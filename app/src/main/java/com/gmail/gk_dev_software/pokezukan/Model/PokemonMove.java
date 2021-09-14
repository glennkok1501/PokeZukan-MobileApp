package com.gmail.gk_dev_software.pokezukan.Model;

public class PokemonMove {
    public static int LEVEL_UP = 0;
    public static int TUTOR = 1;
    public static int MACHINE = 2;
    public static int EGG = 3;

    private String name;
    private int method;
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public void setMethod(String method) {
        switch (method) {
            case "level-up":
                this.method = LEVEL_UP;
                break;
            case "tutor":
                this.method = TUTOR;
                break;
            case "machine":
                this.method = MACHINE;
                break;
            default:
                this.method = EGG;
                break;
        }

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PokemonMove() {
    }

    public PokemonMove(String name, int method, int level) {
        this.name = name;
        this.method = method;
        this.level = level;
    }
}

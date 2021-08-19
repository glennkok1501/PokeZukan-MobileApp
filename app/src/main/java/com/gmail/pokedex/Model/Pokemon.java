package com.gmail.pokedex.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {

    //Order for sorting. Almost national order, except families are grouped together.
    private int order;

    //The identifier for this resource.
    private int id;

    //The name for this resource.
    private String name;

    //The base experience gained for defeating this Pokémon.
    private int base_experience;

    //The height of this Pokémon in decimetres.
    private int height;

    //Set for exactly one Pokémon used as the default for each species.
    private boolean is_default;

    //The weight of this Pokémon in hectograms.
    private int weight;

    private ArrayList<PokemonAbility> abilities;

    private ArrayList<NamedAPIResource> forms;

    private ArrayList<VersionGameIndex> game_indices;

    private ArrayList<PokemonHeldItem> held_items;

    private String location_area_encounters;

    private ArrayList<PokemonMove> moves;

    private PokemonSprites sprites;

    private NamedAPIResource species;

    private ArrayList<PokemonStat> stats;

    private ArrayList<PokemonType> types;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

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

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<NamedAPIResource> getForms() {
        return forms;
    }

    public void setForms(ArrayList<NamedAPIResource> forms) {
        this.forms = forms;
    }

    public ArrayList<VersionGameIndex> getGame_indices() {
        return game_indices;
    }

    public void setGame_indices(ArrayList<VersionGameIndex> game_indices) {
        this.game_indices = game_indices;
    }

    public ArrayList<PokemonHeldItem> getHeld_items() {
        return held_items;
    }

    public void setHeld_items(ArrayList<PokemonHeldItem> held_items) {
        this.held_items = held_items;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public ArrayList<PokemonMove> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<PokemonMove> moves) {
        this.moves = moves;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public NamedAPIResource getSpecies() {
        return species;
    }

    public void setSpecies(NamedAPIResource species) {
        this.species = species;
    }

    public ArrayList<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<PokemonStat> stats) {
        this.stats = stats;
    }

    public ArrayList<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<PokemonType> types) {
        this.types = types;
    }

    public Pokemon() {
    }

    public Pokemon(int order, int id, String name, int base_experience, int height, boolean is_default, int weight, ArrayList<PokemonAbility> abilities, ArrayList<NamedAPIResource> forms, ArrayList<VersionGameIndex> game_indices, ArrayList<PokemonHeldItem> held_items, String location_area_encounters, ArrayList<PokemonMove> moves, PokemonSprites sprites, NamedAPIResource species, ArrayList<PokemonStat> stats, ArrayList<PokemonType> types) {
        this.order = order;
        this.id = id;
        this.name = name;
        this.base_experience = base_experience;
        this.height = height;
        this.is_default = is_default;
        this.weight = weight;
        this.abilities = abilities;
        this.forms = forms;
        this.game_indices = game_indices;
        this.held_items = held_items;
        this.location_area_encounters = location_area_encounters;
        this.moves = moves;
        this.sprites = sprites;
        this.species = species;
        this.stats = stats;
        this.types = types;
    }
}

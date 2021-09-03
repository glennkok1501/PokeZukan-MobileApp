package com.gmail.pokedex.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonSpecies implements Serializable {
    private int id;
    private String name;
    private int order;
    private int gender_rate;
    private int capture_rate;
    private int base_happiness;
    private boolean is_baby;
    private boolean is_legendary;
    private boolean is_mythical;
    private int hatch_counter;
    private ArrayList<NamedAPIResource> egg_groups;

}

package com.gmail.pokedex.Utils;

import com.gmail.pokedex.Model.Pokemon;

import java.util.Comparator;

public class PokemonComparator implements Comparator<Pokemon> {

    @Override
    public int compare (Pokemon p1, Pokemon p2){
        int comparison = 0;
        comparison = Integer.compare(p1.getId(), p2.getId());
        if (comparison == 0){
            comparison = p1.getName().compareTo(p2.getName());
        }
        return comparison;
    }

}

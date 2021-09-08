package com.gmail.pokedex.Utils;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonBrief;

import java.util.Comparator;

public class PokemonComparator implements Comparator<PokemonBrief> {

    @Override
    public int compare (PokemonBrief p1, PokemonBrief p2){
        int comparison = 0;
//        if (p1.getOrder() < 1 || p2.getOrder() < 1){
            comparison = Integer.compare(p1.getId(), p2.getId());
//        }
//        else{
//            comparison = Integer.compare(p1.getOrder(), p2.getOrder());
//        }
        if (comparison == 0){
            comparison = p1.getName().compareTo(p2.getName());
        }
        return comparison;
    }

}

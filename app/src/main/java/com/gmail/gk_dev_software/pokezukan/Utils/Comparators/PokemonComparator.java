package com.gmail.gk_dev_software.pokezukan.Utils.Comparators;

import com.gmail.gk_dev_software.pokezukan.Model.PokemonBrief;

import java.util.Comparator;

public class PokemonComparator implements Comparator<PokemonBrief> {

    @Override
    public int compare (PokemonBrief p1, PokemonBrief p2){
        int comparison = 0;
            comparison = Integer.compare(p1.getId(), p2.getId());

        if (comparison == 0){
            comparison = p1.getName().compareTo(p2.getName());
        }
        return comparison;
    }

}

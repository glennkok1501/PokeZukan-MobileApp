package com.gmail.gk_dev_software.pokezukan.Utils.Comparators;

import com.gmail.gk_dev_software.pokezukan.Model.PokemonMove;

import java.util.Comparator;

public class PokemonMoveComparator implements Comparator<PokemonMove> {

    @Override
    public int compare (PokemonMove p1, PokemonMove p2){
        int comparison = 0;
        comparison = Integer.compare(p1.getLevel(), p2.getLevel());

        if (comparison == 0){
            comparison = p1.getName().compareTo(p2.getName());
        }
        return comparison;
    }

}

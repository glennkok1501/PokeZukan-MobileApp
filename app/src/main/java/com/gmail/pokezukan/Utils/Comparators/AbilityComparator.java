package com.gmail.pokezukan.Utils.Comparators;

import com.gmail.pokezukan.Model.AbilityBrief;
import com.gmail.pokezukan.Model.PokemonBrief;

import java.util.Comparator;

public class AbilityComparator implements Comparator<AbilityBrief> {
    @Override
    public int compare (AbilityBrief a1, AbilityBrief a2){
        return a1.getName().compareTo(a2.getName());
    }
}

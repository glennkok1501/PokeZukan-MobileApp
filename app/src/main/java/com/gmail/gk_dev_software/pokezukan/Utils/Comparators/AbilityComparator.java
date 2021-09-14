package com.gmail.gk_dev_software.pokezukan.Utils.Comparators;

import com.gmail.gk_dev_software.pokezukan.Model.AbilityBrief;

import java.util.Comparator;

public class AbilityComparator implements Comparator<AbilityBrief> {
    @Override
    public int compare (AbilityBrief a1, AbilityBrief a2){
        return a1.getName().compareTo(a2.getName());
    }
}

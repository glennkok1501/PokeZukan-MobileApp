package com.gmail.gk_dev_software.pokezukan.Utils.Comparators;

import com.gmail.gk_dev_software.pokezukan.Model.AbilityBrief;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {
    @Override
    public int compare (String s1, String s2){
        return s1.compareTo(s2);
    }
}

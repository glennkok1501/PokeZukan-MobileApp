package com.gmail.pokezukan.Utils.Comparators;

import com.gmail.pokezukan.Model.AbilityBrief;
import com.gmail.pokezukan.Model.MoveBrief;

import java.util.Comparator;

public class MoveComparator implements Comparator<MoveBrief> {
    @Override
    public int compare (MoveBrief m1, MoveBrief m2){
        return m1.getName().compareTo(m2.getName());
    }
}

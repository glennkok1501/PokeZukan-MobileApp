package com.gmail.gk_dev_software.pokezukan.Utils.Comparators;

import com.gmail.gk_dev_software.pokezukan.Model.MoveBrief;

import java.util.Comparator;

public class MoveComparator implements Comparator<MoveBrief> {
    @Override
    public int compare (MoveBrief m1, MoveBrief m2){
        return m1.getName().compareTo(m2.getName());
    }
}

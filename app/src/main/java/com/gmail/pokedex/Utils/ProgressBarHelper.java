package com.gmail.pokedex.Utils;

import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarHelper {
    private ProgressBar p;

    public ProgressBarHelper(ProgressBar p) {
        this.p = p;
    }

    public void show(){
        p.setVisibility(View.VISIBLE);
    }

    public void hide(){
        p.setVisibility(View.GONE);
    }
}

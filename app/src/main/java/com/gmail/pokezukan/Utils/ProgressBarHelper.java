package com.gmail.pokezukan.Utils;

import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarHelper {
    private ProgressBar p;
    private ProgressBar[] pList;

    public ProgressBarHelper(ProgressBar p) {
        this.p = p;
    }

    public ProgressBarHelper(ProgressBar[] pList) {
        this.pList = pList;
    }

    public void show(){
        p.setVisibility(View.VISIBLE);
    }

    public void hide(){
        p.setVisibility(View.GONE);
    }

    public void multiShow(){
        for (ProgressBar progressBar : pList) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void multiHide(){
        for (ProgressBar progressBar : pList) {
            progressBar.setVisibility(View.GONE);
        }
    }
}

package com.gmail.pokezukan.Model;

import java.io.Serializable;

public class Training implements Serializable {
    private int catch_rate;
    private int base_exp;
    private String growth_rate;

    public int getCatch_rate() {
        return catch_rate;
    }

    public void setCatch_rate(int catch_rate) {
        this.catch_rate = catch_rate;
    }

    public int getBase_exp() {
        return base_exp;
    }

    public void setBase_exp(int base_exp) {
        this.base_exp = base_exp;
    }

    public String getGrowth_rate() {
        return growth_rate;
    }

    public void setGrowth_rate(String growth_rate) {
        this.growth_rate = growth_rate;
    }

    public Training() {
    }

    public Training(int catch_rate, int base_exp, String growth_rate) {
        this.catch_rate = catch_rate;
        this.base_exp = base_exp;
        this.growth_rate = growth_rate;
    }
}

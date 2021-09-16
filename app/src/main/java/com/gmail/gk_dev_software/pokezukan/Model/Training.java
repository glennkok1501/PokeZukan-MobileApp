package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;
import java.util.List;

public class Training implements Serializable {
    private List<String> ev_yield;
    private int catch_rate;
    private int base_exp;
    private String growth_rate;

    public List<String> getEv_yield() {
        return ev_yield;
    }

    public void setEv_yield(List<String> ev_yield) {
        this.ev_yield = ev_yield;
    }

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

    public Training(List<String> ev_yield, int catch_rate, int base_exp, String growth_rate) {
        this.ev_yield = ev_yield;
        this.catch_rate = catch_rate;
        this.base_exp = base_exp;
        this.growth_rate = growth_rate;
    }
}

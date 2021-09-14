package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;

public class Stats implements Serializable {
    private int hp;
    private int attack;
    private int defense;
    private int sp_atk;
    private int sp_def;
    private int speed;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSp_atk() {
        return sp_atk;
    }

    public void setSp_atk(int sp_atk) {
        this.sp_atk = sp_atk;
    }

    public int getSp_def() {
        return sp_def;
    }

    public void setSp_def(int sp_def) {
        this.sp_def = sp_def;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Stats() {
    }

    public Stats(int hp, int attack, int defense, int sp_atk, int sp_def, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.sp_atk = sp_atk;
        this.sp_def = sp_def;
        this.speed = speed;
    }
}

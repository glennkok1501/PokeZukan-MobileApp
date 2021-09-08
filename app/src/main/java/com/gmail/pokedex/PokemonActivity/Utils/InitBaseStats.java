package com.gmail.pokedex.PokemonActivity.Utils;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class InitBaseStats {

    private View view;
    private Context context;

    public InitBaseStats(View view, Pokemon pokemon) {
        this.view = view;
        context = view.getContext();

        int[] stats = new int[]{
                pokemon.getBase_stats().getHp(),
                pokemon.getBase_stats().getAttack(),
                pokemon.getBase_stats().getDefense(),
                pokemon.getBase_stats().getSp_atk(),
                pokemon.getBase_stats().getSp_def(),
                pokemon.getBase_stats().getSpeed()
        };

        ProgressBar hpBar = view.findViewById(R.id.base_stats_hp_bar);
        ProgressBar atkBar = view.findViewById(R.id.base_stats_attack_bar);
        ProgressBar defBar = view.findViewById(R.id.base_stats_def_bar);
        ProgressBar spAtkBar = view.findViewById(R.id.base_stats_spAtk_bar);
        ProgressBar spDefBar = view.findViewById(R.id.base_stats_spDef_bar);
        ProgressBar spdBar = view.findViewById(R.id.base_stats_speed_bar);

        TextView hp = view.findViewById(R.id.base_stats_hp_textView);
        TextView atk = view.findViewById(R.id.base_stats_attack_textView);
        TextView def = view.findViewById(R.id.base_stats_def_textView);
        TextView spAtk = view.findViewById(R.id.base_stats_spAtk_textView);
        TextView spDef = view.findViewById(R.id.base_stats_spDef_textView);
        TextView spd = view.findViewById(R.id.base_stats_speed_textView);
        TextView total = view.findViewById(R.id.base_stats_total);

        int[] statsVal = getStats(stats);

        setStat(hpBar, hp, stats[0], statsVal[0], statsVal[2]);
        setStat(atkBar, atk, stats[1], statsVal[0], statsVal[2]);
        setStat(defBar, def, stats[2], statsVal[0], statsVal[2]);
        setStat(spAtkBar, spAtk, stats[3], statsVal[0], statsVal[2]);
        setStat(spDefBar, spDef, stats[4], statsVal[0], statsVal[2]);
        setStat(spdBar, spd, stats[5], statsVal[0], statsVal[2]);

        total.setText(String.format("Total: %s", statsVal[1]));

    }

    private void setStat(ProgressBar bar, TextView num, int value, int maxVal, int avgVal){
        int percent = (int)Math.round((value*1.0/maxVal)*100);
        bar.setProgress(percent);
        if (value >= avgVal){
            bar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.good_stat), PorterDuff.Mode.SRC_IN);
        }
        else{
            bar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.average_stat), PorterDuff.Mode.SRC_IN);
        }

        num.setText(String.valueOf(value));
    }

    private int[] getStats(int[] arr){
        int[] stats = new int[3];

        for (int j : arr) {
            if (j > stats[0]) {
                stats[0] = j;
            }
            stats[1] += j;
        }
        stats[2] = (int) Math.round(stats[1]*1.0/6);
        return stats;
    }


}

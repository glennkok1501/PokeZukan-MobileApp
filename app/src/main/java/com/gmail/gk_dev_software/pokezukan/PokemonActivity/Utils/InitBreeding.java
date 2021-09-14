package com.gmail.gk_dev_software.pokezukan.PokemonActivity.Utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gmail.gk_dev_software.pokezukan.Model.Gender;
import com.gmail.gk_dev_software.pokezukan.Model.Pokemon;
import com.gmail.gk_dev_software.pokezukan.Model.PokemonEggCycle;
import com.gmail.gk_dev_software.pokezukan.R;
import com.gmail.gk_dev_software.pokezukan.Utils.AutoCap;
import com.gmail.gk_dev_software.pokezukan.Utils.ListConvert;

public class InitBreeding {
    private Context context;

    public InitBreeding(View view, Pokemon pokemon) {
        this.context = view.getContext();

        TextView eg = view.findViewById(R.id.breeding_eggGroup_textView);
        TextView happy = view.findViewById(R.id.breeding_baseHap_textView);
        TextView gender = view.findViewById(R.id.breeding_gender_textView);
        TextView ec = view.findViewById(R.id.breeding_eggCycle_textView);

        eg.setText(ListConvert.ToString(pokemon.getBreeding().getEgg_groups()));
        happy.setText(String.valueOf(pokemon.getBreeding().getBase_happiness()));
        gender.setText(sortGender(pokemon.getBreeding().getGender()));
        ec.setText(getEggCycle(pokemon.getBreeding().getEgg_cycle()));
    }

    private String sortGender(Gender gender){
        Double m = gender.getMale();
        Double f = gender.getFemale();
        if (m == 0 && f == 0){
            return "No Gender";
        }
        StringBuilder builder = new StringBuilder();
        if (m > 0){
            builder.append("\u2642"+m+"% ");
        }
        if (f > 0){
            builder.append("\u2640"+f+"% ");

        }
        return builder.toString();
    }

    private String getEggCycle(PokemonEggCycle p){
        int counter = p.getHatch_counter();
        int steps = p.getSteps();
        return AutoCap.set(String.format("%s (Approx. %s Steps)", counter, steps));
    }
}

package com.gmail.pokedex.PokemonActivity.Utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gmail.pokedex.Model.Gender;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.AutoCap;
import com.gmail.pokedex.Utils.ListConvert;

import java.util.List;

public class InitBreeding {
    private Context context;

    public InitBreeding(View view, Pokemon pokemon) {
        this.context = view.getContext();

        TextView eg = view.findViewById(R.id.breeding_eggGroup_textView);
        TextView gender = view.findViewById(R.id.breeding_gender_textView);
        TextView ec = view.findViewById(R.id.breeding_eggCycle_textView);

        eg.setText(ListConvert.ToString(pokemon.getBreeding().getEgg_groups()));
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

    private String getEggCycle(String s){
        if (s.length() == 0){
            return "-";
        }
        return AutoCap.set(s);
    }
}

package com.gmail.pokedex.PokemonActivity.Utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.AutoCap;

public class InitTraining {
    private Context context;

    public InitTraining(View view, Pokemon pokemon) {
        this.context = view.getContext();

        TextView ev = view.findViewById(R.id.training_ev_textView);
        TextView catchRate = view.findViewById(R.id.training_catchRate_textView);
        TextView fs = view.findViewById(R.id.training_baseFriendship_textView);
        TextView exp = view.findViewById(R.id.training_baseExp_textView);
        TextView gr = view.findViewById(R.id.training_growthRate_textView);

        ev.setText(AutoCap.set(pokemon.getTraining().getEv_yield()));
        catchRate.setText(String.valueOf(pokemon.getTraining().getCatch_rate()));
        fs.setText(String.valueOf(pokemon.getTraining().getBase_friendship()));
        exp.setText(String.valueOf(pokemon.getTraining().getBase_exp()));
        gr.setText(AutoCap.set(pokemon.getTraining().getGrowth_rate()));

    }
}

package com.gmail.pokezukan.PokemonActivity.Utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.AutoCap;

public class InitTraining {
    private Context context;

    public InitTraining(View view, Pokemon pokemon) {
        this.context = view.getContext();

        TextView catchRate = view.findViewById(R.id.training_catchRate_textView);
        TextView exp = view.findViewById(R.id.training_baseExp_textView);
        TextView gr = view.findViewById(R.id.training_growthRate_textView);

        catchRate.setText(String.valueOf(pokemon.getTraining().getCatch_rate()));
        exp.setText(String.valueOf(pokemon.getTraining().getBase_exp()));
        gr.setText(AutoCap.set(pokemon.getTraining().getGrowth_rate().replace("-", " ")));

    }
}

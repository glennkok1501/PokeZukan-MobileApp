package com.gmail.gk_dev_software.pokezukan.PokemonActivity.Utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gmail.gk_dev_software.pokezukan.Model.Pokemon;
import com.gmail.gk_dev_software.pokezukan.R;
import com.gmail.gk_dev_software.pokezukan.Utils.AutoCap;
import com.gmail.gk_dev_software.pokezukan.Utils.ListConvert;

public class InitTraining {
    private Context context;

    public InitTraining(View view, Pokemon pokemon) {
        this.context = view.getContext();

        TextView ev = view.findViewById(R.id.training_ev_textView);
        TextView catchRate = view.findViewById(R.id.training_catchRate_textView);
        TextView exp = view.findViewById(R.id.training_baseExp_textView);
        TextView gr = view.findViewById(R.id.training_growthRate_textView);

        ev.setText(AutoCap.set(ListConvert.ToString(pokemon.getTraining().getEv_yield())));
        catchRate.setText(String.valueOf(pokemon.getTraining().getCatch_rate()));
        exp.setText(String.valueOf(pokemon.getTraining().getBase_exp()));
        gr.setText(AutoCap.set(pokemon.getTraining().getGrowth_rate().replace("-", " ")));
    }
}

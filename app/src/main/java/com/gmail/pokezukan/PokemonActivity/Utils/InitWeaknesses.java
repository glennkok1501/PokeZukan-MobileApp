package com.gmail.pokezukan.PokemonActivity.Utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;

import androidx.gridlayout.widget.GridLayout;

import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.Model.Weakness;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.AutoCap;
import com.gmail.pokezukan.Utils.TypeHelper;

import java.util.List;

public class InitWeaknesses {

    private Context context;
    private TypeHelper typeHelper;
    private View view;

    public InitWeaknesses(View view, Pokemon pokemon) {
        this.context = view.getContext();
        this.typeHelper = new TypeHelper();
        this.view = view;
        GridLayout weak_grid = view.findViewById(R.id.weakness_grid);
        GridLayout res_grid = view.findViewById(R.id.resistant_grid);

        loadData(weak_grid, res_grid, pokemon.getWeaknesses());

    }

    private void loadData(GridLayout weak_grid, GridLayout res_grid, List<Weakness> weaknesses){
        for (Weakness w : weaknesses){
            Double eff = w.getEffective();
            if (eff > 1){
                weak_grid.addView(setValue(w));
            }
            else if (eff < 1){
                res_grid.addView(setValue(w));
            }
        }
    }

    private TextView setValue(Weakness w){
        TextView t = new TextView(context);
        t.setText(String.format("%s x%s",AutoCap.set(w.getType()), encodeEff(w.getEffective())));
        t.setTextSize(16);
        t.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_bg));
        t.getBackground().setColorFilter(ContextCompat.getColor(context, typeHelper.getColor(w.getType())), PorterDuff.Mode.SRC_IN);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, 1f),
                GridLayout.spec(GridLayout.UNDEFINED, 1f));
        params.setMargins(5,5,5,5);
        t.setLayoutParams(params);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(ContextCompat.getColor(context, R.color.white));
        t.setPadding(0,5,0,5);
        return t;
    }

    private String encodeEff(Double e){
        String encoded;
        if (e == 0.25){
            encoded = "\u00BC";
        }
        else if (e == 0.5){
            encoded = "\u00BD";
        }
        else{
            encoded = String.valueOf(Math.round(e));

        }
    return encoded;
    }
}

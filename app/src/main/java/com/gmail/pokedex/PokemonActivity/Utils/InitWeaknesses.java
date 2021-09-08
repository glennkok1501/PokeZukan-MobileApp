package com.gmail.pokedex.PokemonActivity.Utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.Weakness;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.AutoCap;
import com.gmail.pokedex.Utils.TypeHelper;

import java.util.List;

public class InitWeaknesses {

    private Context context;
    private TypeHelper typeHelper;
    private View view;

    public InitWeaknesses(View view, Pokemon pokemon) {
        this.context = view.getContext();
        this.typeHelper = new TypeHelper();
        this.view = view;
        GridLayout grid = view.findViewById(R.id.weakness_grid);
        loadData(grid, pokemon.getWeaknesses());

    }

    private void loadData(GridLayout grid, List<Weakness> weaknesses){
        for (Weakness w : weaknesses){
            if (w.getEffective() > 1){
                grid.addView(setValue(w));
            }
        }
    }

    private TextView setValue(Weakness w){
        TextView t = new TextView(context);
        t.setText(String.format("%s x%s",AutoCap.set(w.getType()), w.getEffective()));
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
        t.setPadding(3,5,5,3);
        return t;
    }
}

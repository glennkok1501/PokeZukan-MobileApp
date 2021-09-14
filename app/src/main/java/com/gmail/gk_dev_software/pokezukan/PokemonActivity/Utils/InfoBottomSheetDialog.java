package com.gmail.gk_dev_software.pokezukan.PokemonActivity.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.gk_dev_software.pokezukan.Model.Pokemon;
import com.gmail.gk_dev_software.pokezukan.Model.PokemonAbility;
import com.gmail.gk_dev_software.pokezukan.R;
import com.gmail.gk_dev_software.pokezukan.Utils.AbilityBottomSheet;
import com.gmail.gk_dev_software.pokezukan.Utils.AutoCap;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class InfoBottomSheetDialog {
    private Context context;
    private Pokemon pokemon;
    private BottomSheetDialog dialog;

    public BottomSheetDialog getDialog() {
        return dialog;
    }

    public InfoBottomSheetDialog(Context context, Pokemon pokemon) {
        this.context = context;
        this.pokemon = pokemon;
        dialog = new BottomSheetDialog(context, R.style.SheetDialog);
        dialog.setContentView(R.layout.pokemon_info_bottom_sheet);
        TextView species = dialog.findViewById(R.id.aboutFragment_btmSheet_species_textView);
        TextView entry = dialog.findViewById(R.id.aboutFragment_btmSheet_entry_textView);
        TextView weight = dialog.findViewById(R.id.aboutFragment_btmSheet_weight_textView);
        TextView height = dialog.findViewById(R.id.aboutFragment_btmSheet_height_textView);
        LinearLayout abilityLayout = dialog.findViewById(R.id.aboutFragment_btmSheet_ability_layout);

        species.setText(AutoCap.set(pokemon.getInfo().getSpecies()));
        entry.setText(AutoCap.capStart(pokemon.getEntry()));
        weight.setText(String.format("%s kg", pokemon.getInfo().getWeight()));
        height.setText(String.format("%s m", pokemon.getInfo().getHeight()));
        loadAbility(abilityLayout, pokemon.getInfo().getAbilities());

    }

    private void loadAbility(LinearLayout layout, List<PokemonAbility> abilities){
        for (PokemonAbility a : abilities){
            TextView t = new TextView(context);
            t.setText(getName(a));
            t.setTextSize(16);
            t.setGravity(Gravity.CENTER);
            t.setPadding(0,10,0,10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            t.setLayoutParams(params);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    AbilityBottomSheet newBtmSheet = new AbilityBottomSheet(context, String.format("abilities/%s.json", a.getName()));
                    newBtmSheet.show();
                }
            });

            layout.addView(t);
        }
    }

    private String getName(PokemonAbility p){
        String name = AutoCap.set(p.getName().replace("-", " "));
        if (p.is_hidden()){
            return String.format("%s (Hidden Ability)", name);
        }
        return name;

    }
}

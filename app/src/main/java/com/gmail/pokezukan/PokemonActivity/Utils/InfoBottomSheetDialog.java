package com.gmail.pokezukan.PokemonActivity.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.AbilityBottomSheet;
import com.gmail.pokezukan.Utils.AutoCap;
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
        TextView entry = dialog.findViewById(R.id.aboutFragment_btmSheet_entry_textView);
        TextView weight = dialog.findViewById(R.id.aboutFragment_btmSheet_weight_textView);
        TextView height = dialog.findViewById(R.id.aboutFragment_btmSheet_height_textView);
        LinearLayout abilityLayout = dialog.findViewById(R.id.aboutFragment_btmSheet_ability_layout);


        entry.setText(AutoCap.set(pokemon.getEntry()));
        weight.setText(String.format("%s kg", pokemon.getInfo().getWeight()));
        height.setText(String.format("%s m", pokemon.getInfo().getHeight()));
        loadAbility(abilityLayout, pokemon.getInfo().getAbilities());


    }

    private void loadAbility(LinearLayout layout, List<String> abilities){
        for (String a : abilities){
            TextView t = new TextView(context);
            t.setText(AutoCap.set(a));
            t.setTextSize(16);
            t.setGravity(Gravity.CENTER);

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    AbilityBottomSheet newBtmSheet = new AbilityBottomSheet(context, nameToLink(a));
                    newBtmSheet.show();
                }
            });

            layout.addView(t);
        }

    }

    private String nameToLink(String s){
        if (s.contains("(")){
            s = s.replaceAll(" \\(.*\\)", "");
        }
        return s.replace(" ", "-");
    }
}

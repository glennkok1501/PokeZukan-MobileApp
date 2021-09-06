package com.gmail.pokedex.PokemonActivity;

import android.content.Context;
import android.widget.TextView;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.PokemonSerializer;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class InfoBottomSheetDialog {
    private Context context;
    private Pokemon pokemon;

    public InfoBottomSheetDialog(Context context, Pokemon pokemon) {
        this.context = context;
        this.pokemon = pokemon;
    }

    public void show(){
        BottomSheetDialog dialog = new BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme);
        dialog.setContentView(R.layout.pokemon_info_bottom_sheet);
        TextView entry = dialog.findViewById(R.id.aboutFragment_btmSheet_entry_textView);
        TextView weight = dialog.findViewById(R.id.aboutFragment_btmSheet_weight_textView);
        TextView height = dialog.findViewById(R.id.aboutFragment_btmSheet_height_textView);

        entry.setText(new PokemonSerializer().getSummary(pokemon));
        weight.setText(String.format("%s kg", pokemon.getInfo().getWeight()));
        height.setText(String.format("%s m", pokemon.getInfo().getHeight()));



        dialog.show();
    }
}

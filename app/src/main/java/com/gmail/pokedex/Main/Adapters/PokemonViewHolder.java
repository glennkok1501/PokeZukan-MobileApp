package com.gmail.pokedex.Main.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    ImageView artwork, type1, type2;
    TextView name, id;
    ConstraintLayout layout;

    public PokemonViewHolder(View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.mainRV_name_textView);
        id = itemView.findViewById(R.id.mainRV_id_textView);
        artwork = itemView.findViewById(R.id.mainRV_pokemon_imageView);
        type1 = itemView.findViewById(R.id.mainRV_type1_imageView);
        type2 = itemView.findViewById(R.id.mainRV_type2_imageView);
        layout = itemView.findViewById(R.id.mainRV_innerLayout);

    }
}
package com.gmail.pokedex.Main.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    ImageView artwork;
    TextView id;
    ConstraintLayout layout;

    public PokemonViewHolder(View itemView){
        super(itemView);
        id = itemView.findViewById(R.id.mainRV_id_textView);
        artwork = itemView.findViewById(R.id.mainRV_pokemon_imageView);
        layout = itemView.findViewById(R.id.mainRV_innerLayout);

    }
}
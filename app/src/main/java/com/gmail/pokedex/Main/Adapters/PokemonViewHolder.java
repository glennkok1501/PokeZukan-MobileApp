package com.gmail.pokedex.Main.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    ImageView artwork;
    TextView name;

    public PokemonViewHolder(View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.mainRV_name_textView);
        artwork = itemView.findViewById(R.id.mainRV_pokemon_imageView);

    }
}
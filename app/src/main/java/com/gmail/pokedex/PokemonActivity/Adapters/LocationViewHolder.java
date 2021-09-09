package com.gmail.pokedex.PokemonActivity.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.R;

public class LocationViewHolder extends RecyclerView.ViewHolder {

    TextView game, area;

    public LocationViewHolder(View itemView) {
        super(itemView);
        game = itemView.findViewById(R.id.location_game_textView);
        area = itemView.findViewById(R.id.location_area_textView);
    }
}

package com.gmail.gk_dev_software.pokezukan.Main.Adapters.dex;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.gk_dev_software.pokezukan.R;

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
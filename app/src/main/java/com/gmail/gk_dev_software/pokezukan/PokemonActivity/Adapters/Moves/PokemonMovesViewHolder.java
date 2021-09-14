package com.gmail.gk_dev_software.pokezukan.PokemonActivity.Adapters.Moves;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.gk_dev_software.pokezukan.R;

public class PokemonMovesViewHolder extends RecyclerView.ViewHolder {

    TextView level, name;

    public PokemonMovesViewHolder(View itemView) {
        super(itemView);
        level = itemView.findViewById(R.id.move_lvl_textView);
        name = itemView.findViewById(R.id.move_name_textView);
    }
}

package com.gmail.pokedex.Main.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.R;

public class AbilityViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    public AbilityViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.ability_name_textView);
    }
}

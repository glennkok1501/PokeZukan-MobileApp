package com.gmail.gk_dev_software.pokezukan.Main.Adapters.ability;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.gk_dev_software.pokezukan.R;

public class AbilityViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    public AbilityViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.ability_name_textView);
    }
}

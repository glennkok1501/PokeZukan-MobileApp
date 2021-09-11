package com.gmail.pokedex.Main.Adapters.move;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.R;

public class MoveViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView name;
    public MoveViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.move_type_imageView);
        name = itemView.findViewById(R.id.move_name_textView);
    }
}

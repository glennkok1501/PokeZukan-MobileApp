package com.gmail.pokedex.Main.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.Model.AbilityBrief;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.AbilityBottomSheet;
import com.gmail.pokedex.Utils.AutoCap;

import java.util.ArrayList;

public class AbilityAdapter extends RecyclerView.Adapter<AbilityViewHolder> {
    ArrayList<AbilityBrief> data;
    private AbilityBottomSheet btmSheet;

    public AbilityAdapter(ArrayList<AbilityBrief> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AbilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.ability_recyclerview_layout, parent, false);
        return new AbilityViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull AbilityViewHolder holder, int position) {
        AbilityBrief a = data.get(position);
        holder.name.setText(AutoCap.set(a.getName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btmSheet = new AbilityBottomSheet(holder.itemView.getContext(), a.getLink());
                btmSheet.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

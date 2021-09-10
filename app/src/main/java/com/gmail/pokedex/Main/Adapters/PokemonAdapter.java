package com.gmail.pokedex.Main.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.PokemonBrief;
import com.gmail.pokedex.PokemonActivity.PokemonActivity;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.PokemonComparator;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    ArrayList<PokemonBrief> data;
    private final Context context;

    public PokemonAdapter(ArrayList<PokemonBrief> input, Context context){
        data = input;
        this.context = context;
    }

    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_view_grid_layout, parent, false);
        return new PokemonViewHolder(item);
    }

    public void onBindViewHolder(PokemonViewHolder holder, int position){
        PokemonBrief p = data.get(position);

        if (p.getId() >= 1000){
            holder.id.setVisibility(View.INVISIBLE);
        }
        else{
            holder.id.setVisibility(View.VISIBLE);
            holder.id.setText(String.format("%03d", p.getId()));
        }
        Glide.with(context)
                .load(p.getIcon())
                .override(180, 180)
                .placeholder(R.drawable.ic_pokeball_color)
                .error(R.drawable.ic_pokeball)
                .into(holder.artwork);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PokemonActivity.class);
                intent.putExtra("link", p.getLink());
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }

    public void updateData(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data.sort(new PokemonComparator());
        }
        notifyItemRangeChanged(0, data.size());
    }

    public void filter(ArrayList<PokemonBrief> arrayList){
        data = arrayList;
        notifyDataSetChanged();

    }

}

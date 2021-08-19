package com.gmail.pokedex.Main.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonMainRVModel;
import com.gmail.pokedex.Model.RawPokemonModel;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    ArrayList<Pokemon> data;


    public PokemonAdapter(ArrayList<Pokemon> input){
        data = input;
    }

    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_view_layout, parent, false);
        return new PokemonViewHolder(item);
    }

    public void onBindViewHolder(PokemonViewHolder holder, int position){
        Pokemon p = data.get(position);
        holder.name.setText(p.getName());
        Glide.with(holder.itemView.getContext()).load(p.getSprites().getOther().getOfficial_artwork().getFront_default()).into(holder.artwork);
//        holder.sprite.setImageBitmap(p.getSprite());
    }

    public int getItemCount(){
        return data.size();
    }
}

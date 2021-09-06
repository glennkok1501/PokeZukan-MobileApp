package com.gmail.pokedex.Main.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.PokemonActivity.PokemonActivity;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.PokemonComparator;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    ArrayList<Pokemon> data;
    private final Context context;
    private final ImageScaleAnim anim;

    public PokemonAdapter(ArrayList<Pokemon> input, Context context){
        data = input;
        this.context = context;
        anim = new ImageScaleAnim(context);
    }

    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_view_grid_layout, parent, false);
        return new PokemonViewHolder(item);
    }

    public void onBindViewHolder(PokemonViewHolder holder, int position){
        Pokemon p = data.get(position);

        if (p.getId() >= 1000){
            holder.id.setVisibility(View.INVISIBLE);
        }
        else{
            holder.id.setVisibility(View.VISIBLE);
            holder.id.setText(String.format("#%03d", p.getId()));
        }
        Glide.with(context)
                .load(p.getSprites().getSmall())
                .override(180, 180)
                .placeholder(R.drawable.ic_pokeball_color)
                .error(R.drawable.ic_pokeball)
                .into(holder.artwork);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PokemonActivity.class);
                intent.putExtra("pokemon", p);
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
        notifyDataSetChanged();
    }

    public void filter(ArrayList<Pokemon> arrayList){
        data = arrayList;
        notifyDataSetChanged();

    }

}

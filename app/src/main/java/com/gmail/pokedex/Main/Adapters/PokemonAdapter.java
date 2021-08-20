package com.gmail.pokedex.Main.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonType;
import com.gmail.pokedex.PokemonActivity.PokemonActivity;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.TypeImageHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    ArrayList<Pokemon> data;
    private TypeImageHelper typeImageHelper = new TypeImageHelper();

    public PokemonAdapter(ArrayList<Pokemon> input){
        data = input;
    }

    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_view_grid_layout, parent, false);
        return new PokemonViewHolder(item);
    }

    public void onBindViewHolder(PokemonViewHolder holder, int position){
        Pokemon p = data.get(position);
        holder.name.setText(p.getName());
        holder.id.setText(String.format("#%03d", p.getId()));
//        holder.id.setText("#"+p.getId());
        typeImageHelper.setType(p.getTypes(), holder.type1, holder.type2);
        Glide.with(holder.itemView.getContext())
                .load(p.getSprites().getOther().getOfficial_artwork().getFront_default())
                .override(180, 180)
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.pokeball)
                .into(holder.artwork);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, PokemonActivity.class);
                intent.putExtra("pokemon", p);
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }

    public void updateData(int range){
        notifyItemRangeChanged(0, range);
    }
}

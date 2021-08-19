package com.gmail.pokedex.Main.Adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonMainRVModel;
import com.gmail.pokedex.Model.PokemonType;
import com.gmail.pokedex.Model.RawPokemonModel;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.ImageLoader;
import com.gmail.pokedex.Utils.TypeImageHelper;

import java.io.IOException;
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
        holder.id.setText("#"+p.getId());
        setType(p.getTypes(), holder.type1, holder.type2);
        Glide.with(holder.itemView.getContext())
                .load(p.getSprites().getOther().getOfficial_artwork().getFront_default())
                .override(180, 180)
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.pokeball)
                .into(holder.artwork);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder types = new StringBuilder();
                for (PokemonType t : p.getTypes()){
                    types.append(t.getType().getName());
                }
                Log.v("TAG", types.toString());
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }

    public void updateData(int range){
        notifyItemRangeChanged(0, range);
    }

    private void setType(ArrayList<PokemonType> types, ImageView image1, ImageView image2){
        image2.setVisibility(View.INVISIBLE);
        int type1 = typeImageHelper.getImage(types.get(0).getType().getName());
        setImage(type1, image1);
        if (types.size() > 1){
            image2.setVisibility(View.VISIBLE);
            int type2 = typeImageHelper.getImage(types.get(1).getType().getName());
            setImage(type2, image2);
        }
    }

    private void setImage(int image, ImageView imageView){
        if (image > 0){
            imageView.setImageResource(image);
        }
    }
}

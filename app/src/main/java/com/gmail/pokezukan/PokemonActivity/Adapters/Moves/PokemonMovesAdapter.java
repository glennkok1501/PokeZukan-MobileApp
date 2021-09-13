package com.gmail.pokezukan.PokemonActivity.Adapters.Moves;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokezukan.Model.Location;
import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.Model.PokemonBrief;
import com.gmail.pokezukan.Model.PokemonMove;
import com.gmail.pokezukan.PokemonActivity.Adapters.Location.LocationViewHolder;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.Comparators.PokemonComparator;
import com.gmail.pokezukan.Utils.Comparators.PokemonMoveComparator;
import com.gmail.pokezukan.Utils.ListConvert;

import java.util.ArrayList;
import java.util.List;

public class PokemonMovesAdapter extends RecyclerView.Adapter<PokemonMovesViewHolder> {
    List<PokemonMove> data;

    public PokemonMovesAdapter(List<PokemonMove> data) {
        this.data = data;
    }

    @Override
    public PokemonMovesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_move_recyclerview_layout, parent, false);
        return new PokemonMovesViewHolder(item);
    }

    @Override
    public void onBindViewHolder(PokemonMovesViewHolder holder, int position) {
        PokemonMove m = data.get(position);
        holder.name.setText(m.getName());
        holder.level.setText(String.valueOf(m.getLevel()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data.sort(new PokemonMoveComparator());
        }
        notifyItemRangeChanged(0, data.size());
    }

    public void filter(ArrayList<PokemonMove> arrayList){
        data = arrayList;
        notifyDataSetChanged();
    }

}

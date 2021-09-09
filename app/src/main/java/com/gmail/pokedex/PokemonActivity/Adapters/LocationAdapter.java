package com.gmail.pokedex.PokemonActivity.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokedex.Main.Adapters.PokemonViewHolder;
import com.gmail.pokedex.Model.Location;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.ListConvert;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    List<Location> data;

    public LocationAdapter(List<Location> data) {
        this.data = data;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_recyclerview_layout, parent, false);
        return new LocationViewHolder(item);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        Location l = data.get(position);
        holder.game.setText(ListConvert.ToString(l.getGame()));

        String area = ListConvert.ToString(l.getArea());
        if (area.length() > 0){
            holder.area.setText(area);
        }
        else{
            holder.area.setText("No Location");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

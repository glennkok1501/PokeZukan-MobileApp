package com.gmail.gk_dev_software.pokezukan.PokemonActivity.Adapters.Location;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gmail.gk_dev_software.pokezukan.Model.Location;
import com.gmail.gk_dev_software.pokezukan.R;
import com.gmail.gk_dev_software.pokezukan.Utils.EmptyDataHelper;
import com.gmail.gk_dev_software.pokezukan.Utils.ListConvert;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    List<Location> data;
    private EmptyDataHelper empty;
    public LocationAdapter(List<Location> data, EmptyDataHelper empty) {
        this.data = data;
        this.empty = empty;
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

        String area = ListConvert.ToBulletList(l.getArea());
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

    public void updateData(){
        int size = data.size();
        notifyItemRangeChanged(0, size);
        empty.check(size);
    }
}

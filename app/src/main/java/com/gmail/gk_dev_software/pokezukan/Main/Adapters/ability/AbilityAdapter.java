package com.gmail.gk_dev_software.pokezukan.Main.Adapters.ability;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.gk_dev_software.pokezukan.Model.AbilityBrief;
import com.gmail.gk_dev_software.pokezukan.R;
import com.gmail.gk_dev_software.pokezukan.Utils.AbilityBottomSheet;
import com.gmail.gk_dev_software.pokezukan.Utils.AutoCap;
import com.gmail.gk_dev_software.pokezukan.Utils.Comparators.AbilityComparator;

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

    public void updateData(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data.sort(new AbilityComparator());
        }
        notifyItemRangeChanged(0, data.size());
    }

    public void filter(ArrayList<AbilityBrief> arrayList){
        data = arrayList;
        notifyDataSetChanged();

    }
}

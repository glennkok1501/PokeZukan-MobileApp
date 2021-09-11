package com.gmail.pokezukan.Main.Adapters.move;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokezukan.Model.MoveBrief;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.AutoCap;
import com.gmail.pokezukan.Utils.MoveBottomSheet;
import com.gmail.pokezukan.Utils.TypeHelper;

import java.util.ArrayList;

public class MoveAdapter extends RecyclerView.Adapter<MoveViewHolder> {
    ArrayList<MoveBrief> data;
    private TypeHelper typeHelper;
    private MoveBottomSheet moveBottomSheet;

    public MoveAdapter(ArrayList<MoveBrief> data) {
        this.data = data;
        typeHelper = new TypeHelper();
    }

    @NonNull
    @Override
    public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.move_recyclerview_layout, parent, false);
        return new MoveViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveViewHolder holder, int position) {
        MoveBrief m = data.get(position);
        holder.name.setText(AutoCap.set(m.getName()));
        Context context = holder.itemView.getContext();
        typeHelper.setImage(context, m.getType(), holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.v("TAG", m.getLink());
                moveBottomSheet = new MoveBottomSheet(context, m.getLink());
                moveBottomSheet.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void filter(ArrayList<MoveBrief> arrayList){
        data = arrayList;
        notifyDataSetChanged();

    }
}
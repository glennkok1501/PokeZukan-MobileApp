package com.gmail.pokezukan.Main.Adapters.move;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokezukan.Model.MoveBrief;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.AutoCap;
import com.gmail.pokezukan.Utils.Comparators.AbilityComparator;
import com.gmail.pokezukan.Utils.Comparators.MoveComparator;
import com.gmail.pokezukan.Utils.MoveBottomSheet;
import com.gmail.pokezukan.Utils.TypeHelper;

import java.util.ArrayList;

public class MoveAdapter extends RecyclerView.Adapter<MoveViewHolder> {
    ArrayList<MoveBrief> data;
    private TypeHelper typeHelper;
    private MoveBottomSheet moveBottomSheet;
    private Context context;

    public MoveAdapter(Context context, ArrayList<MoveBrief> data) {
        this.data = data;
        this.context = context;
        typeHelper = new TypeHelper(context);
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
        holder.name.setText(AutoCap.capStart(m.getName()));
        Context context = holder.itemView.getContext();
        typeHelper.setImage(m.getType(), holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveBottomSheet = new MoveBottomSheet(context, m.getLink());
                moveBottomSheet.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data.sort(new MoveComparator());
        }
        notifyItemRangeChanged(0, data.size());
    }

    public void filter(ArrayList<MoveBrief> arrayList){
        data = arrayList;
        notifyDataSetChanged();

    }
}

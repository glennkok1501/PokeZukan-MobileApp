package com.gmail.pokezukan.Utils;

import android.view.View;
import android.widget.TextView;

import com.gmail.pokezukan.R;

public class EmptyDataHelper {

    public EmptyDataHelper(View view, int dataSize) {
        TextView emptyText = view.findViewById(R.id.empty_textView);
        if (dataSize > 0){
            emptyText.setVisibility(View.GONE);
        }
        else{
            emptyText.setVisibility(View.VISIBLE);
        }
    }
}

package com.gmail.gk_dev_software.pokezukan.Utils;

import android.view.View;
import android.widget.TextView;

import com.gmail.gk_dev_software.pokezukan.R;

public class EmptyDataHelper {

    TextView emptyText;

    public EmptyDataHelper(View view) {
        emptyText = view.findViewById(R.id.empty_textView);
    }

    public void check(int dataSize){
        if (dataSize > 0){
            emptyText.setVisibility(View.GONE);
        }
        else{
            emptyText.setVisibility(View.VISIBLE);
        }
    }
}

package com.gmail.gk_dev_software.pokezukan.Main.Utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.gk_dev_software.pokezukan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabHelper {
    private Animation showAnim, hideAnim;
    private Context context;
    private View searchLayout;
    private RecyclerView rv;
    private int topPadding;
    private EditText searchEditText;
    private FloatingActionButton fab;
    private TextView clear;

    public EditText getSearchEditText() {
        return searchEditText;
    }

    public TextView getClear() {
        return clear;
    }

    public void setHint(String hint){
        searchEditText.setHint(hint);
    }

    public FabHelper(Context context, RecyclerView rv, FloatingActionButton fab, View view, int topPadding) {
        this.context = context;
        this.rv = rv;
        this.fab = fab;
        this.searchLayout = view.findViewById(R.id.search_layout);
        this.searchEditText = view.findViewById(R.id.search_editText);
        this.clear = view.findViewById(R.id.cancel_textView);
        this.topPadding = (int) (topPadding * context.getResources().getDisplayMetrics().density + 0.5f);
        hideAnim = AnimationUtils.loadAnimation(context, R.anim.hide_top_bar);
        showAnim = AnimationUtils.loadAnimation(context, R.anim.show_top_bar);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSearch();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vis = searchLayout.getVisibility();
                if (vis == View.VISIBLE){
                    hideSearch();
                }
                else{
                    showSearch();
                }
            }
        });
    }

    public void checkFabImage(){
        if (searchLayout.getVisibility() == View.VISIBLE){
            fab.setImageResource(R.drawable.ic_baseline_close_24);
        }
        else{
            fab.setImageResource(R.drawable.ic_baseline_search_24);
        }
    }

    private void hideSearch(){
        fab.setImageResource(R.drawable.ic_baseline_search_24);
        clearSearch();
        searchLayout.startAnimation(hideAnim);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        searchLayout.setVisibility(View.GONE);
        rv.setPadding(0,0,0,0);
    }

    private void showSearch(){
        searchLayout.startAnimation(showAnim);
        fab.setImageResource(R.drawable.ic_baseline_close_24);
        searchLayout.setVisibility(View.VISIBLE);
        rv.setPadding(0, topPadding,0,0);
        searchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void clearSearch(){
        searchEditText.getText().clear();
    }

}


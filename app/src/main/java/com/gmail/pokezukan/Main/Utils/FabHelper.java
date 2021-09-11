package com.gmail.pokezukan.Main.Utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.pokezukan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabHelper {
    private Animation topToBottomAnim, bottomToTopAnim, showAnim, hideAnim;
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
        topToBottomAnim = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom_animation);
        bottomToTopAnim = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top_animation);
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

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int vis = fab.getVisibility();
                if (dy > 3){
                    //scrolling down
                    if (vis == View.VISIBLE){
                        hideFab();
                    }
                }
                else if (dy < -3){
                    //scrolling up
                    if (vis == View.GONE){
                        showFab();
                    }
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

    public void showFab(){
        fab.setVisibility(View.VISIBLE);
        fab.startAnimation(bottomToTopAnim);
    }

    public void hideFab(){
        fab.setVisibility(View.GONE);
        fab.startAnimation(topToBottomAnim);

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

    public void checkPause(){
        if (fab.getVisibility() == View.GONE) {
            showFab();
        }
    }


}


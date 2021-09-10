package com.gmail.pokedex.Main.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokedex.Main.Adapters.PokemonAdapter;
import com.gmail.pokedex.Model.PokemonBrief;
import com.gmail.pokedex.Utils.ProgressBarHelper;
import com.gmail.pokedex.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DexFragment extends Fragment {

    private Context context;
    private ArrayList<PokemonBrief> pokemonList = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView mainPokemonRV;
    private PokemonAdapter pokemonAdapter;
    private ConstraintLayout layout;
    private View searchLayout;
    private FloatingActionButton fab;
    private Animation topToBottomAnim, bottomToTopAnim;
    private EditText searchEditText;
    private int topPadding;
    public DexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dex, container, false);
        context = view.getContext();
        String DATA_URL = String.format("%sdata/all.json", getResources().getString(R.string.cdn));
        RequestQueue mQueue = Volley.newRequestQueue(context);
        topPadding = (int) (40 * getResources().getDisplayMetrics().density + 0.5f);
        mainPokemonRV = view.findViewById(R.id.main_pokemon_RV);
        progressBar = view.findViewById(R.id.main_pokemon_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);
        layout = view.findViewById(R.id.main_pokemon_layout);
        searchLayout = view.findViewById(R.id.main_pokemon_search_layout);
        searchEditText = view.findViewById(R.id.main_pokemon_search_editText);
        TextView cancelText = view.findViewById(R.id.main_pokemon_cancel_textView);
        searchLayout.setVisibility(View.GONE);
        cancelText.setVisibility(View.GONE);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        mainPokemonRV.setLayoutManager(gridLayoutManager);
        mainPokemonRV.setItemAnimator(new DefaultItemAnimator());
        pokemonAdapter = new PokemonAdapter(pokemonList, context);
        mainPokemonRV.setAdapter(pokemonAdapter);

        topToBottomAnim = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom_animation);
        bottomToTopAnim = AnimationUtils.loadAnimation(context, R.anim.bottom_to_top_animation);
        fab =  getActivity().findViewById(R.id.main_fab);

        pbh.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, DATA_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("pokemon");
                            for (int i = 0; i < results.length(); i++){
                                PokemonBrief p = parsePokemon(results.getJSONObject(i));
                                if (p != null){
                                    pokemonList.add(p);
                                }
                            }
                            pokemonAdapter.updateData();
                            pbh.hide();
                        }

                        catch (Exception e) {
                            Toast.makeText(context, "Data unavailable", Toast.LENGTH_SHORT).show();
                            pbh.hide();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Data unavailable", Toast.LENGTH_LONG).show();
                pbh.hide();
                error.printStackTrace();
            }
        });
        mQueue.add(request);

        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearSearch();
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pokemonAdapter.filter(filter(charSequence.toString().toLowerCase()));
                if (charSequence.length()>0){
                    cancelText.setVisibility(View.VISIBLE);
                }
                else{
                    cancelText.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

        mainPokemonRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int vis = fab.getVisibility();
                if (dy > 0){
                    if (vis == View.VISIBLE){
                        hideFab();
                    }
                }
                else if (dy < 0){
                    if (vis == View.GONE){
                        showFab();
                    }
                }
            }
        });

        return view;
    }

    private void showFab(){
        fab.setVisibility(View.VISIBLE);
        fab.startAnimation(bottomToTopAnim);
    }

    private void hideFab(){
        fab.setVisibility(View.GONE);
        fab.startAnimation(topToBottomAnim);

    }

    private void hideSearch(){
        fab.setImageResource(R.drawable.ic_baseline_search_24);
        clearSearch();
        searchLayout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.hide_top_bar));
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        searchLayout.setVisibility(View.GONE);
        mainPokemonRV.setPadding(0,0,0,0);

    }

    private void showSearch(){
        searchLayout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.show_top_bar));
        fab.setImageResource(R.drawable.ic_baseline_close_24);
        searchLayout.setVisibility(View.VISIBLE);
        mainPokemonRV.setPadding(0,topPadding,0,0);
        searchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    private ArrayList<PokemonBrief> filter(String search_string){
        ArrayList<PokemonBrief> filtered = new ArrayList<>();
        for (PokemonBrief p : pokemonList){
            String string = String.format("%s %s", p.getName().toLowerCase(), p.getId());
            if (string.contains(search_string)){
                filtered.add(p);
            }
        }
        return filtered;
    }

    private PokemonBrief parsePokemon(JSONObject obj){
        PokemonBrief p = new PokemonBrief();
        try{
            p.setId(obj.getInt("id"));
            p.setName(obj.getString("name"));
            p.setIcon(obj.getString("icon"));
            p.setLink(obj.getString("link"));
            return p;
        }
        catch (JSONException e){
            return null;
        }
    }

    private void clearSearch(){
        searchEditText.getText().clear();
    }

    private void reset(){
        if (fab.getVisibility() == View.GONE) {
            showFab();
        }
        if (searchLayout.getVisibility() == View.VISIBLE){
            hideSearch();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (fab.getVisibility() == View.GONE) {
            showFab();
        }
    }
}
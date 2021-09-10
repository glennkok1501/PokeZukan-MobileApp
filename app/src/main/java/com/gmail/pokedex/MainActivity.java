package com.gmail.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokedex.Main.Adapters.PokemonAdapter;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonBrief;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PokemonBrief> pokemonList = new ArrayList<>();
    private Context context;
    private ProgressBar progressBar;
    private PokemonAdapter pokemonAdapter;
    private FloatingActionButton fab;
    private ConstraintLayout layout;
    private LinearLayout searchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        String DATA_URL = String.format("%sdata/all.json", getResources().getString(R.string.cdn));
        RequestQueue mQueue = Volley.newRequestQueue(context);

        RecyclerView mainPokemonRV = findViewById(R.id.main_pokemon_RV);
        progressBar = findViewById(R.id.main_pokemon_progressbar);
        fab = findViewById(R.id.main_pokemon_fab);
        layout = findViewById(R.id.main_pokemon_layout);
        searchLayout = findViewById(R.id.main_pokemon_search_layout);
        EditText searchEditText = findViewById(R.id.main_pokemon_search_editText);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        mainPokemonRV.setLayoutManager(gridLayoutManager);
        mainPokemonRV.setItemAnimator(new DefaultItemAnimator());
        pokemonAdapter = new PokemonAdapter(pokemonList, this);
        mainPokemonRV.setAdapter(pokemonAdapter);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, DATA_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressBarStatus(false);
                            JSONArray results = response.getJSONArray("pokemon");
                            for (int i = 0; i < results.length(); i++){
                                PokemonBrief p = parsePokemon(results.getJSONObject(i));
                                if (p != null){
                                    pokemonList.add(p);
                                }
                            }
                            pokemonAdapter.updateData();
                            progressBarStatus(true);
                        }

                        catch (Exception e) {
                            Toast.makeText(context, "Data unavailable", Toast.LENGTH_SHORT).show();
                            progressBarStatus(false);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Data unavailable", Toast.LENGTH_LONG).show();
                progressBarStatus(false);
                error.printStackTrace();
            }
        });
        mQueue.add(request);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pokemonAdapter.filter(filter(charSequence.toString().toLowerCase()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void progressBarStatus(boolean hide){
        int prog_vis;
        int fab_vis;
        int search_vis;
        if (hide){
            prog_vis = View.GONE;
            fab_vis = View.VISIBLE;
            search_vis = View.VISIBLE;
        }
        else{
            prog_vis = View.VISIBLE;
            fab_vis = View.GONE;
            search_vis = View.GONE;
        }
        int finalProg_vis = prog_vis;
        int finalFab_vis = fab_vis;

        progressBar.setVisibility(finalProg_vis);
        fab.setVisibility(finalFab_vis);
        searchLayout.setVisibility(search_vis);

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


}
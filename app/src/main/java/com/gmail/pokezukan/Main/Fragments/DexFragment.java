package com.gmail.pokezukan.Main.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokezukan.Main.Adapters.dex.PokemonAdapter;
import com.gmail.pokezukan.Main.Utils.FabHelper;
import com.gmail.pokezukan.Model.PokemonBrief;
import com.gmail.pokezukan.Utils.ProgressBarHelper;
import com.gmail.pokezukan.R;
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
    private FabHelper fabHelper;
    private FloatingActionButton fab;
    private View view;

    public DexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dex, container, false);
        context = view.getContext();

        String DATA_URL = String.format("%sdata/all.json", getResources().getString(R.string.cdn));
        RequestQueue mQueue = Volley.newRequestQueue(context);
        mainPokemonRV = view.findViewById(R.id.main_pokemon_RV);
        progressBar = view.findViewById(R.id.main_pokemon_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        mainPokemonRV.setLayoutManager(gridLayoutManager);
        mainPokemonRV.setItemAnimator(new DefaultItemAnimator());
        pokemonAdapter = new PokemonAdapter(pokemonList, context);
        mainPokemonRV.setAdapter(pokemonAdapter);

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

        return view;
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

    @Override
    public void onResume() {
        super.onResume();
        initFab();
        fabHelper.checkFabImage();

        fabHelper.getSearchEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pokemonAdapter.filter(filter(charSequence.toString().toLowerCase()));
                if (charSequence.length()>0){
                    fabHelper.getClear().setVisibility(View.VISIBLE);
                }
                else{
                    fabHelper.getClear().setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        fabHelper.checkPause();
    }

    private void initFab(){
        fab =  getActivity().findViewById(R.id.main_fab);
        fabHelper = new FabHelper(context, mainPokemonRV, fab, view, 40);
        fabHelper.setHint("Search by name or ID");

    }
}
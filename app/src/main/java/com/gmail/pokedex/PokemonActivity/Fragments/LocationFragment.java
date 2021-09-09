package com.gmail.pokedex.PokemonActivity.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.pokedex.Main.Adapters.PokemonAdapter;
import com.gmail.pokedex.MainActivity;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.PokemonActivity.Adapters.LocationAdapter;
import com.gmail.pokedex.R;


public class LocationFragment extends Fragment {

    private Pokemon pokemon;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);
        Context context = view.getContext();
        RecyclerView locationRV = view.findViewById(R.id.location_RV);
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        locationRV.setLayoutManager(layoutManager);
        locationRV.setItemAnimator(new DefaultItemAnimator());
        LocationAdapter adapter = new LocationAdapter(pokemon.getLocations());
        locationRV.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }



}
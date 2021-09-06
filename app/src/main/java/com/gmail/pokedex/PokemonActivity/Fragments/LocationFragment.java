package com.gmail.pokedex.PokemonActivity.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.pokedex.Model.Pokemon;
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
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");

        // Inflate the layout for this fragment
        return view;
    }



}
package com.gmail.pokedex.PokemonActivity.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;

import java.util.ArrayList;
import java.util.HashMap;


public class MovesFragment extends Fragment {

    private Pokemon pokemon;

    public MovesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_moves, container, false);
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");

        // Inflate the layout for this fragment
        return view;
    }

}
package com.gmail.pokedex.PokemonActivity.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.AutoCap;
import com.gmail.pokedex.Utils.PokemonSerializer;
import com.gmail.pokedex.Utils.TypeHelper;
import com.gmail.pokedex.WebActivity;


public class MoreFragment extends Fragment {

    private Pokemon pokemon;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        Context context = view.getContext();
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");

        view.post(new Runnable() {
            @Override
            public void run() {
                try {
                    new InitBaseStats(view, pokemon);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
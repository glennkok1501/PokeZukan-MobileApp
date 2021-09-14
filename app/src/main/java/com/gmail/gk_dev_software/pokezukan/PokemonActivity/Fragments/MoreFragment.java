package com.gmail.gk_dev_software.pokezukan.PokemonActivity.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.gk_dev_software.pokezukan.Model.Pokemon;
import com.gmail.gk_dev_software.pokezukan.PokemonActivity.Utils.InitBaseStats;
import com.gmail.gk_dev_software.pokezukan.PokemonActivity.Utils.InitBreeding;
import com.gmail.gk_dev_software.pokezukan.PokemonActivity.Utils.InitTraining;
import com.gmail.gk_dev_software.pokezukan.PokemonActivity.Utils.InitWeaknesses;
import com.gmail.gk_dev_software.pokezukan.R;


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
                    new InitWeaknesses(view, pokemon);
                    new InitTraining(view, pokemon);
                    new InitBreeding(view, pokemon);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
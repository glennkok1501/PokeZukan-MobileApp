package com.gmail.pokezukan.PokemonActivity.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.PokemonActivity.Utils.InitBaseStats;
import com.gmail.pokezukan.PokemonActivity.Utils.InitBreeding;
import com.gmail.pokezukan.PokemonActivity.Utils.InitTraining;
import com.gmail.pokezukan.PokemonActivity.Utils.InitWeaknesses;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.WebActivity;


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
        TextView ref = view.findViewById(R.id.moreFragment_link_textView);

        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("name", pokemon.getName());
                context.startActivity(intent);
            }
        });

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
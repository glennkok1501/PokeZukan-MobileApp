package com.gmail.pokedex.PokemonActivity.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;


public class AboutFragment extends Fragment {

    private Pokemon pokemon;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        pokemon = (Pokemon) getArguments().getSerializable("pokemon");
        ImageView image = view.findViewById(R.id.aboutFragment_pokemon_imageView);
        Glide.with(container.getContext())
                .load(pokemon.getSprites().getOther().getOfficial_artwork().getFront_default())
                .override(250, 250)
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.pokeball)
                .into(image);
        return view;
    }
}
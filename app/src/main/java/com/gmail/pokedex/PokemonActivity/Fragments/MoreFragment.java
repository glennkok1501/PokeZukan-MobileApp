package com.gmail.pokedex.PokemonActivity.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.AutoCap;
import com.gmail.pokedex.Utils.PokemonSerializer;


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
        TextView entry = view.findViewById(R.id.moreFragment_entry_textView);
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");
        entry.setText(new PokemonSerializer().getSummary(pokemon));


        return view;
    }
}
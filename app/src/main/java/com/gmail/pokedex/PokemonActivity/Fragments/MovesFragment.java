package com.gmail.pokedex.PokemonActivity.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonMove;
import com.gmail.pokedex.Model.PokemonMoveVersion;
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

        HashMap<String, ArrayList<PokemonMove>> sortedMoves = sortMove(pokemon.getMoves());

        try{
            for (PokemonMove move : sortedMoves.get("level-up")){
                ArrayList<PokemonMoveVersion> versions = move.getVersion_group_details();
                Log.v("TAG", "LEVEL UP MOVES - "+move.getMove().getName() + " AT LEVEL "+versions.get(versions.size()-1).getLevel_learned_at());
            }
        }
        catch (Exception e){
            Log.v("TAG", "No Moves");
        }

        // Inflate the layout for this fragment
        return view;
    }

    private HashMap<String, ArrayList<PokemonMove>> sortMove(ArrayList<PokemonMove> list){
        HashMap<String, ArrayList<PokemonMove>> map = new HashMap<>();

        for (PokemonMove move : list){
            ArrayList<PokemonMoveVersion> versions = move.getVersion_group_details();
            String method = versions.get(versions.size()-1).getMove_learn_method().getName();
            ArrayList<PokemonMove> tempList;
            if (!map.containsKey(method)){
                tempList = new ArrayList<>();
            }
            else{
                tempList = map.get(method);
            }
            tempList.add(move);
            map.put(method, tempList);
        }

        return map;
    }
}
package com.gmail.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gmail.pokedex.Database.DBHelper;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Main.Adapters.PokemonAdapter;
import com.gmail.pokedex.Network.PokeAPI;
import com.gmail.pokedex.Utils.PokemonComparator;
import com.gmail.pokedex.Utils.PokemonSerializer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pokemon> pokemonList = new ArrayList<>();
    private int POKEMON_LIMIT; //10220
    private int POKEMON_COUNT; //1118
    private PokeAPI pokeAPI;
    private ProgressBar progressBar;
    private RecyclerView mainPokemonRV;
    private PokemonAdapter pokemonAdapter;
    private FloatingActionButton fab;
    private ConstraintLayout layout;
    private LinearLayout searchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        POKEMON_LIMIT = getResources().getInteger(R.integer.pokemon_limit);
        POKEMON_COUNT = getResources().getInteger(R.integer.pokemon_count);
        mainPokemonRV = findViewById(R.id.main_pokemon_RV);
        progressBar = findViewById(R.id.progressBar2);
        fab = findViewById(R.id.main_pokemon_fab);
        layout = findViewById(R.id.main_pokemon_layout);
        searchLayout = findViewById(R.id.main_pokemon_search_layout);
        EditText searchEditText = findViewById(R.id.main_pokemon_search_editText);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        mainPokemonRV.setLayoutManager(gridLayoutManager);
        mainPokemonRV.setItemAnimator(new DefaultItemAnimator());
        pokemonAdapter = new PokemonAdapter(pokemonList);
        mainPokemonRV.setAdapter(pokemonAdapter);

        pokeAPI = new PokeAPI(MainActivity.this);
        pokeAPI.initPokemons(pokemonList, POKEMON_LIMIT);

        new Thread() {
            public void run() {
                try{
                    while (pokemonList.size() < POKEMON_COUNT){
                        progressBarStatus(false);
                        Log.v("TAG", "LIST SIZE - "+pokemonList.size());
                        updateListThread(mainPokemonRV, pokemonAdapter);
                        Thread.sleep(1000);
                    }
                    Log.v("TAG", "LIST SIZE - FINISHED "+pokemonList.size());
                    updateListThread(mainPokemonRV, pokemonAdapter);
                    progressBarStatus(true);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pokemonAdapter.filter(filter(charSequence.toString().toLowerCase()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private ArrayList<Pokemon> filter(String search_string){
        ArrayList<Pokemon> filtered = new ArrayList<>();
        for (Pokemon p : pokemonList){
            String string = String.format("%s %s", p.getName().toLowerCase(), p.getId());
            if (string.contains(search_string)){
                filtered.add(p);
            }
        }
        return filtered;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    private void progressBarStatus(boolean hide){
        int prog_vis;
        int fab_vis;
        int search_vis;
        if (hide){
            prog_vis = View.GONE;
            fab_vis = View.VISIBLE;
            search_vis = View.VISIBLE;
        }
        else{
            prog_vis = View.VISIBLE;
            fab_vis = View.GONE;
            search_vis = View.GONE;
        }
        int finalProg_vis = prog_vis;
        int finalFab_vis = fab_vis;
        layout.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(finalProg_vis);
                fab.setVisibility(finalFab_vis);
                searchLayout.setVisibility(search_vis);
            }
        });
    }

    private void updateListThread(RecyclerView mainPokemonRV, PokemonAdapter pokemonAdapter){
        mainPokemonRV.post(new Runnable() {
            @Override
            public void run() {
                pokemonAdapter.updateData();
            }
        });
    }
}
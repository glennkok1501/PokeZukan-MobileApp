package com.gmail.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pokemon> pokemonList = new ArrayList<>();
    private DBHelper db;
    private PokemonSerializer pokemonSerializer = new PokemonSerializer();
    private final int RETRIEVE_LIMIT = 50;
    private int POKEMON_LIMIT; //10220
    private int POKEMON_COUNT; //1118
    private PokeAPI pokeAPI;
    private ProgressBar progressBar;
    private RecyclerView mainPokemonRV;
    private PokemonAdapter pokemonAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        POKEMON_LIMIT = getResources().getInteger(R.integer.pokemon_limit);
        POKEMON_COUNT = getResources().getInteger(R.integer.pokemon_count);
        mainPokemonRV = findViewById(R.id.main_pokemon_RV);
        progressBar = findViewById(R.id.progressBar2);
        fab = findViewById(R.id.main_pokemon_fab);

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
                        progressBarStatus(progressBar, fab, false);
                        Log.v("TAG", "LIST SIZE - "+pokemonList.size());
                        updateListThread(mainPokemonRV, pokemonAdapter);
                        Thread.sleep(1000);
                    }
                    Log.v("TAG", "LIST SIZE - FINISHED "+pokemonList.size());
                    updateListThread(mainPokemonRV, pokemonAdapter);
                    progressBarStatus(progressBar, fab, true);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    private void progressBarStatus(ProgressBar progressBar, FloatingActionButton fab, boolean hide){
        int prog_vis;
        int fab_vis;
        if (hide){
            prog_vis = View.GONE;
            fab_vis = View.VISIBLE;
        }
        else{
            prog_vis = View.VISIBLE;
            fab_vis = View.GONE;
        }
        int finalProg_vis = prog_vis;
        int finalFab_vis = fab_vis;
        progressBar.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(finalProg_vis);
                fab.setVisibility(finalFab_vis);
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
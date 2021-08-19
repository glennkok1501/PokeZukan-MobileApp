package com.gmail.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.gmail.pokedex.Database.DBHelper;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Main.Adapters.PokemonAdapter;
import com.gmail.pokedex.Network.PokeAPI;
import com.gmail.pokedex.Utils.PokemonSerializer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pokemon> pokemonList = new ArrayList<>();
    private DBHelper db;
    private PokemonSerializer pokemonSerializer = new PokemonSerializer();
    private final int RETRIEVE_LIMIT = 50;
    private int POKEMON_COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        POKEMON_COUNT = getResources().getInteger(R.integer.pokemon_count);
        db = new DBHelper(this, null, null, 1);
        RecyclerView mainPokemonRV = findViewById(R.id.main_pokemon_RV);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
//        mainPokemonRV.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        mainPokemonRV.setLayoutManager(gridLayoutManager);
        mainPokemonRV.setItemAnimator(new DefaultItemAnimator());
        PokemonAdapter pokemonAdapter = new PokemonAdapter(pokemonList);
        mainPokemonRV.setAdapter(pokemonAdapter);

        new Thread() {
            public void run() {
                try{
                    int offset = 0;
                    int count = 0;
                    while (count < POKEMON_COUNT){
                        pokemonList.addAll(pokemonSerializer.RawListToObjList(db.getPokemons(offset, RETRIEVE_LIMIT)));
                        Thread.sleep(100);
                        offset += RETRIEVE_LIMIT;
                        count += RETRIEVE_LIMIT;
                        Log.v("TAG", ""+pokemonList.size());
                        mainPokemonRV.post(new Runnable() {
                            @Override
                            public void run() {
                                pokemonAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
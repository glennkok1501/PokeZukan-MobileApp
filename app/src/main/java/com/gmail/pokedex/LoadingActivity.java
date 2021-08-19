package com.gmail.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmail.pokedex.Database.DBHelper;
import com.gmail.pokedex.Network.PokeAPI;

public class LoadingActivity extends AppCompatActivity {
    private final int POKEMON_LIMIT = 5;
    private final int POKEMON_COUNT = 1118;
    private PokeAPI pokeAPI;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

    }
}
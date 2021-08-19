package com.gmail.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.gmail.pokedex.Database.DBHelper;
import com.gmail.pokedex.Network.PokeAPI;

public class LoadingActivity extends AppCompatActivity {
    private int POKEMON_LIMIT; //10220
    private int POKEMON_COUNT; //1118
    private PokeAPI pokeAPI;
    private DBHelper db;
    public static int LOADING_PAGE_COUNT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        POKEMON_LIMIT = getResources().getInteger(R.integer.pokemon_limit);
        POKEMON_COUNT = getResources().getInteger(R.integer.pokemon_count);
        db = new DBHelper(this, null, null, 1);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        if (db.getPokemonCount() == 0){
            progressBar.setVisibility(View.VISIBLE);
            initData(progressBar);
        }
        else{
            startActivity(new Intent(LoadingActivity.this, MainActivity.class));
            finish();
        }

    }

    private void initData(ProgressBar progressBar){
        new Thread() {
            public void run() {
                try{
                    while (LOADING_PAGE_COUNT < POKEMON_COUNT){
                        progressBar.setProgress((int)getProgress());
                        Thread.sleep(500);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
            }
        }.start();

        pokeAPI = new PokeAPI(LoadingActivity.this);
        pokeAPI.initPokemons(db, POKEMON_LIMIT);
    }

    private double getProgress(){
        return (LOADING_PAGE_COUNT*1.0/POKEMON_COUNT)*100;
    }
}
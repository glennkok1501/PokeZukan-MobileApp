package com.gmail.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.gmail.pokedex.Database.DBHelper;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Main.Adapters.PokemonAdapter;
import com.gmail.pokedex.Network.PokeAPI;
import com.gmail.pokedex.Utils.PokemonSerializer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pokemon> pokemonList;
    private PokeAPI pokeAPI;
    private DBHelper db;
    private PokemonSerializer pokemonSerializer = new PokemonSerializer();
    private final int POKEMON_LIMIT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this, null, null, 1);
        pokeAPI = new PokeAPI(this);
        pokemonList = pokemonSerializer.RawListToObjList(db.getPokemons());

        Log.v("TAG", "LIST SIZE - "+pokemonList.size());


        RecyclerView mainPokemonRV = findViewById(R.id.main_pokemon_RV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mainPokemonRV.setLayoutManager(linearLayoutManager);
        mainPokemonRV.setItemAnimator(new DefaultItemAnimator());
        PokemonAdapter pokemonAdapter = new PokemonAdapter(pokemonList);
        mainPokemonRV.setAdapter(pokemonAdapter);

        if (pokemonList.size() == 0){
            pokeAPI.loadList(pokemonList, pokemonAdapter, db, POKEMON_LIMIT);
        }
//        else{
//            ArrayList<Pokemon> pokemonList = pokemonSerializer.RawListToObjList(rawPokemonList);
//            PokemonAdapter pokemonAdapter = new PokemonAdapter(pokemonList);
//            mainPokemonRV.setAdapter(pokemonAdapter);
//        }
    }
}
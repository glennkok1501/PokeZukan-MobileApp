package com.gmail.pokedex.Network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Utils.PokemonSerializer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://pokeapi.co/
public class PokeAPI {
    private Context context;
    private RequestQueue mQueue;
    private final String MAIN_URL = "https://pokeapi.co/api/v2/pokemon";
    private final String POKEMON_LIMIT_URL = "https://pokeapi.co/api/v2/pokemon?limit=";
    private final PokemonSerializer pokeSerializer = new PokemonSerializer();


    public PokeAPI(Context context) {
        this.context = context;
        mQueue = Volley.newRequestQueue(this.context);
    }

    public void initPokemons(ArrayList<Pokemon> pokemonList, int limit){
        String url = POKEMON_LIMIT_URL+limit;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++){
                                String endpoint = results.getJSONObject(i).getString("url");
                                addPokemonToList(endpoint, pokemonList);
                            }
                        }

                        catch (Exception e) {
                            Toast.makeText(context, "Data unavailable", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Data unavailable", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void addPokemonToList(String endpoint, ArrayList<Pokemon> pokemonList){
        String regex = "\\bhttps://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(endpoint);
        String url;
        if (m.find()){
            url = endpoint;
        }
        else{
            url = String.format("%s/%s",MAIN_URL,endpoint);
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Pokemon pokemon = pokeSerializer.Convert(response);
                            if (pokemon.getOrder() < 1){
                                pokemon.setOrder(pokemon.getId());
                            }
                            pokemonList.add(pokemon);
                        }
                        catch (Exception e) {
                            Toast.makeText(context, "Data unavailable", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Data unavailable", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private boolean filterPokemon(String name){
        String pikachuRegex = "pikachu-[a-zA-Z]";
        Pattern r = Pattern.compile(pikachuRegex);
        return r.matcher(name).find();
    }

}

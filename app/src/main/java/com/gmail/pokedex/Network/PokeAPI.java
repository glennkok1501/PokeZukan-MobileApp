package com.gmail.pokedex.Network;

import android.content.Context;
import android.util.Log;
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
    private final String DATA_URL = "https://gist.githubusercontent.com/glennkok1501/8f91a332afa71878f259cc16ab98c378/raw/b818f9040a9032156a3b9b94415896a86b9f45ee/pokemon.json";
    private final PokemonSerializer pokeSerializer = new PokemonSerializer();


    public PokeAPI(Context context) {
        this.context = context;
        mQueue = Volley.newRequestQueue(this.context);
    }

    public void initPokemons(ArrayList<Pokemon> pokemonList){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, DATA_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("pokemon");
                            for (int i = 0; i < results.length(); i++){
                                pokemonList.add(pokeSerializer.serialize(results.getJSONObject(i)));
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

}

package com.gmail.pokedex.Utils;

import android.util.Log;

import com.gmail.pokedex.Model.NamedAPIResource;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonAbility;
import com.gmail.pokedex.Model.PokemonDreamWorld;
import com.gmail.pokedex.Model.PokemonOfficialArtwork;
import com.gmail.pokedex.Model.PokemonOther;
import com.gmail.pokedex.Model.PokemonSprites;
import com.gmail.pokedex.Model.RawPokemonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonSerializer {

    public PokemonSerializer() {
    }

    public Pokemon Convert(JSONObject obj) throws JSONException {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(obj.getInt("id"));
        pokemon.setName(sanitizeName(obj.getString("name")));
        Log.v("TAG", obj.getString("name"));
        pokemon.setAbilities(getAbilities(obj.getJSONArray("abilities")));
        pokemon.setSprites(getSprites(obj.getJSONObject("sprites")));
        return pokemon;
    }

    private ArrayList<PokemonAbility> getAbilities(JSONArray array) throws JSONException {
        ArrayList<PokemonAbility> abilities = new ArrayList<>();

        for (int i = 0; i < array.length(); i++){
            PokemonAbility ability = new PokemonAbility();
            JSONObject obj = array.getJSONObject(i);
            ability.setIs_hidden(obj.getBoolean("is_hidden"));
            ability.setSlot(obj.getInt("slot"));
            JSONObject resObj = obj.getJSONObject("ability");
            ability.setAbility(getResource(resObj));
            abilities.add(ability);
        }

        return abilities;
    }

    public NamedAPIResource getResource(JSONObject obj) throws JSONException {
        NamedAPIResource res = new NamedAPIResource();
        res.setName(obj.getString("name"));
        res.setUrl(obj.getString("url"));
        return res;
    }

    private PokemonSprites getSprites(JSONObject obj) throws JSONException{
        PokemonSprites sprites = new PokemonSprites();
        Log.v("TAG", obj.toString());
        sprites.setFront_default(obj.getString("front_default"));
        sprites.setFront_shiny(obj.getString("front_shiny"));
        sprites.setFront_female(obj.getString("front_female"));
        sprites.setFront_shiny_female(obj.getString("front_shiny_female"));
        sprites.setBack_default(obj.getString("back_default"));
        sprites.setBack_shiny(obj.getString("back_shiny"));
        sprites.setBack_female(obj.getString("back_female"));
        sprites.setBack_shiny_female(obj.getString("back_shiny_female"));
        sprites.setOther(getOther(obj.getJSONObject("other")));
        return sprites;
    }

    private PokemonOther getOther(JSONObject obj) throws JSONException{
        PokemonOther other = new PokemonOther();

        //dream world
        PokemonDreamWorld dreamWorld = new PokemonDreamWorld();
        JSONObject dreamWorldObj = obj.getJSONObject("dream_world");
        dreamWorld.setFront_default(dreamWorldObj.getString("front_default"));
        dreamWorld.setFront_female(dreamWorldObj.getString("front_female"));

        //official artwork
        PokemonOfficialArtwork artwork = new PokemonOfficialArtwork();
        JSONObject offArtworkObj = obj.getJSONObject("official-artwork");
        artwork.setFront_default(offArtworkObj.getString("front_default"));

        other.setDream_world(dreamWorld);
        other.setOfficial_artwork(artwork);

        return other;
    }

    public String sanitizeName(String name){
        return (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()).replace("_"," ");
    }

    public Pokemon RawToObj(RawPokemonModel raw) throws JSONException {
        return Convert(new JSONObject(raw.getData()));
    }

    public ArrayList<Pokemon> RawListToObjList(ArrayList<RawPokemonModel> rawPokemonList){
        ArrayList<Pokemon> pokemonList = new ArrayList<>();

        for (RawPokemonModel raw : rawPokemonList){
            try{
                Pokemon pokemon = RawToObj(raw);
                pokemonList.add(pokemon);
            }
            catch (Exception e){
                Log.d("ERROR", "Unable to parse JSON");
            }
        }
        return pokemonList;
    }
}

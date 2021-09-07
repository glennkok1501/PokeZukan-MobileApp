package com.gmail.pokedex.Utils;

import android.util.Log;

import com.gmail.pokedex.Model.Breeding;
import com.gmail.pokedex.Model.Gender;
import com.gmail.pokedex.Model.Info;
import com.gmail.pokedex.Model.Location;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.Sprites;
import com.gmail.pokedex.Model.Stats;
import com.gmail.pokedex.Model.Training;
import com.gmail.pokedex.Model.Weakness;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonSerializer {

    public PokemonSerializer() {
    }

    public Pokemon serialize(JSONObject obj) throws JSONException {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(obj.getInt("id"));
        pokemon.setName(obj.getString("name"));
        pokemon.setEntry(obj.getString("entry"));
        pokemon.setLocations(getLocations(obj.getJSONArray("locations")));
        pokemon.setInfo(getInfo(obj.getJSONObject("info")));
        pokemon.setTraining(getTraining(obj.getJSONObject("training")));
        pokemon.setBreeding(getBreeding(obj.getJSONObject("breeding")));
        pokemon.setBase_stats(getStats(obj.getJSONObject("base_stats")));
        pokemon.setWeaknesses(getWeaknesses(obj.getJSONArray("weaknesses")));
        pokemon.setSprites(getSprites(obj.getJSONObject("sprites")));
        return pokemon;
    }

    public String getSummary (Pokemon p){
        return AutoCap.set(String.format("%s, the %s. %s", p.getName(), p.getInfo().getSpecies(), p.getEntry()));
    }

    private Sprites getSprites(JSONObject obj) throws JSONException {
        Sprites s = new Sprites();
        s.setSmall(obj.getString("small"));
        s.setLarge(obj.getString("large"));
        return s;
    }

    private List<Weakness> getWeaknesses(JSONArray array) throws JSONException{
        List<Weakness> weaknesses = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            Weakness w = new Weakness();
            JSONObject obj = array.getJSONObject(i);
            w.setType(obj.getString("type"));
            w.setEffective(obj.getInt("effective"));
            weaknesses.add(w);
        }
        return weaknesses;
    }

    private Stats getStats(JSONObject obj) throws JSONException{
        Stats s = new Stats();
        s.setHp(obj.getInt("hp"));
        s.setAttack(obj.getInt("attack"));
        s.setDefense(obj.getInt("defense"));
        s.setSp_atk(obj.getInt("sp-atk"));
        s.setSp_def(obj.getInt("sp-def"));
        s.setSpeed(obj.getInt("speed"));
        return s;
    }

    private Breeding getBreeding(JSONObject obj) throws JSONException {
        Breeding b = new Breeding();
        b.setEgg_groups(JsonToStringList(obj.getJSONArray("egg_groups")));
        JSONObject genderObj = obj.getJSONObject("gender");
        b.setGender(new Gender(genderObj.getDouble("male"), genderObj.getDouble("female")));
        b.setEgg_cycle(obj.getString("egg_cycle"));
        return b;
    }

    private Training getTraining(JSONObject obj) throws JSONException {
        Training t = new Training();
        t.setEv_yield(obj.getString("ev_yield"));
        t.setCatch_rate(obj.getInt("catch_rate"));
        t.setBase_friendship(obj.getInt("base_friendship"));
        t.setBase_exp(obj.getInt("base_exp"));
        t.setGrowth_rate(obj.getString("growth_rate"));
        return t;
    }

    private List<Location> getLocations(JSONArray array) throws JSONException {
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            Location l = new Location();
            JSONObject obj = array.getJSONObject(i);
            l.setGame(JsonToStringList(obj.getJSONArray("game")));
            l.setArea(JsonToStringList(obj.getJSONArray("area")));
            locations.add(l);
        }
        return locations;
    }

    private List<String> JsonToStringList(JSONArray array){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            try {
                list.add(array.getString(i));
            } catch (JSONException e) {
                list.add(null);
            }
        }
        return list;
    }

    private Info getInfo(JSONObject obj)  throws JSONException {
        Info i = new Info();
        i.setType(JsonToStringList(obj.getJSONArray("type")));
        i.setSpecies(obj.getString("species"));
        i.setHeight(obj.getDouble("height"));
        i.setWeight(obj.getDouble("weight"));
        i.setAbilities(JsonToStringList(obj.getJSONArray("abilities")));
        return i;
    }
}

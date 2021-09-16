package com.gmail.gk_dev_software.pokezukan.Utils;

import android.content.Context;
import android.util.Log;

import com.gmail.gk_dev_software.pokezukan.Model.Breeding;
import com.gmail.gk_dev_software.pokezukan.Model.Gender;
import com.gmail.gk_dev_software.pokezukan.Model.Info;
import com.gmail.gk_dev_software.pokezukan.Model.Pokemon;
import com.gmail.gk_dev_software.pokezukan.Model.PokemonAbility;
import com.gmail.gk_dev_software.pokezukan.Model.PokemonBrief;
import com.gmail.gk_dev_software.pokezukan.Model.PokemonEggCycle;
import com.gmail.gk_dev_software.pokezukan.Model.Sprites;
import com.gmail.gk_dev_software.pokezukan.Model.Stats;
import com.gmail.gk_dev_software.pokezukan.Model.Training;
import com.gmail.gk_dev_software.pokezukan.Model.Weakness;
import com.gmail.gk_dev_software.pokezukan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PokemonSerializer {

    private final String cdn;
    private final String git;

    public PokemonSerializer(Context context) {
        this.cdn = context.getString(R.string.pokezukan_api);
        this.git = context.getString(R.string.git_repo);
    }

    public Pokemon serialize(JSONObject obj) throws JSONException {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(obj.getInt("id"));
        pokemon.setName(obj.getString("name"));
        pokemon.setEntry(obj.getString("entry"));
        pokemon.setLocation(setLink(obj.getString("location")));
        pokemon.setInfo(getInfo(obj.getJSONObject("info")));
        pokemon.setTraining(getTraining(obj.getJSONObject("training")));
        pokemon.setBreeding(getBreeding(obj.getJSONObject("breeding")));
        pokemon.setBase_stats(getStats(obj.getJSONObject("base_stats")));
        pokemon.setWeaknesses(getWeaknesses(pokemon.getInfo().getType()));
        pokemon.setSprites(getSprites(obj.getJSONObject("sprites")));
        pokemon.setMoves(setLink(obj.getString("moves")));
        return pokemon;
    }

    private Sprites getSprites(JSONObject obj) throws JSONException {
        Sprites s = new Sprites();
        s.setHome(setGitLink(obj.getString("home")));
        s.setArtwork(setGitLink(obj.getString("artwork")));
        return s;
    }

    private List<Weakness> getWeaknesses(List<String> t){
        HashMap<String, double[]> chart = new HashMap<>();
        String[] type_ls = new String[]{"normal", "fire", "water", "grass", "electric", "ice", "fighting", "poison", "ground", "flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel", "fairy"};
        chart.put("normal", new double[]{1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1});
        chart.put("fire", new double[]{1, 0.5, 2, 0.5, 1, 0.5, 1, 1, 2, 1, 1, 0.5, 2, 1, 1, 1, 0.5, 0.5});
        chart.put("water", new double[]{1, 0.5, 0.5, 2, 2, 0.5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1});
        chart.put("grass", new double[]{1, 2, 0.5, 0.5, 0.5, 2, 1, 2, 0.5, 2, 1, 2, 1, 1, 1, 1, 1, 1});
        chart.put("electric", new double[]{1, 1, 1, 1, 0.5, 1, 1, 1, 2, 0.5, 1, 1, 1, 1, 1, 1, 0.5, 1});
        chart.put("ice", new double[]{1, 2, 1, 1, 1, 0.5, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1});
        chart.put("fighting", new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0.5, 0.5, 1, 1, 0.5, 1, 2});
        chart.put("poison", new double[]{1, 1, 1, 0.5, 1, 1, 0.5, 0.5, 2, 1, 2, 0.5, 1, 1, 1, 1, 1, 0.5});
        chart.put("ground", new double[]{1, 1, 2, 2, 0, 2, 1, 0.5, 1, 1, 1, 1, 0.5, 1, 1, 1, 1, 1});
        chart.put("flying", new double[]{1, 1, 1, 0.5, 2, 2, 0.5, 1, 0, 1, 1, 0.5, 2, 1, 1, 1, 1, 1});
        chart.put("psychic", new double[]{1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 0.5, 2, 1, 2, 1, 2, 1, 1});
        chart.put("bug", new double[]{1, 2, 1, 0.5, 1, 1, 0.5, 1, 0.5, 2, 1, 1, 2, 1, 1, 1, 1, 1});
        chart.put("rock", new double[]{0.5, 0.5, 2, 2, 1, 1, 2, 0.5, 2, 0.5, 1, 1, 1, 1, 1, 1, 2, 1});
        chart.put("ghost", new double[]{0, 1, 1, 1, 1, 1, 0, 0.5, 1, 1, 1, 0.5, 1, 2, 1, 2, 1, 1});
        chart.put("dragon", new double[]{1, 0.5, 0.5, 0.5, 0.5, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2});
        chart.put("dark", new double[]{1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, 2, 1, 0.5, 1, 0.5, 1, 2});
        chart.put("steel", new double[]{0.5, 2, 1, 0.5, 1, 0.5, 2, 0, 2, 0.5, 0.5, 0.5, 0.5, 1, 0.5, 1, 0.5, 0.5});
        chart.put("fairy", new double[]{1, 1, 1, 1, 1, 1, 0.5, 2, 1, 1, 1, 0.5, 1, 1, 0, 0.5, 2, 1});

        List<Weakness> weaknesses = new ArrayList<>();
        HashMap<String, Double> map = calWeakness(t, chart, type_ls);
        for (String s : map.keySet()){
            Weakness w = new Weakness();
            w.setType(s);
            w.setEffective(map.get(s));
            weaknesses.add(w);
        }
        return weaknesses;
    }

    private HashMap<String, Double> calWeakness(List<String> t, HashMap<String, double[]> chart, String[] type_ls){
        HashMap<String, Double> val = new HashMap<>();
        for (int i = 0; i < t.size(); i++){
            for (int j = 0; j < chart.get(t.get(i)).length; j++){
                try{
                    val.put(type_ls[j], val.get(type_ls[j])*chart.get(t.get(i))[j]);
                }
                catch (Exception e){
                    val.put(type_ls[j], chart.get(t.get(i))[j]);
                }
            }
        }
        return val;
    }

    private Stats getStats(JSONObject obj) throws JSONException{
        Stats s = new Stats();
        s.setHp(obj.getInt("hp"));
        s.setAttack(obj.getInt("attack"));
        s.setDefense(obj.getInt("defense"));
        s.setSp_atk(obj.getInt("sp. atk"));
        s.setSp_def(obj.getInt("sp. def"));
        s.setSpeed(obj.getInt("speed"));
        return s;
    }

    private Breeding getBreeding(JSONObject obj) throws JSONException {
        Breeding b = new Breeding();
        b.setBase_happiness(obj.getInt("base_happiness"));
        b.setEgg_groups(JsonToStringList(obj.getJSONArray("egg_group")));
        JSONObject genderObj = obj.getJSONObject("gender");
        b.setGender(new Gender(genderObj.getDouble("male"), genderObj.getDouble("female")));
        JSONObject eggObj = obj.getJSONObject("egg_cycle");
        b.setEgg_cycle(new PokemonEggCycle(eggObj.getInt("hatch_counter"), eggObj.getInt("steps")));
        return b;
    }

    private Training getTraining(JSONObject obj) throws JSONException {
        Training t = new Training();
        JSONArray evArr = obj.getJSONArray("ev_yield");
        List<String> evList = new ArrayList<>();
        for (int i = 0; i < evArr.length(); i++){
            JSONObject ev = evArr.getJSONObject(i);
            String stat = ev.getString("stat");
            int effort = ev.getInt("effort");
            evList.add(String.format("%s %s", effort, stat));

        }
        t.setEv_yield(evList);
        t.setCatch_rate(obj.getInt("capture_rate"));
        t.setBase_exp(obj.getInt("base_exp"));
        t.setGrowth_rate(obj.getString("growth_rate"));
        return t;
    }

    public List<String> JsonToStringList(JSONArray array){
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
        i.setType(JsonToStringList(obj.getJSONArray("types")));
        i.setSpecies(obj.getString("species"));
        i.setHeight(obj.getDouble("height"));
        i.setWeight(obj.getDouble("weight"));

        JSONArray abArr = obj.getJSONArray("abilities");
        List<PokemonAbility> abList = new ArrayList<>();
        for (int a = 0; a < abArr.length(); a++){
            JSONObject abObj = abArr.getJSONObject(a);
            abList.add(new PokemonAbility(abObj.getString("name"), abObj.getBoolean("is_hidden")));
        }
        i.setAbilities(abList);
        return i;
    }

    public PokemonBrief parsePokemon(JSONObject obj){
        PokemonBrief p = new PokemonBrief();
        try{
            p.setId(obj.getInt("id"));
            p.setName(obj.getString("name"));
            p.setIcon(setGitLink(obj.getString("sprite")));
            p.setLink(setLink(obj.getString("link")));
            return p;
        }
        catch (JSONException e){
            return null;
        }
    }

    public String setGitLink(String link){
        return String.format("%s%s", git, link);
    }

    public String setLink(String link){
        return String.format("%s%s", cdn, link);
    }
}

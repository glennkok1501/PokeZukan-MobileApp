package com.gmail.pokedex.Utils;

import android.util.Log;

import com.gmail.pokedex.Model.NamedAPIResource;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonAbility;
import com.gmail.pokedex.Model.PokemonDreamWorld;
import com.gmail.pokedex.Model.PokemonHeldItem;
import com.gmail.pokedex.Model.PokemonHeldItemVersion;
import com.gmail.pokedex.Model.PokemonMove;
import com.gmail.pokedex.Model.PokemonMoveVersion;
import com.gmail.pokedex.Model.PokemonOfficialArtwork;
import com.gmail.pokedex.Model.PokemonOther;
import com.gmail.pokedex.Model.PokemonSprites;
import com.gmail.pokedex.Model.PokemonStat;
import com.gmail.pokedex.Model.PokemonType;
import com.gmail.pokedex.Model.RawPokemonModel;
import com.gmail.pokedex.Model.VersionGameIndex;

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
        pokemon.setBase_experience(obj.getInt("base_experience"));
        pokemon.setHeight(obj.getInt("height"));
        pokemon.setIs_default(obj.getBoolean("is_default"));
        pokemon.setOrder(obj.getInt("order"));
        pokemon.setWeight(obj.getInt("weight"));
        pokemon.setSprites(getSprites(obj.getJSONObject("sprites")));
        pokemon.setAbilities(getAbilities(obj.getJSONArray("abilities")));
        pokemon.setForms(getForms(obj.getJSONArray("forms")));
        pokemon.setGame_indices(getGameIndices(obj.getJSONArray("game_indices")));
        pokemon.setHeld_items(getHeldItems(obj.getJSONArray("held_items")));
        pokemon.setLocation_area_encounters(obj.getString("location_area_encounters"));
        pokemon.setMoves(getMoves(obj.getJSONArray("moves")));
        pokemon.setSpecies(getResource(obj.getJSONObject("species")));
        pokemon.setStats(getStats(obj.getJSONArray("stats")));
        pokemon.setTypes(getTypes(obj.getJSONArray("types")));

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
                Log.d("ERROR", "Unable to parse JSON - "+raw);
            }
        }
        return pokemonList;
    }

    private ArrayList<NamedAPIResource> getForms(JSONArray array) throws JSONException {
        ArrayList<NamedAPIResource> forms = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            forms.add(getResource(array.getJSONObject(i)));
        }
        return forms;
    }

    private ArrayList<VersionGameIndex> getGameIndices(JSONArray array) throws  JSONException{
        ArrayList<VersionGameIndex> indexes = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            VersionGameIndex index = new VersionGameIndex();
            index.setGame_index(obj.getInt("game_index"));
            index.setVersion(getResource(obj.getJSONObject("version")));
            indexes.add(index);
        }
        return indexes;
    }

    private ArrayList<PokemonHeldItem> getHeldItems(JSONArray array)  throws  JSONException{
        ArrayList<PokemonHeldItem> items = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            PokemonHeldItem item = new PokemonHeldItem();
            item.setItem(getResource(obj.getJSONObject("item")));

            ArrayList<PokemonHeldItemVersion> versions = new ArrayList<>();
            JSONArray versionArray = obj.getJSONArray("version_details");
            for (int j = 0; j < versionArray.length(); j++){
                JSONObject ver_obj = versionArray.getJSONObject(j);
                PokemonHeldItemVersion version = new PokemonHeldItemVersion();
                version.setVersion(getResource(ver_obj.getJSONObject("version")));
                version.setRarity(ver_obj.getInt("rarity"));
                versions.add(version);
            }
            item.setVersion_details(versions);
            items.add(item);
        }
        return items;
    }

    private ArrayList<PokemonMove> getMoves(JSONArray array)  throws  JSONException{
        ArrayList<PokemonMove> moves = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            PokemonMove move = new PokemonMove();
            move.setMove(getResource(obj.getJSONObject("move")));

            ArrayList<PokemonMoveVersion> versions = new ArrayList<>();
            JSONArray moveArray = obj.getJSONArray("version_group_details");
            for (int j = 0; j < moveArray.length(); j++){
                JSONObject ver_obj = moveArray.getJSONObject(j);
                PokemonMoveVersion version = new PokemonMoveVersion();
                version.setLevel_learned_at(ver_obj.getInt("level_learned_at"));
                version.setVersion_group(getResource(ver_obj.getJSONObject("version_group")));
                version.setMove_learn_method(getResource(ver_obj.getJSONObject("move_learn_method")));
                versions.add(version);
            }
            move.setVersion_group_details(versions);
            moves.add(move);
        }
        return moves;
    }

    private ArrayList<PokemonStat> getStats(JSONArray array) throws JSONException{
        ArrayList<PokemonStat> stats = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            PokemonStat stat = new PokemonStat();
            stat.setBase_stat(obj.getInt("base_stat"));
            stat.setEffort(obj.getInt("effort"));
            stat.setStat(getResource(obj.getJSONObject("stat")));
            stats.add(stat);
        }
        return stats;
    }

    private ArrayList<PokemonType> getTypes(JSONArray array) throws JSONException{
        ArrayList<PokemonType> types = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            PokemonType type = new PokemonType();
            type.setSlot(obj.getInt("slot"));
            type.setType(getResource(obj.getJSONObject("type")));
            types.add(type);
        }
        return types;
    }
}

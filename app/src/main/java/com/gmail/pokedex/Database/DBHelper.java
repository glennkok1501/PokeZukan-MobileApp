package com.gmail.pokedex.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.RawPokemonModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pokedex.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_POKEMON = "Pokemon";
    private static final String COLUMN_POKEMON_ID = "Id";
    private static final String COLUMN_POKEMON_DEXID = "DexId";
    private static final String COLUMN_POKEMON_NAME = "Name";
    private static final String COLUMN_POKEMON_DATA = "Data";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POKEMON_TABLE = "CREATE TABLE " + TABLE_POKEMON +
                " (" + COLUMN_POKEMON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_POKEMON_DEXID + " INTEGER, "+
                COLUMN_POKEMON_NAME + " TEXT, "+
                COLUMN_POKEMON_DATA + " TEXT)";
        db.execSQL(CREATE_POKEMON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POKEMON);
        onCreate(db);
    }

    public ArrayList<RawPokemonModel> getPokemons(int offset, int limit){
        ArrayList<RawPokemonModel> pokemonList = new ArrayList<>();
//        String query = "SELECT * FROM " + TABLE_POKEMON +
//                " ORDER BY " + COLUMN_POKEMON_DEXID + " ASC," +
//                COLUMN_POKEMON_NAME + " ASC";
        String query = "SELECT * FROM "+TABLE_POKEMON+" ORDER BY "+COLUMN_POKEMON_DEXID+" ASC, "+COLUMN_POKEMON_NAME+" ASC LIMIT "+offset+","+limit;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int dexid = cursor.getInt(1);
            String name = cursor.getString(2);
            String data = cursor.getString(3);
            RawPokemonModel rawData = new RawPokemonModel(id, dexid, name, data);
            pokemonList.add(rawData);
        }
        cursor.close();
        db.close();
        return pokemonList;
    }

    public int getPokemonCount(){
        String query = "SELECT COUNT(*) FROM " + TABLE_POKEMON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);

        } else {
            count = 0;
        }
        cursor.close();
        db.close();
        return count;
    }

    public void addPokemon(RawPokemonModel rawData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_POKEMON_NAME, rawData.getName());
        values.put(COLUMN_POKEMON_DEXID, rawData.getDexId());
        values.put(COLUMN_POKEMON_DATA, rawData.getData());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_POKEMON, null, values);
        db.close();
    }

    public void clearPokemons(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POKEMON, null, null);
    }

    public RawPokemonModel selectPokemon(int Id){
        String query = "SELECT * FROM " + TABLE_POKEMON + " WHERE " + COLUMN_POKEMON_ID + " = " + Id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        RawPokemonModel rawData;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            int dexid = cursor.getInt(1);
            String name = cursor.getString(2);
            String data = cursor.getString(3);
            rawData = new RawPokemonModel(id, dexid, name, data);
        } else {
            rawData = null;
        }
        cursor.close();
        db.close();
        return rawData;
    }


}

package com.gmail.pokedex.PokemonActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.Model.PokemonBrief;
import com.gmail.pokedex.PokemonActivity.Fragments.AboutFragment;
import com.gmail.pokedex.PokemonActivity.Fragments.MoreFragment;
import com.gmail.pokedex.PokemonActivity.Fragments.LocationFragment;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.PokemonSerializer;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

public class PokemonActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RequestQueue mQueue;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        PokemonSerializer pokemonSerializer = new PokemonSerializer();
        Intent intent = getIntent();
        mQueue = Volley.newRequestQueue(PokemonActivity.this);
        tabLayout = findViewById(R.id.pokemon_tabLayout);
        viewPager = findViewById(R.id.pokemon_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        progressBar = findViewById(R.id.pokemon_progressbar);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, intent.getStringExtra("link"), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Pokemon pokemon = pokemonSerializer.serialize(response);
                            loadData(pokemon);
                            progressBar.setVisibility(View.GONE);

                        }

                        catch (Exception e) {
                            Toast.makeText(PokemonActivity.this, "Data unavailable", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PokemonActivity.this, "Data unavailable", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }

    private void loadData(Pokemon pokemon){
        PokemonViewPagerAdapter adapter = new PokemonViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        Bundle bundle = new Bundle();
        bundle.putSerializable("pokemon", pokemon);

        Fragment movesFragment = new LocationFragment();
        movesFragment.setArguments(bundle);
        adapter.addFragment(movesFragment, "Location");

        Fragment aboutFragment = new AboutFragment();
        aboutFragment.setArguments(bundle);
        adapter.addFragment(aboutFragment, "About");

        Fragment moreFragment = new MoreFragment();
        moreFragment.setArguments(bundle);
        adapter.addFragment(moreFragment, "More");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }
}
package com.gmail.pokezukan.PokemonActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.PokemonActivity.Fragments.AboutFragment;
import com.gmail.pokezukan.PokemonActivity.Fragments.MoreFragment;
import com.gmail.pokezukan.PokemonActivity.Fragments.LocationFragment;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.PokemonSerializer;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class PokemonActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RequestQueue mQueue;
    private ProgressBar progressBar;
    private Fragment aboutFragment, locationFragment, moreFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        PokemonSerializer pokemonSerializer = new PokemonSerializer();
        Intent intent = getIntent();
        mQueue = Volley.newRequestQueue(PokemonActivity.this);
        tabLayout = findViewById(R.id.pokemon_tabLayout);
        viewPager = findViewById(R.id.pokemon_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        progressBar = findViewById(R.id.pokemon_progressbar);
        aboutFragment = new AboutFragment();
        moreFragment = new MoreFragment();
        locationFragment = new LocationFragment();

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

        aboutFragment.setArguments(bundle);
        adapter.addFragment(aboutFragment, "About");

        moreFragment.setArguments(bundle);
        adapter.addFragment(moreFragment, "More");

        locationFragment.setArguments(bundle);
        adapter.addFragment(locationFragment, "Location");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
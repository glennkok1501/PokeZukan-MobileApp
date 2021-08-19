package com.gmail.pokedex.PokemonActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.PokemonActivity.Fragments.AboutFragment;
import com.gmail.pokedex.PokemonActivity.Fragments.MoreFragment;
import com.gmail.pokedex.PokemonActivity.Fragments.MovesFragment;
import com.gmail.pokedex.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.tabs.TabLayout;

public class PokemonActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        Intent intent = getIntent();
        Pokemon pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        tabLayout = findViewById(R.id.pokemon_tabLayout);
        viewPager = findViewById(R.id.pokemon_viewPager);

        tabLayout.setupWithViewPager(viewPager);
        PokemonViewPagerAdapter adapter = new PokemonViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        Bundle bundle = new Bundle();
        bundle.putSerializable("pokemon", pokemon);

        Fragment movesFragment = new MovesFragment();
        movesFragment.setArguments(bundle);
        adapter.addFragment(movesFragment, "Moves");

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
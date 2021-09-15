package com.gmail.gk_dev_software.pokezukan;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_activity);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Resources res = getResources();
        TextView api = findViewById(R.id.about_pokeapi);
        TextView sprites = findViewById(R.id.about_sprite);
        TextView type_ic = findViewById(R.id.about_type);
        TextView move_cat = findViewById(R.id.about_cat);
        TextView pokeball = findViewById(R.id.about_pokeball);

        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectURL(res.getString(R.string.credits_pokeapi_link));
            }
        });
        sprites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectURL(res.getString(R.string.credits_sprites_link));
            }
        });
        type_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectURL(res.getString(R.string.credits_type_link));
            }
        });
        move_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectURL(res.getString(R.string.credits_cat_link));
            }
        });
        pokeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectURL(res.getString(R.string.credits_pokeball_link));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void redirectURL(String url){
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
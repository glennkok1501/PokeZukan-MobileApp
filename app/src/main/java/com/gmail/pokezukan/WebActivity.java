package com.gmail.pokezukan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String name = getIntent().getStringExtra("name").split(" ")[0];

        WebView webView = findViewById(R.id.webAct_webView);
//        webView.loadUrl(getString(R.string.pokemondb)+name);

    }
}
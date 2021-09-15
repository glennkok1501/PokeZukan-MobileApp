package com.gmail.gk_dev_software.pokezukan.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.gk_dev_software.pokezukan.Main.Adapters.MainAdapter;
import com.gmail.gk_dev_software.pokezukan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TabLayout tabLayout;
    private ViewPager2 vp2;
    private MainAdapter mainAdapter;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private final int ABOUT_MENU_ITEM = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        tabLayout = findViewById(R.id.main_tabLayout);
        vp2 = findViewById(R.id.main_viewPager2);
        fab = findViewById(R.id.main_fab);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mainAdapter = new MainAdapter(fragmentManager, getLifecycle());
        vp2.setAdapter(mainAdapter);
        tabLayout.addTab(tabLayout.newTab().setText("Pok\u00e9mon"));
        tabLayout.addTab(tabLayout.newTab().setText("Abilities"));
        tabLayout.addTab(tabLayout.newTab().setText("Moves"));
//        tabLayout.addTab(tabLayout.newTab().setText("Items"));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.add(Menu.NONE, ABOUT_MENU_ITEM, Menu.NONE, "About");
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case ABOUT_MENU_ITEM:
                Log.v("TAG", "ABOUT");
                return true;
            default:
                return false;
        }
    }
}
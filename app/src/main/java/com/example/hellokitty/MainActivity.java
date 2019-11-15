package com.example.hellokitty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Cat> favouriteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main, new SearchFragment()).commit();
        }

        // get the navigation buttons by id
        BottomNavigationView navButtons = findViewById(R.id.nav_buttons);

        // set the listener of the navigation buttons
        navButtons.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment currentFragment = new SearchFragment();
                switch (menuItem.getItemId()) {
                    case R.id.search_btn:
                        currentFragment = new SearchFragment();
                        break;
                    case R.id.favourite_btn:
                        currentFragment = new FavouritesFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main, currentFragment).commit();
                return true;
            }
        });
    }
}

package com.example.foodhubpartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class partner_main_screens extends AppCompatActivity {
    FrameLayout layout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_main_screens);
        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        layout = findViewById(R.id.partner_screens);
        bottomNavigationView = findViewById(R.id.bottomnavbar);
        fragment_shop_initial1 shop_initial1 = new fragment_shop_initial1(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.partner_screens, shop_initial1).commit();

        partner_profile pprofile = new partner_profile(this);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.shop) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.partner_screens, shop_initial1).commit();
                } else if (item.getItemId() == R.id.menu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.partner_screens, shop_initial1).commit();
                } else if (item.getItemId() == R.id.payments) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.partner_screens, shop_initial1).commit();
                } else if (item.getItemId() == R.id.profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.partner_screens, pprofile).commit();
                }
                return true;
            }
        });
    }
}
package com.example.foodhubpartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class partner_main_screens extends AppCompatActivity {
    LinearLayout layout;
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
        layout = findViewById(R.id.fragment_screens);
        bottomNavigationView = findViewById(R.id.bottomnavbar);
        fragment_shop_initial1 shop_initial = new fragment_shop_initial1(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_screens,shop_initial).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.shop){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_screens,shop_initial).commit();
                }
                else if(item.getItemId()==R.id.menu){

                }
                else if(item.getItemId()==R.id.payments){

                }
                else if(item.getItemId()==R.id.profile){

                }
                return false;
            }
        });
    }
}
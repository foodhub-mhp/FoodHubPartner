package com.example.foodhubpartner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checkUserLoggedorNot()) {
                    Intent signintent = new Intent(getApplicationContext(), signin.class);
                    startActivity(signintent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, partner_main_screens.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1000);
    }

    private boolean checkUserLoggedorNot() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            return true;
        }
        else{
            return false;
        }
    }
}
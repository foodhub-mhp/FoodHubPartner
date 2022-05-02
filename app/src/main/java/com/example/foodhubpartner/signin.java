package com.example.foodhubpartner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signin extends AppCompatActivity {
    Button connectwithemail,connectwithphone;
    public static Activity sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        connectwithemail = findViewById(R.id.signinwithmail);
        connectwithphone = findViewById(R.id.signinwithphno);
        sign = this;
        connectwithemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),emaillogin.class);
                startActivity(intent);
            }
        });
        connectwithphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),mobilelogin.class);
                startActivity(intent);
            }
        });
    }
}
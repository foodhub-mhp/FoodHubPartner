package com.example.foodhubpartner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;

public class mobilelogin extends AppCompatActivity {
    Button next;
    TextInputEditText mobileno;
    CountryCodePicker country;
    public static Activity ml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilelogin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        next = findViewById(R.id.next);
        mobileno = findViewById(R.id.phno);
        country = findViewById(R.id.ccpicker);
        ml = this;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phno = mobileno.getText().toString();
                String country_code = country.getSelectedCountryCodeWithPlus();
                if(!phno.isEmpty() && Patterns.PHONE.matcher(phno).matches() && phno.length()==10)
                {
                    //Toast.makeText(mobilelogin.this, "your Mobile no "+country_code+" "+phno+" verified !", Toast.LENGTH_SHORT).show();
                    String code = country.getSelectedCountryCodeWithPlus().toString();
                    Intent intent = new Intent(mobilelogin.this,phoneotpverification.class);
                    intent.putExtra("phno",code+" "+phno);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(mobilelogin.this, "Enter valid Mobile no !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
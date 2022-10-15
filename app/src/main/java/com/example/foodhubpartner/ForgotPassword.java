package com.example.foodhubpartner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotPassword extends AppCompatActivity {
    TextInputEditText emailid;
    TextView gotologin;
    Button resetpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        emailid = findViewById(R.id.femailid);
        gotologin = findViewById(R.id.loginact);
        resetpwd = findViewById(R.id.resetpwdbtn);

        resetpwd.setOnClickListener(v -> {
            String email = emailid.getText().toString();
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Check Your Email to Reset Your Password", Toast.LENGTH_SHORT).show();
            }
            else {
                emailid.setError("Invalid Email Id");
            }
        });
        gotologin.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPassword.this, signin.class);
            startActivity(intent);
            signin.sign.finish();
            emaillogin.el.finish();
            finish();
        });
    }
}
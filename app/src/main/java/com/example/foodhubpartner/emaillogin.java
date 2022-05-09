package com.example.foodhubpartner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class emaillogin extends AppCompatActivity {
    TextInputEditText emailid;
    Button next;
    public static Activity el;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emaillogin);
        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        emailid = findViewById(R.id.emailid);
        next = findViewById(R.id.next);
        el = this;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailid.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    //Toast.makeText(emaillogin.this, "Email "+email+"Verified !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(emaillogin.this,emailotpverification.class);
                    intent.putExtra("emailid",email);
                    startActivity(intent);
                } else {
                    Toast.makeText(emaillogin.this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.foodhubpartner;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.chaos.view.PinView;

public class phoneotpverification extends AppCompatActivity {
    TextView phnoinfo;
    EditText e1, e2, e3, e4, e5, e6;
    //PinView otpText;
    Button verification;
    LottieAnimationView checked;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneotpverification);
        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        phnoinfo = findViewById(R.id.phno_info);
        String phno = phnoinfo.getText().toString() + bundle.getString("phno");
        phnoinfo.setText(phno);
        //otpText = findViewById(R.id.otp);
        checked = findViewById(R.id.check);

        /*verification = findViewById(R.id.verify);
        //otpText.requestFocus();
        verification.setOnClickListener(v->{
            String otp = otpText.getText().toString();
            if (otp.length()==6) {
                if(otp.equals("123456")) {
                    checked.setAnimation(R.raw.lotti_validated);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(phoneotpverification.this, partner_main_screens.class);
                            mobilelogin.ml.finish();
                            signin.sign.finish();
                            startActivity(intent);
                            finish();
                        }

                    }, 1050);
                }
            }
        });*/

        e1 = findViewById(R.id.otpET1);
        e2 = findViewById(R.id.otpET2);
        e3 = findViewById(R.id.otpET3);
        e4 = findViewById(R.id.otpET4);
        e5 = findViewById(R.id.otpET5);
        e6 = findViewById(R.id.otpET6);
        verification = findViewById(R.id.verify);
        checked = findViewById(R.id.check);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    e6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e1.getText().toString().length() == 1 && e2.getText().toString().length() == 1
                        && e3.getText().toString().length() == 1 && e4.getText().toString().length() == 1) {
                    String otpmsg = e1.getText().toString() + e2.getText().toString() + e3.getText().toString() + e4.getText().toString();
                    if (otpmsg.equals("1234")) {
                        //Toast.makeText(phoneotpverification.this, "Valid otp", Toast.LENGTH_SHORT).show();
                        checked.setAnimation(R.raw.lotti_validated);
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(phoneotpverification.this, partner_main_screens.class);
                                mobilelogin.ml.finish();
                                signin.sign.finish();
                                startActivity(intent);
                                finish();
                            }
                        }, 1050);
                    }
                } else {
                    Toast.makeText(phoneotpverification.this, "Please enter otp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
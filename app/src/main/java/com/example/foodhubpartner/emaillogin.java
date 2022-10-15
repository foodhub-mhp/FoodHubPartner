package com.example.foodhubpartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class emaillogin extends AppCompatActivity {
    TextInputEditText emailid, emailpassword;
    Button login;
    LottieAnimationView checked;
    TextView gotoregister,forgotpassword;
    Handler h = new Handler();
    FirebaseAuth firebaseAuth;
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
        emailpassword = findViewById(R.id.emailpassword);
        login = findViewById(R.id.emaillogin);
        checked = findViewById(R.id.check);
        gotoregister = findViewById(R.id.egotoregister);
        forgotpassword = findViewById(R.id.forgotpassword);
        el = this;

        //getInstance for firebaseauth
        firebaseAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailid.getText().toString();
                String email_pwd = emailpassword.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && email_pwd.length()!=0) {
                    //Toast.makeText(emaillogin.this, "Email "+email+"Verified !", Toast.LENGTH_SHORT).show();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(firebaseAuth.getCurrentUser()==null) {
                                firebaselogin(email,email_pwd);
                            }
                        }

                        public boolean firebaselogin(String email, String email_pwd) {
                            boolean flag;
                            firebaseAuth.signInWithEmailAndPassword(email,email_pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        try {
                                            checked.setAnimation(R.raw.lotti_validated);
                                            Toast.makeText(emaillogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(emaillogin.this, partner_main_screens.class);
                                            intent.putExtra("emailid", email);
                                            signin.sign.finish();
                                            startActivity(intent);
                                            finish();
                                        }catch (Exception e)
                                        {
                                            Toast.makeText(emaillogin.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(emaillogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            return false;
                        }
                    }, 1050);
                } else {
                    Toast.makeText(emaillogin.this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gotoregister.setOnClickListener(v->{
            Intent intent = new Intent(emaillogin.this,registration.class);
            startActivity(intent);
        });

        forgotpassword.setOnClickListener(v->{
            Intent intent = new Intent(emaillogin.this,ForgotPassword.class);
            startActivity(intent);
        });
    }
}
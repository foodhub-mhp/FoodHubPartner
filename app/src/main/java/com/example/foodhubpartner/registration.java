package com.example.foodhubpartner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;

public class registration extends AppCompatActivity {
    TextInputEditText rname, rphone, remail, raddress;
    ImageButton rcprofile;
    ImageView rprofilepic;
    TextView rsignin;
    Button create;
    CountryCodePicker country;
    LottieAnimationView checked;
    Uri imageUri;
    int pic_id = 123;
    int gall_pic_id = 124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        create = findViewById(R.id.rcreate);
        rname = findViewById(R.id.rname);
        rphone = findViewById(R.id.rphoneno);
        remail = findViewById(R.id.remail);
        raddress = findViewById(R.id.raddress);
        rprofilepic = findViewById(R.id.dprofilepic);
        rcprofile = findViewById(R.id.pickbutton);
        country = findViewById(R.id.ccpicker);
        rsignin = findViewById(R.id.rsignin);

        rname.setScroller(new Scroller(this));
        rname.setMaxLines(1);
        rname.setHorizontallyScrolling(true);
        rname.setMovementMethod(new ScrollingMovementMethod());

        remail.setScroller(new Scroller(this));
        remail.setMaxLines(1);
        remail.setVerticalScrollBarEnabled(true);
        remail.setMovementMethod(new ScrollingMovementMethod());

        raddress.setScroller(new Scroller(this));
        raddress.setMaxLines(5);
        raddress.setVerticalScrollBarEnabled(true);
        raddress.setMovementMethod(new ScrollingMovementMethod());

        rsignin.setOnClickListener(v->{
            Intent intent = new Intent(this, signin.class);
            startActivity(intent);
            signin.sign.finish();
            finish();
        });
        rcprofile.setOnClickListener(v -> {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.imagepicker_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            AlertDialog imagepicker = builder.create();
            imagepicker.setCanceledOnTouchOutside(true);
            imagepicker.setTitle("Chose The Options");

            ImageButton camera = view.findViewById(R.id.rdcamera);
            ImageButton gallery = view.findViewById(R.id.rdgallery);
            imagepicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(i==DialogInterface.BUTTON_NEGATIVE){
                        imagepicker.dismiss();
                    }
                }
            });

            imagepicker.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove image", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(i==DialogInterface.BUTTON_NEUTRAL){
                        rprofilepic.setImageResource(R.drawable.profile24);
                    }
                }
            });
            imagepicker.show();
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, pic_id);
                    imagepicker.dismiss();
                }
            });
            gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(intent, gall_pic_id);
                    imagepicker.dismiss();
                }
            });
        });

        create.setOnClickListener(v -> {
            if (rname.getText().toString().isEmpty()) {
                rname.setError("Please Enter this field");
            }
            if (rphone.getText().toString().isEmpty()) {
                rphone.setError("Please Enter this field");
            }
            if (remail.getText().toString().isEmpty()) {
                remail.setError("Please Enter this field");
            }
            if (raddress.getText().toString().isEmpty()) {
                raddress.setError("Please Enter this field");
            }
            if (!rname.getText().toString().isEmpty() && !rphone.getText().toString().isEmpty() && !remail.getText().toString().isEmpty() && !raddress.getText().toString().isEmpty()) {
                int flag = 0;
                String country_code = country.getSelectedCountryCodeWithPlus();
                String email = remail.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    flag += 1;
                } else {
                    remail.setError("Invalid Email");
                }
                String phno = rphone.getText().toString();
                if (!phno.isEmpty() && Patterns.PHONE.matcher(phno).matches() && phno.length() == 10) {
                    flag += 1;
                } else {
                    rphone.setError("Invalid Phone no");
                }
                if (flag == 2) {
                    openRegisterDialog();
                    signin.sign.finish();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 124) {
            try {
                imageUri = data.getData();
                if (imageUri != null)
                    rprofilepic.setImageURI(imageUri);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (requestCode == 123) {
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                rprofilepic.setImageBitmap(photo);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void openRegisterDialog() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.registration_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        AlertDialog registerdialog = builder.create();
        registerdialog.setCanceledOnTouchOutside(true);

        //decalring fields
        Button dok, signin;
        dok = view.findViewById(R.id.rdok);
        signin = view.findViewById(R.id.rdsignin);
        checked = view.findViewById(R.id.rdcheck);
        checked.setAnimation(R.raw.lotti_validated);
        registerdialog.show();
        dok.setOnClickListener(v -> {
            registerdialog.dismiss();
        });
        signin.setOnClickListener(v -> {
            Intent intent = new Intent(this, signin.class);
            startActivity(intent);
            this.finish();
        });
    }
}
package com.example.foodhubpartner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hbb20.CountryCodePicker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class registration extends AppCompatActivity {

    TextInputEditText rname, rphone, remail, raddress, rpassword;
    ImageButton rcprofile;
    CircleImageView rprofilepic;
    //ImageView rprofilepic;
    TextView rsignin;
    Button create;
    Button location;
    CountryCodePicker country;
    LottieAnimationView checked;
    ProgressBar progressBar;
    FirebaseAuth partnerAuth;
    String mapp_address = "";

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
        rpassword = findViewById(R.id.rpassword);
        rprofilepic = findViewById(R.id.dprofilepic);
        rcprofile = findViewById(R.id.pickbutton);
        country = findViewById(R.id.ccpicker);
        rsignin = findViewById(R.id.rsignin);
        progressBar = findViewById(R.id.progressbar);
        location = findViewById(R.id.location);

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

        partnerAuth = FirebaseAuth.getInstance();

        rsignin.setOnClickListener(v -> {
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
                    if (i == DialogInterface.BUTTON_NEGATIVE) {
                        imagepicker.dismiss();
                    }
                }
            });

            imagepicker.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove image", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_NEUTRAL) {
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

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(registration.this, MapsActivity.class), 100);
            }
        });


        //create button listener
        create.setOnClickListener(v -> {
            int flag = 0;
            if (rname.getText().toString().isEmpty()) {
                rname.setError("Please Enter this field");
                rname.requestFocus();
                return;
            }

            if (rphone.getText().toString().isEmpty()) {
                rphone.setError("Please Enter this field");
                rphone.requestFocus();
                return;
            } else if (!rphone.getText().toString().isEmpty() && Patterns.PHONE.matcher(rphone.getText().toString()).matches() && rphone.getText().toString().length() == 10) {
                flag += 1;
            } else {
                rphone.setError("Invalid Phone no");
                rphone.requestFocus();
                return;
            }

            if (remail.getText().toString().isEmpty()) {
                remail.setError("Please Enter this field");
                remail.requestFocus();
                return;
            } else if (Patterns.EMAIL_ADDRESS.matcher(remail.getText().toString()).matches()) {
                flag += 1;
            } else {
                remail.setError("Invalid Email");
                remail.requestFocus();
                return;
            }

            if (rpassword.getText().toString().isEmpty()) {
                rpassword.setError("Please Enter This field");
                rpassword.requestFocus();
                return;
            } else if (rpassword.getText().toString().length() >= 6 && isValidPassword(rpassword.getText().toString())) {
                flag += 1;
            } else {
                rpassword.setError("A Password must contains minimum 6  characters\nOne Capital character,\nOne Small character,\nOne Special character,\nOne Number");
                rpassword.requestFocus();
                return;
            }

            if (raddress.getText().toString().isEmpty()) {
                raddress.setError("Please Enter this field");
                raddress.requestFocus();
                return;
            }


            String country_code = country.getSelectedCountryCodeWithPlus();
            String email = remail.getText().toString();
            if (flag == 3) {
                String pname, pphno, pemail, ppassword, paddress;
                pname = rname.getText().toString();
                pphno = country_code + rphone.getText().toString();
                pemail = email;
                ppassword = rpassword.getText().toString();
                paddress = raddress.getText().toString();

                registerPartner(pname, pphno, pemail, ppassword, paddress);
                signin.sign.finish();
            }
        });

    }

    //Firebase authentication code
    private void registerPartner(String pname, String pphno, String pemail, String ppassword, String paddress) {
        progressBar.setVisibility(View.VISIBLE);
        //dissable the user interaction when progress bas is vissible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        partnerAuth.createUserWithEmailAndPassword(pemail, ppassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //if partner didn't upload his/her profile then by default we are giving the our android local pic i.e profile24
                            if (imageUri == null) {
                                imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                                        "://" + getResources().getResourcePackageName(R.drawable.profile24)
                                        + '/' + getResources().getResourceTypeName(R.drawable.profile24) + '/' + getResources().getResourceEntryName(R.drawable.profile24));
                            }

                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference uploader = storage.getReference("profilePics" + new Random().nextInt(50));
                            uploader.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    PartnerRegister partner = new PartnerRegister(pname, pphno, pemail, paddress, uri.toString());
                                                    FirebaseDatabase.getInstance().getReference("partners")
                                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("partner_details").setValue(partner)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Toast.makeText(registration.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                                                        progressBar.setVisibility(View.GONE);
                                                                        openRegisterDialog();
                                                                        rname.setText("");
                                                                        remail.setText("");
                                                                        rpassword.setText("");
                                                                        raddress.setText("");
                                                                        rphone.setText("");
                                                                        rprofilepic.setImageResource(R.drawable.profile24);
                                                                        //enable the user for interaction
                                                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                                    }
                                                                }
                                                            });
                                                }
                                            });
                                        }
                                    });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registration.this, "" + e, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        //enable the user to get interaction
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                });
    }

    //Activity result for Images
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
                progressBar.setVisibility(View.GONE);
            }
        }
        if (requestCode == 100) {
            if (data == null) return;
            raddress.setText(data.getStringExtra("address"));
        }

    }


    //when onsuccessful registration this dialog will be opened
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


    //This method is used for validating the user password
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
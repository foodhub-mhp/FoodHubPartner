package com.example.foodhubpartner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shop_register extends AppCompatActivity {
    TextInputEditText from_time, to_time, shop_name, shop_address, shop_contact_no, shop_email_id, shop_Upi_Id, shop_Gst_no;
    Button shop_register, shop_location;
    String fromtime = "", totime = "";
    ImageView shopimage;
    Uri imageUri;
    ProgressBar progressBar;
    int pic_id = 123;
    int gall_pic_id = 124;
    public int shop_count;

    //firebase variables
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        //setting up variable id's
        shop_name = findViewById(R.id.shopname);
        shop_address = findViewById(R.id.shop_address);
        shop_contact_no = findViewById(R.id.shop_contact_no);
        shop_email_id = findViewById(R.id.shop_email_Id);
        shop_Upi_Id = findViewById(R.id.shop_UPI_Id);
        shop_Gst_no = findViewById(R.id.shop_Gst_No);
        from_time = findViewById(R.id.from_time);
        to_time = findViewById(R.id.to_time);
        shopimage = findViewById(R.id.shop_image);
        shop_register = findViewById(R.id.shop_register);
        shop_location = (Button) findViewById(R.id.shop_location);
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);

        //initializing firebase variables
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        //From Time Listener
        from_time.setOnClickListener(v -> {
            View view = LayoutInflater.from(this).inflate(R.layout.timepicker_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            AlertDialog from_timepicker_dialog = builder.create();
            from_timepicker_dialog.setCanceledOnTouchOutside(true);
            from_timepicker_dialog.setTitle("Choose From Timings");

            TimePicker ft = (TimePicker) view.findViewById(R.id.time1);

            from_timepicker_dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        int hour = ft.getCurrentHour();
                        int min = ft.getCurrentMinute();
                        String format = "";

                        if (hour == 0) {
                            hour += 12;
                            format = "AM";
                        } else if (hour == 12) {
                            format = "PM";
                        } else if (hour > 12) {
                            hour -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        String minute = "";
                        if (min < 10) {
                            minute += "0" + min;
                        } else {
                            minute += min;
                        }
                        fromtime = "" + hour + " : " + minute + " " + format;
                        from_time.setText(fromtime);
                        from_timepicker_dialog.dismiss();
                    }
                }
            });
            from_timepicker_dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_NEGATIVE) {
                        if (fromtime.isEmpty()) {
                            from_time.setText("From");
                        }
                        from_timepicker_dialog.dismiss();
                    }
                }
            });
            from_timepicker_dialog.show();
        });

        //To Time Listener
        to_time.setOnClickListener(v -> {
            View view = LayoutInflater.from(this).inflate(R.layout.timepicker_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            AlertDialog from_timepicker_dialog = builder.create();
            from_timepicker_dialog.setCanceledOnTouchOutside(true);
            from_timepicker_dialog.setTitle("Choose End Timings");

            TimePicker et = (TimePicker) view.findViewById(R.id.time1); //et means End time

            from_timepicker_dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        int hour = et.getCurrentHour();
                        int min = et.getCurrentMinute();
                        String format = "";

                        if (hour == 0) {
                            hour += 12;
                            format = "AM";
                        } else if (hour == 12) {
                            format = "PM";
                        } else if (hour > 12) {
                            hour -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        String minute = "";
                        if (min < 10) {
                            minute += "0" + min;
                        } else {
                            minute += min;
                        }
                        totime = "" + hour + " : " + minute + " " + format;
                        to_time.setText(totime);
                        from_timepicker_dialog.dismiss();
                    }
                }
            });
            from_timepicker_dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_NEGATIVE) {
                        if (totime.isEmpty()) {
                            from_time.setText("To");
                        }
                        from_timepicker_dialog.dismiss();
                    }
                }
            });
            from_timepicker_dialog.show();
        });

        //Adding listener to the upload shop image button
        shopimage.setOnClickListener(v -> {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.imagepicker_dialog, null);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setView(view);
            android.app.AlertDialog imagepicker = builder.create();
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
                        shopimage.setImageResource(R.drawable.uploadimage);
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

        shop_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Shop_register.this, MapsActivity.class), 100);
            }
        });

        //register button listener
        shop_register.setOnClickListener(v -> {
            String shopname, shopcontactno, shopemailid, shopaddress, shopupiid, shopgstno;

            shopname = shop_name.getText().toString();
            shopcontactno = shop_contact_no.getText().toString();
            shopemailid = shop_email_id.getText().toString();
            shopaddress = shop_address.getText().toString();
            shopupiid = shop_Upi_Id.getText().toString();
            shopgstno = shop_Gst_no.getText().toString();

            //validating required fields
            if (!shopname.isEmpty() && !shopcontactno.isEmpty() && !shopemailid.isEmpty() && !shopaddress.isEmpty() && !shopupiid.isEmpty() && !shopgstno.isEmpty() && !fromtime.isEmpty() && !totime.isEmpty()) {
                int flag = 0;
                if (!shopemailid.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(shopemailid).matches()) {
                    flag += 1;
                    System.out.println("email");
                } else {
                    shop_email_id.setError("Invalid Email");
                }

                if (!shopcontactno.isEmpty() && Patterns.PHONE.matcher(shopcontactno).matches() && shopcontactno.length() == 10) {
                    flag += 1;
                    System.out.println("phone no");
                } else {
                    shop_contact_no.setError("Invalid Phone no");
                }

                if (shopaddress.length() < 20) {
                    shop_address.setError("Address must contains atleast 20 characters/letters");
                } else {
                    flag++;
                    System.out.println("address");
                }
                //validating UPI Id
                Pattern p = Pattern.compile("^(.+)@(.+)$");
                Matcher m = p.matcher(shopupiid);
                boolean b = m.matches();
                if (b) {
                    flag++;
                    System.out.println("UPI");
                } else {
                    shop_Upi_Id.setError("Invalid UPI id");
                }
                //validating GST No
                Pattern p2 = Pattern.compile("^[0-9]{2}[A-Z]{5}[0-9]{4}" + "[A-Z]{1}[1-9A-Z]{1}" + "Z[0-9A-Z]{1}$");
                Matcher m2 = p2.matcher(shopgstno);
                boolean b2 = m2.matches();
                if (b2) {
                    flag++;
                    System.out.println("GST");
                } else {
                    shop_Gst_no.setError("Invalid GST No");
                }
                if (flag == 5) {
                    progressBar.setVisibility(View.VISIBLE);
                    //dissable the user interaction when progress bas is vissible
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    reference = FirebaseDatabase.getInstance().getReference("partners").child(firebaseAuth.getCurrentUser().getUid());
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            shop_count = Integer.parseInt(snapshot.child("partner_details").child("shop_count").getValue(String.class));
                            reference.child("partner_details").child("shop_count").setValue(""+(shop_count+1));
                            if (imageUri == null) {

                                //if partner didn't upload his/her shop pic then by default we are giving the our android local pic i.e profile24
                                imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                                        "://" + getResources().getResourcePackageName(R.drawable.shop)
                                        + '/' + getResources().getResourceTypeName(R.drawable.shop) + '/' + getResources().getResourceEntryName(R.drawable.shop));
                            }
                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference uploader = storage.getReference("shop_profile_Pics" + new Random().nextInt(50));
                            uploader.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    ShopRegistration shopRegistration = new ShopRegistration(fromtime.toString(),totime.toString(),shopname.toString(),shopaddress.toString(),shopcontactno.toString(),shopemailid.toString(),shopupiid.toString(),shopgstno.toString(),uri.toString());

                                                    FirebaseDatabase.getInstance().getReference("partners")
                                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("shops").child("shopName_"+shopname.toString()).setValue(shopRegistration)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    Toast.makeText(Shop_register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                                                                    //enable the user for interaction
                                                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                                    Intent intent = new Intent(Shop_register.this,partner_main_screens.class);
                                                                    startActivity(intent);
                                                                    Shop_register.this.finish();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(Shop_register.this, "" + e, Toast.LENGTH_SHORT).show();
                                                                    progressBar.setVisibility(View.GONE);
                                                                    //enable the user to get interaction
                                                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                                }
                                                            });

                                                }
                                            });
                                        }
                                    });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            } else {
                if (shopname.isEmpty()) {
                    shop_name.setError("This Field must required");
                }
                if (shopcontactno.isEmpty()) {
                    shop_contact_no.setError("This Field must required");
                }
                if (shopemailid.isEmpty()) {
                    shop_email_id.setError("This Field must required");
                }
                if (shopaddress.isEmpty()) {
                    shop_address.setError("This Field must required");
                }
                if (shopupiid.isEmpty()) {
                    shop_Upi_Id.setError("This Field must required");
                }
                if (shopgstno.isEmpty()) {
                    shop_Gst_no.setError("This Field must required");
                }
                if (from_time.getText().toString().isEmpty() && to_time.getText().toString().isEmpty()) {
                    from_time.setError("This Field must required");
                    to_time.setError("This Field must required");
                } else {
                    from_time.setError(null);
                    to_time.setError(null);
                }
            }
        });
    }

    //Gets result when user selects the image from either gallery or from camera
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 124) {
            try {
                imageUri = data.getData();
                if (imageUri != null)
                    shopimage.setImageURI(imageUri);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (requestCode == 123) {
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                shopimage.setImageBitmap(photo);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (requestCode == 100) {
            if (data == null) return;
            shop_address.setText(data.getStringExtra("address"));
        }
    }
}
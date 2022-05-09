package com.example.foodhubpartner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

public class Shop_register extends AppCompatActivity {
    TextInputEditText from_time, to_time;
    String fromtime, totime;
    ImageView shopimage;
    Uri imageUri;
    int pic_id = 123;
    int gall_pic_id = 124;

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
        from_time = findViewById(R.id.from_time);
        to_time = findViewById(R.id.to_time);
        shopimage = findViewById(R.id.shop_image);

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
                    if(i==DialogInterface.BUTTON_NEGATIVE){
                        imagepicker.dismiss();
                    }
                }
            });

            imagepicker.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove image", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(i==DialogInterface.BUTTON_NEUTRAL){
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
    }
}
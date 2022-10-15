package com.example.foodhubpartner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class partner_profile extends Fragment {
    Context context;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    DatabaseReference reference;

    TextView username,partnerphno,partneremail,partneraddress;
    CircleImageView pprofile;
    Button logout;
    public partner_profile(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_partner_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        username = (TextView) view.findViewById(R.id.partner_username);
        pprofile = (CircleImageView) view.findViewById(R.id.pprofilepic);
        partnerphno = (TextView) view.findViewById(R.id.partnerphno);
        partneremail = (TextView) view.findViewById(R.id.partneremail);
        partneraddress = (TextView) view.findViewById(R.id.partner_address);
        logout = (Button) view.findViewById(R.id.partner_logout);

        if(firebaseAuth.getCurrentUser()!=null)
        {
            partneremail.setText(firebaseAuth.getCurrentUser().getEmail());
            reference = FirebaseDatabase.getInstance().getReference("partners").child(firebaseAuth.getCurrentUser().getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child("partner_details").child("partner_name").getValue().toString();
                    String phno = snapshot.child("partner_details").child("partner_mobileno").getValue().toString();
                    String address = snapshot.child("partner_details").child("partner_address").getValue().toString();
                    username.setText(name);
                    partnerphno.setText(phno);
                    partneraddress.setText(address);

                    //loading image from the database
                    String link = snapshot.child("partner_details").child("partnerProfile").getValue(String.class);
                    Picasso.get().load(link).into(pprofile);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

        logout.setOnClickListener(v->{
            firebaseAuth.signOut();
            Toast.makeText(context, "Logged Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,signin.class);
            startActivity(intent);
            getActivity().finishAffinity();
        });
        return view;
    }
}
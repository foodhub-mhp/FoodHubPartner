package com.example.foodhubpartner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class fragment_shop_initial1 extends Fragment {
    ImageButton initial_shop_add;
    Context initial_shop_context;
    public fragment_shop_initial1(Context context) {
        // Required empty public constructor
        initial_shop_context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_initial1, container, false);

        partner_main_screens obj = new partner_main_screens();
        initial_shop_add = view.findViewById(R.id.add_initial_shop);
        initial_shop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(initial_shop_context,Shop_register.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
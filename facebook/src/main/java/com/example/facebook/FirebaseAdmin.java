package com.example.facebook;

import com.example.facebook.DataHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mrwhite on 29/1/18.
 */

public class FirebaseAdmin {

    public FirebaseAuth mAuth;
    public FirebaseDatabase database;
    public DatabaseReference myRef;



    public FirebaseAdmin() {

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef =  database.getReference();

    }






}

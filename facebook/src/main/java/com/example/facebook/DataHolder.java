package com.example.facebook;

import org.json.JSONObject;

/**
 * Created by mrwhite on 29/1/18.
 */

public class DataHolder {
    public static DataHolder instance = new DataHolder();

        public static JSONObject jsonObject;
        public static FirebaseAdmin firebaseAdmin;

        public DataHolder(){
            firebaseAdmin= new FirebaseAdmin();}


    public void firebaseAdmin(FirebaseAdmin firebaseAdmin) {
    }
}

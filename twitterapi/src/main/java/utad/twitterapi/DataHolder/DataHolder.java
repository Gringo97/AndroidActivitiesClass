package utad.twitterapi.DataHolder;

import org.json.JSONObject;

import utad.twitterapi.FireBase.FirebaseAdmin;

/**
 * Created by oscar.fuente on 29/01/2018.
 */

public class DataHolder {
    public static  DataHolder instance = new DataHolder();
    public static  JSONObject jsonObjectTwitter;
    public static FirebaseAdmin firebaseAdmin;
    public FirebaseAdmin fireBaseAdmin;
    public DataHolder(){
        fireBaseAdmin = new FirebaseAdmin();
    }

    }


package utad.pushnotification;

import java.util.HashMap;

import utad.pushnotification.FireBase.FireBaseAdmin;
import utad.pushnotification.SQLite.Contact;

/**
 * Created by Oscar on 16/12/2017.
 */

public class DataHolder {

    public static  DataHolder instance = new DataHolder();

    public FireBaseAdmin fireBaseAdmin;

    public String API_KEY = "b2aca12ec8478a02295d7d9671dc0650";

    public HashMap<Integer,Contact> usuarioHashMap;

    public DataHolder(){
        fireBaseAdmin = new FireBaseAdmin();
    }
}

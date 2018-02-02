package utad.pushnotification;

import utad.pushnotification.FireBase.FireBaseAdmin;

/**
 * Created by Oscar on 16/12/2017.
 */

public class DataHolder {

    public static  DataHolder instance = new DataHolder();

    public FireBaseAdmin fireBaseAdmin;

    public String API_KEY = "b2aca12ec8478a02295d7d9671dc0650";

    public DataHolder(){
        fireBaseAdmin = new FireBaseAdmin();
    }
}

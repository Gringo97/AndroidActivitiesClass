package fuente.oscar.sqlitecrashreport;

/**
 * Created by Oscar on 16/12/2017.
 */

public class DataHolder {

    public static  DataHolder instance = new DataHolder();

    public FireBaseAdmin fireBaseAdmin;

  
    public DataHolder(){
        fireBaseAdmin = new FireBaseAdmin();
    }
}

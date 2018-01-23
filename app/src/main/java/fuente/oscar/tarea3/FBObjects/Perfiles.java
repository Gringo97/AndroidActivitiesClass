package fuente.oscar.tarea3.FBObjects;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oscar.fuente on 16/01/2018.
 */

public class Perfiles {

    double lat,lon;



    public Perfiles(){}


    public Perfiles(double lat, double lon){
        this.lat = lat;
        this.lon = lon;

    }



    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("lat",lat);
        result.put("lon",lon);
        return result;
    }
}

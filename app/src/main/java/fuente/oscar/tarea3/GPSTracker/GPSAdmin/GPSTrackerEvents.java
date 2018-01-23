package fuente.oscar.tarea3.GPSTracker.GPSAdmin;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import fuente.oscar.tarea3.DataHolder;
import fuente.oscar.tarea3.FBObjects.Perfiles;

/**
 * Created by oscar.fuente on 15/01/2018.
 */

public class GPSTrackerEvents implements LocationListener{
    GPSTracker gpsTracker;



    public GPSTrackerEvents( GPSTracker gpsTracker){

        this.gpsTracker =   gpsTracker;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("GPS LOCATION"," Long:"+gpsTracker.getLongitude()+"  Lat:"+gpsTracker.getLatitude());
        gpsTracker.listener.devolverLatLong(gpsTracker.getLatitude(),gpsTracker.getLongitude());
        DataHolder.instance.fireBaseAdmin.writeNewPost("/Perfiles/", new Perfiles(location.getLatitude(),location.getLongitude()).toMap());




    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

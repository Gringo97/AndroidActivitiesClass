package fuente.oscar.tarea3.FBObjects;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by Oscar on 17/12/2017.
 */

public class Ensalada {
    public String descripcion;
    public String precio;
    public String nombre;
    public String imgUrl;
    public double lat;
    public double lon;
    public Marker marker;

    public Ensalada(){}

    public Ensalada(String descripcion, String precio, String nombre,String imgUrl,double lat, double lon) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombre = nombre;
        this.imgUrl = imgUrl;
        this.lat = lat;
        this.lon = lon;
    }

    public void setMarkerEnsalada(Marker markerEnsalada){
        this.marker = markerEnsalada;
    }
    public Marker getMarkerEnsalada(){
        return marker;
    }
}

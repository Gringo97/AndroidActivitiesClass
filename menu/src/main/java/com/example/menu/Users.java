package com.example.menu;

/**
 * Created by Oscar on 02/02/2018.
 */

public class Users {
    public  String nombre;
    public float userLat,usetLon;

    public Users (){}

    public Users(String name,Float lat, Float lon){
        this.nombre = name;
        this.userLat = lat;
        this.usetLon = lon;
    }

    public String getUsername() {
        return nombre;
    }

    public float getUserLat() {
        return userLat;
    }

    public void setUserLat(float userLat) {
        this.userLat = userLat;
    }

    public float getUsetLon() {
        return usetLon;
    }

    public void setUsetLon(float usetLon) {
        this.usetLon = usetLon;
    }

    public void setUsername(String username) {
        this.nombre = username;
    }
}

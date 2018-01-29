package com.example.facebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SecondActivity extends AppCompatActivity {

    private TextView txtNombrePerfil;
    private TextView txtCumple;
    private TextView txtEmail;
    private ImageView imgPerfilFacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.txtNombrePerfil = this.findViewById(R.id.txtNombreFacebook);
        this.imgPerfilFacebook = this.findViewById(R.id.imgPerfilFacebook);
        this.txtCumple = this.findViewById(R.id.txtCumple);
        this.txtEmail = this.findViewById(R.id.txtEmail);
        this.setData();

    }

    private void setData(){

        try {
            JSONObject data = DataHolder.instance.jsonObject;
            this.txtNombrePerfil.setText(data.get("name").toString());
            this.txtCumple.setText(data.get("birthday").toString());
            this.txtEmail.setText(data.get("email").toString());
            if (data.has("picture")) {
                System.out.println("La url de la img de facebook es: " + data.getJSONObject("picture").getJSONObject("data").get("url").toString());

                String url1 = "https://graph.facebook.com/" + data.get("id").toString()+ "/picture?type=large";
                URL url = this.stringToURL(url1);
                Bitmap bitmap = this.getBitmapFromURL(url);
                this.imgPerfilFacebook.setImageBitmap(bitmap);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Bitmap getBitmapFromURL(URL url) {
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);

            //Connect to our url
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected URL stringToURL(String urlString){
        try{
            URL url = new URL(urlString);
            return url;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

}

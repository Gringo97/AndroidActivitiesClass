package utad.twitterapi;

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

import utad.twitterapi.DataHolder.DataHolder;

public class SecondActivity extends AppCompatActivity {
    private TextView txtEmail;
    private TextView txtNombrePerfil;
    private TextView txtNombreUsuario;
    private TextView descripcion;
    private ImageView imgPerfil;


    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.txtEmail = (TextView) this.findViewById(R.id.txtEmailTwitter);
        this.txtNombrePerfil = (TextView) this.findViewById(R.id.txtUserTwitter);
        this.txtNombreUsuario = (TextView) this.findViewById(R.id.txtNombreTwitter);
        this.descripcion = (TextView) this.findViewById(R.id.txtDescripcionTwitter);
        this.imgPerfil = (ImageView) this.findViewById(R.id.imgPerfilTwitter);

        this.setData();
    }

    private void setData(){

        try {
            JSONObject data = DataHolder.jsonObjectTwitter;
            this.txtNombrePerfil.setText(data.get("UserName").toString());
            this.txtNombreUsuario.setText(data.get("name").toString());
            this.descripcion.setText(data.get("description").toString());
            this.txtEmail.setText(data.get("email").toString());
            if (data.has("picture")) {
                // System.out.println("La url de la img de facebook es: " + data.getJSONObject("picture").getJSONObject("data").get("url").toString());
                String url1 = ("https://twitter.com/"+data.get("UserName").toString()+"/profile_image?size=original");
                URL url= this.stringToURL(url1);
                Bitmap bitMap = this.getBitmapFromURL(url);
                this.imgPerfil.setImageBitmap(bitMap);


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
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

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

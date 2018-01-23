package fuente.oscar.tarea3.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fuente.oscar.tarea3.GeneralActivity.GeneralActivity;
import fuente.oscar.tarea3.R;

/**
 * Created by oscar.fuente on 16/01/2018.
 */

public class HttpJsonAsyncTask extends AsyncTask<String,Integer,String> {

    HttpJsonAsyncTaskListener listener;
    public void setListener(HttpJsonAsyncTaskListener listener){this.listener = listener;}





    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;




    public HttpJsonAsyncTask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {

        String stringUrl = urls[0];
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        String lat,lon,icon,humedad,temperatura;



        if (s != null) {
            try {
                //DESCARGA DE LATITUD Y LONGITUD
                JSONObject jsonObj = new JSONObject(s);
                Log.v("HttpJsonAsyncTask", "JSON OBJETO" + jsonObj);
                JSONObject JSONPosicion = new JSONObject(   jsonObj.getString("coord"));
                Log.v("HttpJsonAsyncTask", "JSON weather" + JSONPosicion);
                lat =  JSONPosicion.getString("lat");
                lon = JSONPosicion.getString("lon");
                listener.weatherCambio(lat,lon);

                //Descarga temperatura

                Log.v("HttpJsonAsyncTask", "JSON OBJETO" + jsonObj);
                JSONObject jsonTemperatura = new JSONObject(   jsonObj.getString("main"));
                Log.v("HttpJsonAsyncTask", "JSON weather" + jsonTemperatura);
                temperatura =  jsonTemperatura.getString("temp");
                humedad = jsonTemperatura.getString("humidity");
                double conversionTemp = (Double.parseDouble(temperatura)/32);

                listener.weatherTempHumedad(conversionTemp,humedad);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }






}

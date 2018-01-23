package fuente.oscar.tarea3.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by oscar.fuente on 22/01/2018.
 */

public class DownloadImgAsyncTask  extends AsyncTask<String,Integer,String> {




    HttpJsonAsyncTaskListener listener;

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;


    public DownloadImgAsyncTask(){}
    public void setListener(HttpJsonAsyncTaskListener listener){this.listener = listener;}

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
        String icon;


        if (s != null) {
            try{
                JSONObject jsonObj = new JSONObject(s);
                Log.v("DescargaDeIconoWeather",jsonObj.toString());
                JSONArray arrWeatherImg = jsonObj.getJSONArray("weather");
                for (int i= 0;i<arrWeatherImg.length();i++){
                    JSONObject jsonObject = arrWeatherImg.getJSONObject(i);
                    icon =  jsonObject.getString("icon").toString();
                    Log.v("DescargaDeIconoWeather",icon);
                    URL urlTiempo= this.stringToURL("http://openweathermap.org/img/w/"+icon.toString()+".png");
                    Log.v("UrlIconoWeather", urlTiempo.toString());
                    Bitmap bitMap = this.getBitmapFromURL(urlTiempo);
                    listener.weatherIcon(bitMap);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

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

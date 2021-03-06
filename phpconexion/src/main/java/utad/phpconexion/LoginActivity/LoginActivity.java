package utad.phpconexion.LoginActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import utad.phpconexion.Alumno;
import utad.phpconexion.DataHolder;
import utad.phpconexion.GeneralActivity;
import utad.phpconexion.R;


public class LoginActivity extends AppCompatActivity {

    public EditText etEmail;
    public EditText etPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }


    // Metodo a realizar cuando se pulse el boton Login
    public void checkLogin(View arg0) {

        // Get text from email and passord field
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        // Initialize  AsyncLogin() class with email and password
        new AsyncLogin(this).execute(email,password);

    }

}


class AsyncLogin extends AsyncTask<String, String, String> {
    LoginActivity loginActivity;

    public AsyncLogin (LoginActivity loginActivity){
        this.loginActivity = loginActivity;
    }

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }




    @Override
    protected String doInBackground(String... urls) {

        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL("http://10.0.2.2/AD_UD3_A03_Cliente_WEB/php/leerAlumnos.php?username= "+loginActivity.etEmail.getText()+"& password="+loginActivity.etPassword.getText());
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
            Log.v("RESULTPHP",result.toString() );
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }


        return result;

    }


//ACCION POSTERIOR AL METODO PROPIO DE ASYNCTASK CON LA INFORMACION SOBRE EL LOGIN
// Y CON LA INFORMACION DEVUELTA SI HA SIDO CORRECTO.
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //ATRIBUTOS DEL OBJETO ALUMNO
        String dni,nombre,apellido,telefono,nacionalidad,titulacion,cod;
        Log.v("JsonAlumnoSRespuestaPHP",s.toString());

        try {
            //PASAMOS EL STRING DEVUELTO POR EL PHP A UN OBJETO JSON
            JSONObject jsonObject = new JSONObject(s);
            //SELECCIONAMO S EL ARRAY QUE QUEREMOS RECORRER
            JSONArray jArrAlumnos= jsonObject.getJSONArray("Alumnos");

            //INICIALIZAMOS EL ARRAYLIST DE ALUMNOS PARA DESPUES MOSTRARLO
            //EN LA SIGUIENTE ACTIVITY
            DataHolder.instance.alumnosList = new ArrayList<>();


            for (int i = 0;i<jArrAlumnos.length();i++){

                cod =jArrAlumnos.getJSONObject(i).get("cod").toString();
                dni =  jArrAlumnos.getJSONObject(i).get("dni").toString();
                nombre = jArrAlumnos.getJSONObject(i).get("nombre").toString();
                apellido =  jArrAlumnos.getJSONObject(i).get("apellido").toString();
                telefono =  jArrAlumnos.getJSONObject(i).get("telefono").toString();
                nacionalidad =  jArrAlumnos.getJSONObject(i).get("nacionalidad").toString();
                titulacion =  jArrAlumnos.getJSONObject(i).get("titulacion").toString();

                //CREACION DEL ALUMNO CON LOS VALORES DE CADA UNO DE ELLOS
                Alumno alumnoNuevo = new Alumno(cod,dni,nombre,apellido,telefono,nacionalidad,titulacion);
                Log.v("OBJETO ALUMNO",alumnoNuevo.apellido+" "+alumnoNuevo.titulacion+" "+alumnoNuevo.nombre+" "+alumnoNuevo.telefono+" ");
                //AÑADIMOS EL ALUMNO A LA LISTA
                DataHolder.instance.alumnosList.add(alumnoNuevo);
            }

            //INICIALIZAMOS EL ACTIVITY POSTERIOR AL LOGIN REALIZADO EN EL PHP
            Intent intent = new Intent(loginActivity, GeneralActivity.class);
            loginActivity.startActivity(intent);
            loginActivity.finish();


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}

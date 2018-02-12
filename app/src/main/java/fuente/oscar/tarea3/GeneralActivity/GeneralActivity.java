package fuente.oscar.tarea3.GeneralActivity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

import fuente.oscar.tarea3.AsyncTasks.DownloadImgAsyncTask;
import fuente.oscar.tarea3.AsyncTasks.HttpJsonAsyncTask;
import fuente.oscar.milib.fragments.ListFragment.ListFragment;
import fuente.oscar.tarea3.Adapters.EnsaladaAdapter.EnsaladaViewHolder;
import fuente.oscar.tarea3.Adapters.CrepeAdapter.ListCrepeAdapter;
import fuente.oscar.tarea3.Adapters.EnsaladaAdapter.ListEnsaladAdapter;
import fuente.oscar.tarea3.Adapters.EnsaladaAdapter.ListEnsaladAdapterListener;
import fuente.oscar.tarea3.AsyncTasks.HttpJsonAsyncTaskListener;
import fuente.oscar.tarea3.DataHolder;
import fuente.oscar.tarea3.FBObjects.Crepe;
import fuente.oscar.tarea3.FBObjects.Ensalada;
import fuente.oscar.tarea3.FireBaseAdminListener;
import fuente.oscar.tarea3.GPSTracker.GPSAdmin.GPSTracker;
import fuente.oscar.tarea3.GPSTracker.GPSAdmin.GPSTrackerListener;
import fuente.oscar.tarea3.MapDetallesFragment;
import fuente.oscar.tarea3.R;
import fuente.oscar.tarea3.WeatherDetailsFragment;

public class GeneralActivity extends AppCompatActivity {
    public ListFragment listFragmentCrepe,listFragmentEnsalada;
    ListEnsaladAdapter listEnsaladaAdapter;
    ListCrepeAdapter listCrepeAdapter;
    SupportMapFragment mapFragment;
    Map<String,Ensalada> ensaladas;
    MapDetallesFragment mapDetailFragment;
    WeatherDetailsFragment weatherDetailsFragment;


    // LinearLayout llcontainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
    //Inicializamos el Events
        GeneralActivityEvents events = new GeneralActivityEvents(this);
    //Indicamos a la clase fireBaseAdmin que va a ser escuchada por events


        DataHolder.instance.fireBaseAdmin.setListener(events);


       listFragmentEnsalada = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentListEnsalada);


    //Inicializacion de ListFragment en ambos fragments
        listFragmentCrepe = new ListFragment();
        //listFragmentEnsalada= new ListFragment();


         mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);

        mapFragment.getMapAsync(events);
        mapDetailFragment = (MapDetallesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDetallesMapa);


        weatherDetailsFragment = (WeatherDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentWeatherDetails);





        //Transaction
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(listFragmentEnsalada);
        transaction.hide(mapDetailFragment);
        transaction.hide(mapFragment);
        transaction.hide(weatherDetailsFragment);


        transaction.show(listFragmentCrepe);
        // ........... transaction.add(R.id.llcontainer1,listFragmentCrepe,"lfCrepe");
        //transaction.add(R.id.llcontainer2,listFragmentEnsalada,"lfEnsalada");

        transaction.commit();
        GPSTracker gpsTracker = new GPSTracker(this);
        gpsTracker.setListener(events);
        gpsTracker.getLocation();



        if(gpsTracker.canGetLocation()){
                Log.v("GPSTracker",gpsTracker.getLatitude()+ "    "+ gpsTracker.getLongitude());
        }else{
            gpsTracker.showSettingsAlert();
        }


        HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
        httpJsonAsyncTask.setListener(events);
        DownloadImgAsyncTask downloadImgAsyncTask = new DownloadImgAsyncTask();
        downloadImgAsyncTask.setListener(events);
      //  String url = String.format("http://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s","3117732",DataHolder.instance.API_KEY);
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s",gpsTracker.getLatitude(),gpsTracker.getLongitude(),DataHolder.instance.API_KEY);


        httpJsonAsyncTask.execute(url);
        downloadImgAsyncTask.execute(url);


    DataHolder.instance.fireBaseAdmin.downloadAndObserveBranch("Crepes");

       // Log.v("SECONDACTIVITY-->","Email User: "+DataHolder.instance.fireBaseAdmin.user.getEmail());


    }
}
class  GeneralActivityEvents implements FireBaseAdminListener,ListEnsaladAdapterListener, OnMapReadyCallback,GoogleMap.OnMarkerClickListener, HttpJsonAsyncTaskListener,GPSTrackerListener{

    GeneralActivity generalActivity;
    private GoogleMap mMap;




    public GeneralActivityEvents(GeneralActivity generalActivity) {
        this.generalActivity = generalActivity;
    }


    @Override
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot) {
        Log.v("asdfghjkl", ""+dataSnapshot.getValue());
            System.out.println("El resultado es -------------------------------------------------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + dataSnapshot.getValue());
        Log.v(branch,""+dataSnapshot.getValue());
        if(branch.equals("Crepes")){
            GenericTypeIndicator<Map<String,Crepe>> indicator = new GenericTypeIndicator<Map<String,Crepe>>(){};
            Map<String,Crepe> crepes = dataSnapshot.getValue(indicator);
            //Crepes crepes = dataSnapshot.getValue(Crepes.class);
            Log.v("--->","CREPES CONTIENE"+ crepes);
            generalActivity.listCrepeAdapter = new ListCrepeAdapter(new ArrayList<Crepe>(crepes.values()));
            generalActivity.listFragmentCrepe.recyclerView.setAdapter(generalActivity.listCrepeAdapter);
            //Transaction
            android.support.v4.app.FragmentTransaction transaction = generalActivity.getSupportFragmentManager().beginTransaction();
            transaction.remove(generalActivity.listFragmentEnsalada);
            transaction.commit();


        }else if(branch.equals("Ensaladas")){
            quitarPinesEnsalada();
            GenericTypeIndicator<Map<String,Ensalada>> indicator = new GenericTypeIndicator<Map<String,Ensalada>>(){};
            generalActivity.ensaladas = dataSnapshot.getValue(indicator);
            //Crepes crepes = dataSnapshot.getValue(Crepes.class);
            Log.v("--->","ENSALADAS CONTIENE"+ generalActivity.ensaladas);

            generalActivity.listEnsaladaAdapter = new ListEnsaladAdapter(new ArrayList<Ensalada>(generalActivity.ensaladas.values()),generalActivity);
            generalActivity.listFragmentEnsalada.recyclerView.setAdapter(generalActivity.listEnsaladaAdapter);
            generalActivity.listEnsaladaAdapter.setListener(this);
            agregarPinesEnsalada();


            android.support.v4.app.FragmentTransaction transaction = generalActivity.getSupportFragmentManager().beginTransaction();
            transaction.remove(generalActivity.listFragmentCrepe);
            transaction.commit();
        }
          }

    @Override
    public void fireBaseAdminRegisterOK(Boolean blOk) {

    }

    @Override
    public void fireBaseAdminLoginOk(Boolean blOk) {

    }

    //LISTA ENSALADA ADAPTER
    @Override
    public void ListEnsaladAdapterCellClick(EnsaladaViewHolder cellHolder) {

        Log.v("--->","PRESIONADO CELDA"+cellHolder.getAdapterPosition());
    }

    public void quitarPinesEnsalada(){
        if(generalActivity.ensaladas!=null) {
            for (Object i : generalActivity.ensaladas.keySet()) {
                Ensalada ensaladaTemp = generalActivity.ensaladas.get(i.toString());
                if (ensaladaTemp.getMarkerEnsalada() != null) {
                    ensaladaTemp.getMarkerEnsalada().remove();

                }
            }
        }
    }



    public void agregarPinesEnsalada(){




        for (Object i: generalActivity.ensaladas.keySet()){
           Ensalada ensaladaTemp = generalActivity.ensaladas.get(i.toString());

            LatLng ensaladaPos = new LatLng(ensaladaTemp.lat, ensaladaTemp.lon);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(ensaladaPos);
            markerOptions.title(ensaladaTemp.nombre);

            if(mMap!= null){
                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(ensaladaTemp);
                ensaladaTemp.setMarkerEnsalada( marker);

                if(generalActivity.ensaladas.get(i).equals(0)){
                   mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ensaladaPos,10));
                }
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        DataHolder.instance.fireBaseAdmin.downloadAndObserveBranch("Ensaladas");
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Ensalada ensalada = (Ensalada) marker.getTag();
        generalActivity.mapDetailFragment.txtNombre.setText(ensalada.nombre);
        generalActivity.mapDetailFragment.txtDescripcion.setText(ensalada.descripcion);
        generalActivity.mapDetailFragment.txtPrecio.setText(ensalada.precio);
        android.support.v4.app.FragmentTransaction transaction = generalActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(generalActivity.mapDetailFragment);


        transaction.commit();
        return false;

    }



    @Override
    public void weatherCambio(String lat, String lon) {
        Log.v("LatLonFinal","lat"+lat+ "  lon "+lon);
        generalActivity.weatherDetailsFragment.txtLat.setText(String.valueOf(lat));
        generalActivity.weatherDetailsFragment.txtLong.setText(String.valueOf(lon));

    }

    @Override
    public void weatherIcon(Bitmap icon) {
        generalActivity.weatherDetailsFragment.imgTiempo.setImageBitmap(icon);
    }

    @Override
    public void weatherTempHumedad(Double temperatura, String humedad) {
        generalActivity.weatherDetailsFragment.txtTemperatura.setText(String.valueOf(temperatura));
        generalActivity.weatherDetailsFragment.txtHumedad.setText(String.valueOf(humedad));
    }




    @Override
    public void devolverLatLong(double lat, double lon) {
        Log.v("LatLonLocationChanged","lat "+lat+ "  lon "+lon);
        this.weatherCambio(String.valueOf(lat),String.valueOf(lon));


        HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
        httpJsonAsyncTask.setListener(this);
        DownloadImgAsyncTask downloadImgAsyncTask = new DownloadImgAsyncTask();
        downloadImgAsyncTask.setListener(this);
        //  String url = String.format("http://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s","3117732",DataHolder.instance.API_KEY);
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s",lat,lon,DataHolder.instance.API_KEY);
        httpJsonAsyncTask.execute(url);
        downloadImgAsyncTask.execute(url);
    }

}
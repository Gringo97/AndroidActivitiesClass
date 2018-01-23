package fuente.oscar.tarea3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailsFragment extends Fragment {
    public TextView txtLat,txtLong,txtHumedad,txtTemperatura;
    public ImageView imgTiempo;

    public WeatherDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_weather_details, container, false);

        txtLat = v.findViewById(R.id.txtLatitud);
        txtLong = v.findViewById(R.id.txtLongitud);
        txtHumedad = v.findViewById(R.id.txtHumedad);
        txtTemperatura = v.findViewById(R.id.txtTemperatura);
        imgTiempo = v.findViewById(R.id.imgTiempo);
                return v;
    }

}

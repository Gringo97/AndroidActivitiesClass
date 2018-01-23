package fuente.oscar.tarea3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapDetallesFragment extends Fragment {

    public TextView txtNombre,txtDescripcion,txtPrecio;
    public MapDetallesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_detalles, container, false);

        txtNombre = v.findViewById(R.id.txtNombreDetallesMap);
        txtDescripcion = v.findViewById(R.id.txtDescripcionDetallesMa);
        txtPrecio = v.findViewById(R.id.txtPrecioDetallesMap);
        return v;
    }

}

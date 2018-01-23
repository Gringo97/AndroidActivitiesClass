package utad.phpconexion.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import utad.phpconexion.R;

/**
 * Created by oscar.fuente on 23/01/2018.
 */

public class AlumnoViewHolder extends RecyclerView.ViewHolder {

    public TextView txtNombre,txtApellido,txtTelefono,txtDni,txtNacionalidad,txtTitulacion;



    public AlumnoViewHolder(View itemView) {
        super(itemView);
        txtNombre = itemView.findViewById(R.id.txtNombreAlumno);
        txtApellido = itemView.findViewById(R.id.txtApellidoAlumno);
        txtDni= itemView.findViewById(R.id.txtDniAlumno);
        txtNacionalidad = itemView.findViewById(R.id.txtNacionalidadAlumno);
        txtTelefono = itemView.findViewById(R.id.txtTelefonoAlumno);
        txtTitulacion = itemView.findViewById(R.id.txtTitulacionAlumno);


    }
}

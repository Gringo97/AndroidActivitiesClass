package utad.phpconexion.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utad.phpconexion.Alumno;
import utad.phpconexion.R;

/**
 * Created by oscar.fuente on 23/01/2018.
 */

public class ListAlumnoAdapter extends  RecyclerView.Adapter<AlumnoViewHolder> {
    AlumnoViewHolder viewHolder;


    private ArrayList<Alumno> arrAlumnos;
    public  ListAlumnoAdapter(ArrayList<Alumno> arrAlumnos){
        this.arrAlumnos = arrAlumnos;

    }


    @Override
    public AlumnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_alumno_layout,null);
        viewHolder = new AlumnoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlumnoViewHolder holder, int position) {
        holder.txtNombre.setText(arrAlumnos.get(position).nombre);
        holder.txtApellido.setText(arrAlumnos.get(position).apellido);
        holder.txtTelefono.setText(arrAlumnos.get(position).telefono);
        holder.txtTitulacion.setText(arrAlumnos.get(position).titulacion);
        holder.txtNacionalidad.setText(arrAlumnos.get(position).nacionalidad);
        holder.txtDni.setText(arrAlumnos.get(position).dni);


    }

    @Override
    public int getItemCount() {
        return arrAlumnos.size();
    }
}

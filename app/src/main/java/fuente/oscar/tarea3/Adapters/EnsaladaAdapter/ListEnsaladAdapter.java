package fuente.oscar.tarea3.Adapters.EnsaladaAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import fuente.oscar.tarea3.FBObjects.Ensalada;
import fuente.oscar.tarea3.R;

/**
 * Created by Oscar on 17/12/2017.
 */

public class ListEnsaladAdapter extends RecyclerView.Adapter<EnsaladaViewHolder> {

    EnsaladaViewHolder viewHolder;

    public ListEnsaladAdapterListener listener;//Recibe los eventos cuando pinchamos sobre una celda


    private ArrayList<Ensalada> arrEnsaladas; //Contiene todos los elementos que se van a pintar en las celdas
    private Context mContext;// Variable de tipo Context usada por la libreria Glide para cargar imagenes desde firebase

    public ListEnsaladAdapter(ArrayList<Ensalada> message, Context mContext){//Context hace referencia a donde se encuentra(Padre)
        this.mContext = mContext;
        arrEnsaladas = message;
    }

    public void setListener(ListEnsaladAdapterListener listener){
        this.listener = listener;
    }


    @Override
    public EnsaladaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Parte visual de la celda
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_ensalada_layout,null);

        //ViewHolder (Controlador) linkado a su correspondiente vista
        viewHolder = new EnsaladaViewHolder(view);

        return viewHolder;
    }
    //Este metodo pinta el contenido de los elementos de la celda a través del molde y para cada posicion de las celdas.
    @Override
    public void onBindViewHolder(EnsaladaViewHolder holder, int position) {
        holder.txtNombre.setText(arrEnsaladas.get(position).nombre);
        holder.txtDescripcion.setText(arrEnsaladas.get(position).descripcion);
        holder.txtPrecio.setText(arrEnsaladas.get(position).precio);
        holder.setListener(this.listener);
        //Funcion que carga en cache la imagen
        Glide.with(mContext).load(arrEnsaladas.get(position).imgUrl).into(holder.imgEnsalada);

    }

    @Override
    public int getItemCount() {
        return arrEnsaladas.size();
    }
}

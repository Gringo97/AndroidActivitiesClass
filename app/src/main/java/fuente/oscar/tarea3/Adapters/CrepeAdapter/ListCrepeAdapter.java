package fuente.oscar.tarea3.Adapters.CrepeAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fuente.oscar.tarea3.FBObjects.Crepe;
import fuente.oscar.tarea3.R;


/**
 * Created by Oscar on 16/12/2017.
 */

public class ListCrepeAdapter extends RecyclerView.Adapter<CrepeViewHolder> {
    CrepeViewHolder viewHolder;

    private ArrayList<Crepe> arrCrepes;

    public  ListCrepeAdapter(ArrayList<Crepe> message){
        arrCrepes = message;
    }


    @Override
    public  CrepeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_crepe_layout,null);
        viewHolder = new CrepeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CrepeViewHolder holder, int position) {
        holder.txtNombre.setText(arrCrepes.get(position).nombre);
        holder.txtPrecio.setText(arrCrepes.get(position).precio);
        holder.txtDescripcion.setText(arrCrepes.get(position).descripcion);
    }

    @Override
    public int getItemCount() {
        return arrCrepes.size();
    }
}
class CrepeViewHolder extends  RecyclerView.ViewHolder{

    public TextView txtNombre;
    public TextView txtDescripcion;
    public TextView txtPrecio;
    public CrepeViewHolder(View itemView) {
        super(itemView);
        txtNombre = itemView.findViewById(R.id.txtCrepeNombre);
        txtDescripcion = itemView.findViewById(R.id.txtCrepeDescripcion);
        txtPrecio = itemView.findViewById(R.id.txtCrepePrecio);
    }
}
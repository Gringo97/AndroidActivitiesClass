package fuente.oscar.tarea3.Adapters.EnsaladaAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fuente.oscar.tarea3.R;

/**
 * Created by oscar.fuente on 18/12/2017.
 */

public class EnsaladaViewHolder extends RecyclerView.ViewHolder{
    public ListEnsaladAdapterListener listener;


    public void setListener(ListEnsaladAdapterListener listener){
        this.listener = listener;
    }



    public TextView txtNombre;
    public TextView txtDescripcion;
    public TextView txtPrecio;
    public ImageView imgEnsalada;

    public EnsaladaViewHolder(View itemView) {
        super(itemView);
        txtNombre = itemView.findViewById(R.id.txtEnsaladaNombre);
        txtDescripcion = itemView.findViewById(R.id.txtEnsaladaDescripcion);
        txtPrecio = itemView.findViewById(R.id.txtEnsaldaPrecio);
        imgEnsalada = itemView.findViewById(R.id.imgEnsalada);

        //Inicializamos el evento
        EnsaladaViewHolderEvents events = new EnsaladaViewHolderEvents(this);
        //Seteo de events como listener de la View
        itemView.setOnClickListener(events);
    }
}

class EnsaladaViewHolderEvents implements View.OnClickListener{

    EnsaladaViewHolder ensaladaViewHolder;

    public EnsaladaViewHolderEvents(EnsaladaViewHolder ensaladaViewHolder) {
        this.ensaladaViewHolder = ensaladaViewHolder;
    }

    @Override
    public void onClick(View v) {
        ensaladaViewHolder.listener.ListEnsaladAdapterCellClick(ensaladaViewHolder);
        Log.v("--->","HE PULSADO SOBRE LA CELDA DE ENSALADA");

    }
}
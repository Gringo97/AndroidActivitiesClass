package utad.examen.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import utad.examen.R;

/**
 * Created by oscar.fuente on 19/12/2017.
 */

public class NoticiasViewHolder extends RecyclerView.ViewHolder{

    public TextView txtTitulo,txtDescripcion;
    public ImageView imgNoticia;




    public ListNoticiasAdapterListener listener;

    public void setListener(ListNoticiasAdapterListener listener){
        this.listener = listener;
    }


    public NoticiasViewHolder(View itemView) {
        super(itemView);
    txtTitulo = itemView.findViewById(R.id.txtTituloNoticia);
    txtDescripcion = itemView.findViewById(R.id.txtDescripcionNoticia);
    imgNoticia = itemView.findViewById(R.id.imgNoticia);


    NoticiasViewHolderEvents events = new NoticiasViewHolderEvents(this);

    itemView.setOnClickListener(events);





    }
}

class NoticiasViewHolderEvents implements View.OnClickListener{

    NoticiasViewHolder noticiasViewHolder;

    public NoticiasViewHolderEvents(NoticiasViewHolder noticiasViewHolder) {
        this.noticiasViewHolder = noticiasViewHolder;
    }

    @Override
    public void onClick(View v) {
        Log.v("-->", "PULSADO CELDA"+ v.getId());
        noticiasViewHolder.listener.ListNoticiasAdapterListenerCellClick(noticiasViewHolder);

    }

}
package utad.examen.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import utad.examen.Noticia.Noticia;
import utad.examen.R;

/**
 * Created by oscar.fuente on 19/12/2017.
 */

public class ListNoticiasAdapter extends RecyclerView.Adapter<NoticiasViewHolder> {
    NoticiasViewHolder viewHolder;

    public ListNoticiasAdapterListener listener;

    public ArrayList<Noticia> arrNoticias;
    private Context mContext;

    public void setListener(ListNoticiasAdapterListener listener){
        this.listener = listener;
    }

    public ListNoticiasAdapter(ArrayList<Noticia> noticias,Context mContext){
        this.mContext = mContext;
        this.arrNoticias = noticias;
    }


    @Override
    public NoticiasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_noticia_layout,null);
        viewHolder = new NoticiasViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoticiasViewHolder holder, int position) {
        holder.txtTitulo.setText(arrNoticias.get(position).titulo);
        holder.txtDescripcion.setText(arrNoticias.get(position).descripcion);
        holder.setListener(this.listener);

        Glide.with(mContext).load(arrNoticias.get(position).urlFoto).into(holder.imgNoticia);
    }

    @Override
    public int getItemCount() {
        return arrNoticias.size();
    }
}

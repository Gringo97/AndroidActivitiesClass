package utad.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;

import fuente.oscar.milib.fragments.ListFragment.ListFragment;
import utad.examen.Adapter.ListNoticiasAdapter;
import utad.examen.Adapter.ListNoticiasAdapterListener;
import utad.examen.Adapter.NoticiasViewHolder;
import utad.examen.Noticia.Noticia;

public class GeneralActivity extends AppCompatActivity {
    public ListFragment listFragmentNoticias;

    public ListFragment listFragmentNoticiasExtendida;

    ListNoticiasAdapter listNoticiasAdapter;
    ListNoticiasAdapter listNoticiasAdappter2;



    LinearLayout llcontainer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        GeneralActivityEvents events = new GeneralActivityEvents(this);

        DataHolder.instance.fireBaseAdmin.setListener(events);

        llcontainer = this.findViewById(R.id.llcontainer);

        listFragmentNoticias = new ListFragment();
        listFragmentNoticiasExtendida = new ListFragment();

        //Transaction
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.llcontainer1,listFragmentNoticias,"lfNoticias");
        transaction.add(R.id.llcontainer2,listFragmentNoticiasExtendida,"Extendida");


        transaction.commit();

        DataHolder.instance.fireBaseAdmin.downloadAndObserveBranch("Noticias");

    }
}
class  GeneralActivityEvents implements FireBaseAdminListener,ListNoticiasAdapterListener {

    GeneralActivity generalActivity;


    public GeneralActivityEvents(GeneralActivity generalActivity) {
        this.generalActivity = generalActivity;
    }

    @Override
    public void ListNoticiasAdapterListenerCellClick(NoticiasViewHolder cellHolder) {
        Log.v("--->","PRESIONADO CELDA"+cellHolder.getAdapterPosition());


         Noticia seleccion = generalActivity.listNoticiasAdapter.arrNoticias.get(cellHolder.getAdapterPosition());

         ArrayList<Noticia> seleccionado = new ArrayList<Noticia>();
        seleccionado.add(seleccion);
         generalActivity.listNoticiasAdappter2 =  new ListNoticiasAdapter(seleccionado,generalActivity);
         generalActivity.listFragmentNoticiasExtendida.recyclerView.setAdapter(generalActivity.listNoticiasAdappter2);
        generalActivity.listNoticiasAdappter2.setListener(this);


        android.support.v4.app.FragmentTransaction transaction = generalActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(generalActivity.listFragmentNoticiasExtendida);
        transaction.hide(generalActivity.listFragmentNoticias);

        transaction.commit();





    }



    @Override
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot) {

        Log.v(branch, "...>" + dataSnapshot.getValue()+"  "+branch);
        if(branch.equals("Noticias")) {
            Log.v(branch, "" + dataSnapshot.getValue());
            GenericTypeIndicator<Map<String, Noticia>> indicator = new GenericTypeIndicator<Map<String, Noticia>>() {
            };
            Map<String, Noticia> noticias = dataSnapshot.getValue(indicator);
            Log.v("--->", "NOTICIAS CONTIENE" + noticias);
            generalActivity.listNoticiasAdapter = new ListNoticiasAdapter(new ArrayList<>(noticias.values()), generalActivity);
            generalActivity.listFragmentNoticias.recyclerView.setAdapter(generalActivity.listNoticiasAdapter);
            generalActivity.listNoticiasAdapter.setListener(this);

            android.support.v4.app.FragmentTransaction transaction = generalActivity.getSupportFragmentManager().beginTransaction();
            transaction.remove(generalActivity.listFragmentNoticiasExtendida);
            transaction.commit();

        }

    }

    @Override
    public void fireBaseAdminRegisterOK(Boolean blOk) {

    }

    @Override
    public void fireBaseAdminLoginOk(Boolean blOk) {

    }
}

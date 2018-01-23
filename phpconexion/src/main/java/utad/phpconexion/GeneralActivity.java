package utad.phpconexion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import utad.phpconexion.Adapter.ListAlumnoAdapter;
import utad.phpconexion.Adapter.ListFragment;
import utad.phpconexion.DataHolder;
import utad.phpconexion.R;

public class GeneralActivity extends AppCompatActivity {

    ListAlumnoAdapter listAlumnoAdapter;
    ListFragment listFragmentAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);


        listFragmentAlumno = (ListFragment)  getSupportFragmentManager().findFragmentById(R.id.fragmentListAlumnos);
        listAlumnoAdapter = new ListAlumnoAdapter(DataHolder.instance.alumnosList);
        listFragmentAlumno.recyclerView.setAdapter(listAlumnoAdapter);


    }
}

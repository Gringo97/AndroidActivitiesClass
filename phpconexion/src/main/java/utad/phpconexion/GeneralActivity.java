package utad.phpconexion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import utad.phpconexion.Adapter.ListAlumnoAdapter;

public class GeneralActivity extends AppCompatActivity {
    ListAlumnoAdapter listAlumnoAdapter;
    public ListFragment listFragmentAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        listFragmentAlumno = (ListFragment)  getSupportFragmentManager().findFragmentById(R.id.fragmentListAlumnos);

        listAlumnoAdapter = new ListAlumnoAdapter(DataHolder.instance.alumnosList);
        listFragmentAlumno.recyclerView.setAdapter(listAlumnoAdapter);


    }
}

package utad.pushnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

import utad.pushnotification.FireBase.FireBaseAdminListener;
import utad.pushnotification.SQLite.Contact;
import utad.pushnotification.SQLite.DatabaseHandler;

public class GeneralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        GeneralActivityEvents events = new GeneralActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(events);


        DataHolder.instance.fireBaseAdmin.downloadAndObserveBranch("Usuarios");


        Button btnNotificar = (Button) findViewById(R.id.btnNotificar);
        btnNotificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String url = String.format("https://us-central1-tarea2androida.cloudfunctions.net/sendNewPush");
                    HttpJsonAsyncTask httpJsonAsyncTask = new HttpJsonAsyncTask();
                    httpJsonAsyncTask.execute(url);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        FirebaseCrash.report(new Exception("GeneralActivity Created"));
    }


}
class  GeneralActivityEvents implements FireBaseAdminListener {

    GeneralActivity generalActivity;

    @Override
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot) {
        Log.v("DATASNAPSHOT", "" + dataSnapshot.getValue());

        Log.v(branch, "" + dataSnapshot.getValue());
        if (branch.equals("Usuarios")) {
            int i = 1;
            DataHolder.instance.usuarioHashMap = new HashMap<>();
            for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                Contact usuario = new Contact();

                usuario.setID(i);
                usuario.setName(messageSnapshot.child("nombre").getValue().toString());
                usuario.setPhoneNumber(messageSnapshot.child("telefono").getValue().toString());
                DataHolder.instance.usuarioHashMap.put(usuario.getID(),usuario);
                i++;
            }
            Log.v("HASHMAPCONTACT",""+DataHolder.instance.usuarioHashMap.size());
            DatabaseHandler databaseHandler = new DatabaseHandler(generalActivity.getBaseContext());
            databaseHandler.getReadableDatabase().execSQL("delete  from  contacts ");


            for(Map.Entry<Integer, Contact> entry : DataHolder.instance.usuarioHashMap.entrySet()) {

                Contact contact = new Contact(entry.getValue().getID(),entry.getValue().getName(),entry.getValue().getPhoneNumber());
                Log.v("--->",""+contact.getID());
                databaseHandler.addContact(contact);
            }

            FirebaseCrash.report(new Exception("DataBaseHandler add FireBase Objects Correct"));

        }

    }

    public GeneralActivityEvents(GeneralActivity generalActivity) {
        this.generalActivity = generalActivity;
    }

    @Override
    public void fireBaseAdminRegisterOK(Boolean blOk) {

    }
    @Override
    public void fireBaseAdminLoginOk(Boolean blOk) {

    }

}

package utad.pushnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNotificar = (Button) findViewById(R.id.btnNotification);

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




    }
}

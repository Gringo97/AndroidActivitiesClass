package utad.animaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MainActivityEvents events;
    Button btn1, btn2;
    TextView textView;
    Animation rotation, translate, alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events= new MainActivityEvents(this);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(events);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(events);
        textView = findViewById(R.id.textView);


//Animation set
        rotation = AnimationUtils.loadAnimation(this, R.anim.anim1);
        alpha = AnimationUtils.loadAnimation(this,R.anim.anim3);
        translate = AnimationUtils.loadAnimation(this,R.anim.anim2);

    }

}


class MainActivityEvents implements View.OnClickListener{
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1){
            mainActivity.btn1.startAnimation(mainActivity.rotation);
            mainActivity.textView.startAnimation(mainActivity.alpha);
            Log.v("boton","btn1");
        }else if (view.getId() == R.id.btn2){
            mainActivity.btn2.startAnimation(mainActivity.translate);
            Log.v("boton","btn2");

        }
    }
}
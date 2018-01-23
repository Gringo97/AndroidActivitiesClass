package fuente.oscar.valuesstyles;

import android.support.design.widget.Snackbar;
import android.view.View;

public  class MainActivityEvents implements View.OnClickListener{
    MainActivity mainActivity;
    public MainActivityEvents (MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }
}

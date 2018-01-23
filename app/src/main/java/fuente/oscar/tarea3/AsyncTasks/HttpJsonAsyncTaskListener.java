package fuente.oscar.tarea3.AsyncTasks;

import android.graphics.Bitmap;

/**
 * Created by oscar.fuente on 18/01/2018.
 */

public interface HttpJsonAsyncTaskListener {
    public void weatherCambio(String lat, String lon);
    public void weatherIcon(Bitmap icon);
    public void weatherTempHumedad(Double temperatura,String humedad);
}

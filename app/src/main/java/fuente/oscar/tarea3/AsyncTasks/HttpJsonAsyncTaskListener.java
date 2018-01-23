package fuente.oscar.tarea3.AsyncTasks;

import android.graphics.Bitmap;

/**
 * Created by oscar.fuente on 18/01/2018.
 */

public interface HttpJsonAsyncTaskListener {
    void weatherCambio(String lat, String lon);
    void weatherIcon(Bitmap icon);
    void weatherTempHumedad(Double temperatura,String humedad);
}

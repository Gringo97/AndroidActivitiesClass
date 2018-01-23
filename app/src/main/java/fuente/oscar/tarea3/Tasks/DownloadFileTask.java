package fuente.oscar.tarea3.Tasks;

import android.os.AsyncTask;


/**
 * Created by oscar.fuente on 15/01/2018.
 */

public class DownloadFileTask extends AsyncTask<String,Integer,Long> {


    public DownloadFileTask(Integer... ar){

    }
    @Override
    protected Long doInBackground(String... strings) {
        long lng=20000;
        publishProgress(70,75,80,90,95,100);
        return lng;
    }
    //Salida del doInBackground
    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}

package utad.examen;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by oscar.fuente on 19/12/2017.
 */

public interface FireBaseAdminListener {
    public  void fireBaseAdminRegisterOK(Boolean blOk);
    public void fireBaseAdminLoginOk(Boolean blOk);
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot);
}

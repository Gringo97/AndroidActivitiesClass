package utad.twitterapi.FireBase;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by oscar.fuente on 29/01/2018.
 */

public interface FirebaseAdminListener {
    public  void fireBaseAdminRegisterOK(Boolean blOk);
    public void fireBaseAdminLoginOk(Boolean blOk);
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot);
}

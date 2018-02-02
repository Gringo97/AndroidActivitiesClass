package com.example.menu;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Oscar on 16/12/2017.
 */

public interface FireBaseAdminListener {
    public  void fireBaseAdminRegisterOK(Boolean blOk);
    public void fireBaseAdminLoginOk(Boolean blOk);
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot);
    public void fireBaseImageDownload();
}

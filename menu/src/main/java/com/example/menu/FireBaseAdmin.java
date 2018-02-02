package com.example.menu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Oscar on 16/12/2017.
 */

public class FireBaseAdmin {
    //Listener
    public FireBaseAdminListener listener;

    public FirebaseUser user;

    public FirebaseAuth mAuth;
    public FirebaseDatabase database;
    public DatabaseReference myRefRoot;
    public DatabaseReference myRefPerfiles;
    public FirebaseStorage storage;
    // Create a storage reference from our app
    StorageReference storageRef;
    StorageReference pathReference;


    //Constructor
    public  FireBaseAdmin(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefRoot = database.getReference("Comida");
        myRefPerfiles = database.getReference();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        pathReference = storageRef.child("ImgPerfil");

    }

    public void setListener(FireBaseAdminListener listener){
        this.listener = listener;
    }



    //Metodo para iniciar sesion
    public void loginWithEmailPassword(String email,String password,Activity activity){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            listener.fireBaseAdminLoginOk(false);
                        }
                        else{
                            listener.fireBaseAdminLoginOk(true);
                        }
                    }
                });
    }

    public void registerNewUserWithEmailPassword(String email,String password,Activity activity){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            listener.fireBaseAdminRegisterOK(false);
                        }else {
                            listener.fireBaseAdminRegisterOK(false);
                        }

                        // ...
                    }
                });
    }

    public void downloadAndObserveBranch(final String branch){
        DatabaseReference refBranch = myRefPerfiles.child(branch);
        Log.v("Rama descarga"," "+refBranch);
        refBranch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.v("datos descarga"," "+dataSnapshot.toString());
                listener.fireBaseAdminbranchDownload(branch,dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                listener.fireBaseAdminbranchDownload(branch,null);
            }
        });
    }

    public void writeNewPost(String branch, Map<String,Object> valores){
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put(branch + this.mAuth.getCurrentUser().getUid(), valores);
        myRefPerfiles.updateChildren(childUpdates);



    }
}

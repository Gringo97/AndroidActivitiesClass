package utad.examen;

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

import static android.content.ContentValues.TAG;

/**
 * Created by oscar.fuente on 19/12/2017.
 */

public class FireBaseAdmin {
    //Listener
    public FireBaseAdminListener listener;

    public FirebaseUser user;

    private FirebaseAuth mAuth;
    public FirebaseDatabase database;
    public DatabaseReference myRefRoot;


    //Constructor
    public  FireBaseAdmin(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefRoot = database.getReference();
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
                            listener.fireBaseAdminRegisterOK(true);
                        }

                        // ...
                    }
                });
    }

    public void downloadAndObserveBranch(final String branch){
        DatabaseReference refBranch = myRefRoot.child(branch);
        refBranch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listener.fireBaseAdminbranchDownload(branch,dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                listener.fireBaseAdminbranchDownload(branch,null);
            }
        });
    }
}

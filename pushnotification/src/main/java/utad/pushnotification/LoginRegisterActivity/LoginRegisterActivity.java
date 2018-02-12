package utad.pushnotification.LoginRegisterActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.iid.FirebaseInstanceId;

import fuente.oscar.milib.fragments.Login.LoginFragment;
import fuente.oscar.milib.fragments.Login.LoginFragmentListener;
import fuente.oscar.milib.fragments.Register.RegisterFragment;
import fuente.oscar.milib.fragments.Register.RegisterFragmentListener;

import utad.pushnotification.DataHolder;
import utad.pushnotification.FireBase.FireBaseAdminListener;
import utad.pushnotification.GeneralActivity;
import utad.pushnotification.R;
import utad.pushnotification.SQLite.Contact;
import utad.pushnotification.SQLite.DatabaseHandler;

public class LoginRegisterActivity extends AppCompatActivity {


    //Fragments
    LoginFragment loginFragment;
    RegisterFragment registerFragment;
    //EventsActivity
    LoginRegisterActivityEvents events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        Log.v("TOKEN MI MOVIL"," entrada");

        //Igualamos el id del fragment.xml a la variable local
        this.loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.LoginFragment);
        this.registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.RegisterFragment);

        //Inicializamos el Events de la propia Activity
        events = new LoginRegisterActivityEvents(this);

        //El Events del Activity va a escuchar a loginFragment, registerFragment y al fireBaseAdmin
        loginFragment.setListener(this.events);
        registerFragment.setListener(this.events);
        DataHolder.instance.fireBaseAdmin.setListener(this.events);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.v("TOKEN MI MOVIL"," "+refreshedToken);

        Log.v("TOKEN MI MOVIL"," "+refreshedToken);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        //Execution
        transaction.commit();




    }
}


//La clase Events implementa las Interfaces Listener
class LoginRegisterActivityEvents implements LoginFragmentListener,RegisterFragmentListener,FireBaseAdminListener {

    //Variable para poder hacer referencia a la activity declarada


    //LOGINFRAGMENTLISTENER
    LoginRegisterActivity loginRegisterActivity;
    public  LoginRegisterActivityEvents(LoginRegisterActivity activity){
        loginRegisterActivity = activity;
    }

    //Button para iniciar sesion
    @Override
    public void LoginButtonClicked(String sUser, String sPassword) {
        DataHolder.instance.fireBaseAdmin.loginWithEmailPassword(sUser,sPassword,loginRegisterActivity);
    }

    //Button para dirigirnos al fragment de registro
    @Override
    public void RegisterButtonClicked() {
        //Transaction
        FragmentTransaction transaction = loginRegisterActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(loginRegisterActivity.loginFragment);
        transaction.show(loginRegisterActivity.registerFragment);
        //Execution
        transaction.commit();
    }

//REGISTERFRAGMENTLISTENER

    //Button para registrarte
    @Override
    public void RegisterAcceptButtonClicked(String user, String password) {
        FirebaseCrash.report(new Exception("FireBase Register Correct"));
        DataHolder.instance.fireBaseAdmin.registerNewUserWithEmailPassword(user,password,loginRegisterActivity);
    }

    //Button para volver al fragment Login
    @Override
    public void BackToLoginButtonCliked() {
        //Transaction
        FragmentTransaction transaction = loginRegisterActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(loginRegisterActivity.registerFragment);
        transaction.show(loginRegisterActivity.loginFragment);
        //Execution
        transaction.commit();
    }

//FIREBASE

    //Metodo para cambiara la activity general si el registro ha sido correcto
    @Override
    public void fireBaseAdminRegisterOK(Boolean blOk) {
        Log.v("-------->","RESULT OF REGISTER"+blOk);
        FirebaseCrash.report(new Exception("FireBase Login Correct"));
        if(blOk){
            Intent intent = new Intent(loginRegisterActivity, GeneralActivity.class);

            loginRegisterActivity.startActivity(intent);
            loginRegisterActivity.finish();
        }
    }

    //Metodo para cambiar a la activity general si el login ha sido correcto
    @Override
    public void fireBaseAdminLoginOk(Boolean blOk) {
        Log.v("-------->","RESULT OF LOGIN"+blOk);
        if(blOk){
            Intent intent = new Intent(loginRegisterActivity, GeneralActivity.class);
            loginRegisterActivity.startActivity(intent);
            loginRegisterActivity.finish();
        }
    }

    //Metodo para Administrar las ramas de la BBDD a descargar
    @Override
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot) {

    }


}

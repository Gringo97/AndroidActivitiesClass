package com.example.facebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.Arrays;


public class MainActivityFacebook extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_facebook);

        callbackManager = CallbackManager.Factory.create();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email","public_profile","user_friends","user_photos","user_birthday","read_custom_friendlists"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //Se llama si se ha realizado el logueo correctamente
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());

                //Mediante la API GraphRequest podemos realizar solicitudes para devolver un JSONObject asociado al usuario logueada que contiene toda su información
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                System.out.println("---------->El objeto devuelto por facebook es: " + object);
                                //JSONObject para almacenar los datos del json que nos devuelve facebook al loguearnos con todos los datos del usuario
                                DataHolder.instance.jsonObject = object;
                                Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                Bundle parameters = new Bundle();
                //Con el parámetro fields especificamos los campos que queremos que nos devuelva el json de facebook
                parameters.putString("fields", "id,name,link,picture,birthday,email");
                request.setParameters(parameters);
                request.executeAsync();






            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }


            private void handleFacebookAccessToken(AccessToken token) {
                Log.d("FirebaseFacebook", "handleFacebookAccessToken:" + token);

                AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                DataHolder.instance.firebaseAdmin.mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(MainActivityFacebook.this,  new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Logueo", "signInWithCredential:success");
                                    FirebaseUser user = DataHolder.instance.firebaseAdmin.mAuth.getCurrentUser();
                                    //getMainActivityEvents().iniciarAppSecondActivity();




                                    //updateUI(user);
                                } else {
                                   /* Intent myIntent = new Intent(this, FacebookActivity.class);
                                    startActivity(myIntent);
                                    */
                                    // If sign in fails, display a message to the user.
                                    Log.w("Logueo", "signInWithCredential:failure", task.getException());
                                    Toast.makeText(MainActivityFacebook.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }


            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                callbackManager.onActivityResult(requestCode, resultCode, data);
                MainActivityFacebook.super.onActivityResult(requestCode, resultCode, data);
            }

        });
    }
}

package utad.twitterapi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.internal.network.OAuth1aInterceptor;
import com.twitter.sdk.android.core.models.User;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utad.twitterapi.FireBase.FirebaseAdmin;
import utad.twitterapi.Interface.ApiInterface;




public class LogInActivity extends AppCompatActivity {


    private TwitterLoginButton twitterLoginButton;

    private Retrofit retrofit;

    private JSONObject datosTwitter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        utad.twitterapi.DataHolder.DataHolder.firebaseAdmin = new FirebaseAdmin();

        datosTwitter = new JSONObject();

        Twitter.initialize(this);

        setContentView(R.layout.activity_log_in);



        twitterLoginButton =  findViewById(R.id.login_button);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                Log.d("USERNAME TWITTER", "twitterLogin:success  " + result.data.getUserName());

                try {
                    datosTwitter.put("UserName", result.data.getUserName().toString());
                    datosTwitter.put("id", String.valueOf(result.data.getUserId()));
                    Log.v("USERNAME TWITTER", result.data.getUserName().toString() + " "+String.valueOf(result.data.getUserId()));
                    handleTwitterSession(result.data);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.twitter.com/1.1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        // Twitter interceptor
                        .client(new OkHttpClient.Builder()
                                .addInterceptor(new OAuth1aInterceptor(TwitterCore.getInstance().getSessionManager().getActiveSession(), TwitterCore.getInstance().getAuthConfig()))
                                .build())
                        .build();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w("asdf", "twitterLogin:failure", exception);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }


    private void handleTwitterSession(TwitterSession session) {
        Log.d("Sesion Iniciada Twitter", "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);


        utad.twitterapi.DataHolder.DataHolder.instance.fireBaseAdmin.mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TwitterSuccess", "signInWithCredential:success");
                            FirebaseUser user = utad.twitterapi.DataHolder.DataHolder.instance.fireBaseAdmin.mAuth.getCurrentUser();
                            //updateUI(user);
                            getUserDatails();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TwitterFailure", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }



    public void getUserDatails() {

        //Utilizacion de TwitterAuthClient para poder obtener el Email del Usuario
        TwitterAuthClient authClient = new TwitterAuthClient();
        //Peticion del Email
        authClient.requestEmail(TwitterCore.getInstance().getSessionManager().getActiveSession(), new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                try {
                    Log.v("Obtencion Email", result.data.toString());
                    datosTwitter.put("email", result.data.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                //Utilizacion de la API Retrofit2 para la obtencion del resto de atributos del usuario

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                retrofit2.Call<User> call = apiInterface.getUserDetails(TwitterCore.getInstance().getSessionManager().getActiveSession().getUserName());
                call.enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {

                        try {
                            datosTwitter.put("name",response.body().name.toString());
                            datosTwitter.put("description",response.body().description.toString());
                            datosTwitter.put("picture",response.body().profileImageUrl);


                            utad.twitterapi.DataHolder.DataHolder.instance.jsonObjectTwitter = datosTwitter;
                            Log.v("DATOS TWITTER ACTUAL",  utad.twitterapi.DataHolder.DataHolder.instance.jsonObjectTwitter.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //CAMBIO DE ACTIVIDAD UNA VEZ OBTENIDO LOS RESULTADOS
                        Intent intent = new Intent(getBaseContext(),SecondActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<User> call, Throwable t) {

                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

    }



}

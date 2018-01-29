package utad.facebooktwittersdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twitter.sdk.android.core.Twitter;

import utad.facebooktwittersdk.Fragments.LoginFragment;
import utad.facebooktwittersdk.Fragments.TwitterLogInFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoginFragment facebookFragment;
        TwitterLogInFragment twitterLogInFragment;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        facebookFragment =(LoginFragment)  getSupportFragmentManager().findFragmentById();
       // twitterLogInFragment = (TwitterLogInFragment) getSupportFragmentManager().findFragmentById();

        Twitter.initialize(this);
    }
}

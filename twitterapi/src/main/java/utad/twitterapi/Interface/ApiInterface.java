package utad.twitterapi.Interface;

import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oscar.fuente on 29/01/2018.
 */

public interface ApiInterface {
    @GET("users/show.json")
    Call<User> getUserDetails(@Query("screen_name") String screenName);
}

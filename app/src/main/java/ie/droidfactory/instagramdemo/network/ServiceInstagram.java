package ie.droidfactory.instagramdemo.network;

import ie.droidfactory.instagramdemo.model.MediaRecent;
import ie.droidfactory.instagramdemo.model.UserSelf;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static ie.droidfactory.instagramdemo.network.ApiUtils.*;

/**
 * endpoints of Instagram api
 */
public interface ServiceInstagram {

    @GET(URL_RECENT_MEDIA)
    Call<MediaRecent> getMediaRecent(
            @Query(PARAM_ACCESS_TOKEN) String accesToken);

    @GET(".")
    Call<UserSelf> getUserSelf(
            @Query(PARAM_ACCESS_TOKEN) String accesToken);

}

package ie.droidfactory.instagramdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * provides retrofit service
 */
@Module(includes = {NetworkModule.class})
public class ServiceInstagramModule {

    @Provides
    @InstagramApplicationScope
    public ServiceInstagram getServiceInstagram(Retrofit instagramRetrofit){
        return instagramRetrofit.create(ServiceInstagram.class);
    }

    @Provides
    @InstagramApplicationScope
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }

    @Provides
    @InstagramApplicationScope
    public Retrofit retrofit(Gson gson, OkHttpClient okHttpClient){
        return  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(ApiUtils.ENDPOINT_SELF)
                .build();
    }
}

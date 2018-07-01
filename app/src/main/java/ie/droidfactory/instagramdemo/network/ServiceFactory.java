package ie.droidfactory.instagramdemo.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    //converter
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    //retrofit client
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson));

    /**
     * create instance of instagram endpoint api
     * @param seviceClass service interface class
     * @param baseUrl
     * @param <S>
     * @return instance of instagram endpoint api
     */
    public static <S> S createService(Class<S> seviceClass, String baseUrl) {

        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .client(okHttpClient
                        .retryOnConnectionFailure(true)
                        .build())
                .build();
        return retrofit.create(seviceClass);
    }
}

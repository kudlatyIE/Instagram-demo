package ie.droidfactory.instagramdemo.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ServiceFactory {

    private static final String TAG = ServiceFactory.class.getSimpleName();

    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(new Converter.Factory(){

                    });

    public static <S> S createService(Class<S> seviceClass, String baseUrl) {
//        Timber.plant(new Timber.DebugTree());
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
//
//            @Override
//            public void log(String message) {
//                Timber.i(message);
//                Log.d(TAG, "HttpLoggingInterceptor message: "+message);
//            }
//        });
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .client(okHttpClient
//                        .addInterceptor(loggingInterceptor)
                        .retryOnConnectionFailure(true)
                        .build())
                .build();
        Log.d(TAG, "base url: "+retrofit.baseUrl());
        return retrofit.create(seviceClass);
    }
}

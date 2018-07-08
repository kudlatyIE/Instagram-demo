package ie.droidfactory.instagramdemo;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = {ContextModule.class})
public class NetworkModule {

    /**
     * logs HTTP request and response data
     * @return
     */
    @Provides
    @InstagramApplicationScope
    public HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return loggingInterceptor;
    }

    /**
     * cache instagram data
     * @param cacheFile
     * @return
     */
    @Provides
    @InstagramApplicationScope
    public Cache cache(File cacheFile){
        return new Cache(cacheFile, 10*1000*1000);
    }

    @Provides
    @InstagramApplicationScope
    public File cacheFile(@ApplicationContext Context context){
        return  new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @InstagramApplicationScope
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor){
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .build();
    }

}

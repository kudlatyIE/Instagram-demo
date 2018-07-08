package ie.droidfactory.instagramdemo;

import android.app.Activity;
import android.app.Application;
import com.squareup.picasso.Picasso;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;
import timber.log.Timber;

public class InstagramApplication extends Application {

    private InstagramApplicationComponent component;

    public static InstagramApplication get(Activity activity){
        return (InstagramApplication) activity.getApplication();
    }

    private ServiceInstagram serviceInstagram;

    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        /**
         * need fill only modules that have a constructor with argument but we can...
         * DaggerInstagramApplicationComponent builder instances all modules (for non-arguments constructors only!)
         */
        component = DaggerInstagramApplicationComponent.builder()
                .contextModule(new ContextModule(this))
//                .networkModule(new NetworkModule())
//                .picassoModule(new PicassoModule())
//                .serviceInstagramModule(new ServiceInstagramModule())
                .build();

        serviceInstagram = component.getServiceInstagram();
        picasso = component.getPicasso();
    }

    public InstagramApplicationComponent component() {
        return component;
    }


}

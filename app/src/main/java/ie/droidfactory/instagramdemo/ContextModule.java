package ie.droidfactory.instagramdemo;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context){
        this.context=context;
    }

    @Provides
    @InstagramApplicationScope
    @ApplicationContext
    public Context context(){
        return context;
    }
}

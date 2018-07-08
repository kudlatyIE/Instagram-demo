package ie.droidfactory.instagramdemo;


import com.squareup.picasso.Picasso;

import dagger.Component;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;

/**
 * bridge between @Inject and @Module
 * Add Scope to Provider module - do not use javax.inject.Singleton, just scope interface
 * @Scope is a 'singleton' for @Component instance which was created, java @Sigleton is a singleton for whole application
 */

@InstagramApplicationScope
@Component(modules = {ServiceInstagramModule.class, PicassoModule.class})
public interface InstagramApplicationComponent {

    Picasso getPicasso();
    ServiceInstagram getServiceInstagram();
}

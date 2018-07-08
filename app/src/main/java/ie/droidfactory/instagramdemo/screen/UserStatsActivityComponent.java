package ie.droidfactory.instagramdemo.screen;

import dagger.Component;
import ie.droidfactory.instagramdemo.InstagramApplicationComponent;
import ie.droidfactory.instagramdemo.ModelView.MediaViewModel;
import ie.droidfactory.instagramdemo.ModelView.UserSelfViewModel;

@UserStatsActivityScope
@Component(modules = {UserStatsActivityModule.class}, dependencies = InstagramApplicationComponent.class)
public interface UserStatsActivityComponent {

//    MediaAdapter mediaAdapter();
//    ServiceInstagram serviceInstagram();
    void injectUserStatsActivity(UserStatsActivity userStatsActivity);
}

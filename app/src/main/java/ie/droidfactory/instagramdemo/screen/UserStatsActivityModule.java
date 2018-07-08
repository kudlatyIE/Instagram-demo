package ie.droidfactory.instagramdemo.screen;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import ie.droidfactory.instagramdemo.screen.adapter.MediaAdapter;

@Module
public class UserStatsActivityModule {

    private final UserStatsActivity userStatsActivity;

    public UserStatsActivityModule(UserStatsActivity userStatsActivity) {
        this.userStatsActivity = userStatsActivity;
    }

    @Provides
    @UserStatsActivityScope
    public UserStatsActivity userStatsActivity(){
        return userStatsActivity;
    }

//    @Provides
//    @UserStatsActivityScope
//    public MediaAdapter mediaAdapter(Picasso picasso){
//        return new MediaAdapter(userStatsActivity, userStatsActivity, picasso);
//    }
}

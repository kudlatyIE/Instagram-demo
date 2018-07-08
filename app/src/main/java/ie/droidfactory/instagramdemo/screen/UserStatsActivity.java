package ie.droidfactory.instagramdemo.screen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import ie.droidfactory.instagramdemo.InstagramApplication;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;
import ie.droidfactory.instagramdemo.screen.adapter.MediaAdapter;
import ie.droidfactory.instagramdemo.ModelView.MediaViewModel;
import ie.droidfactory.instagramdemo.ModelView.UserSelfViewModel;
import ie.droidfactory.instagramdemo.R;
import ie.droidfactory.instagramdemo.model.MediaData;
import ie.droidfactory.instagramdemo.model.MediaRecent;
import ie.droidfactory.instagramdemo.model.UserSelf;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.utils.MySharedPref;
import ie.droidfactory.instagramdemo.utils.ScreenUtils;

/**
 * Display user stats and recent activity...
 */
public class UserStatsActivity extends AppCompatActivity implements MediaAdapter.MediaAdapterOnClickHandle {

    private static final String TAG = UserStatsActivity.class.getSimpleName();
    public static final int TOKEN_REQUEST = 123;
    private int spans;
    private String mToken;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    MediaAdapter mediaAdapter;
    @Inject
    ServiceInstagram service;

    MediaViewModel mediaViewModel;
    UserSelfViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stats_new);
        Button btnLogout = findViewById(R.id.profile_button_logout);
        swipeRefreshLayout = findViewById(R.id.profile_swipe_refresh_layout);
        RecyclerView mRecyclerView = findViewById(R.id.user_media_recycleView);
        spans = getResources().getInteger(R.integer.span_number); // spans number in grid layout (controlled by config.xml)
        Log.d(TAG, "spans number: "+spans);

        UserStatsActivityComponent component = DaggerUserStatsActivityComponent.builder()
                .userStatsActivityModule(new UserStatsActivityModule(this))
                .instagramApplicationComponent(InstagramApplication.get(this).component())
                .build();
        component.injectUserStatsActivity(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, spans);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? spans : 1;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mediaAdapter);

        swipeRefreshLayout.setRefreshing(false);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            mToken= bundle.getString("token");
            getUserStats(mToken, false);
        }else {
            Log.d(TAG, "missing token");
            mToken = MySharedPref.getAccessToken(this);
            if(mToken!=null) getUserStats(mToken, false);
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharedPref.setAccessToken(getApplicationContext(), null);
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("url", ApiUtils.getAuthUrl(getResources().getString(R.string.insta_client_id)));
                startActivityForResult(intent, TOKEN_REQUEST);
                finish();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                if(mToken!=null) getUserStats(mToken, true);
            }
        });
    }

    private void getUserStats(final String token, final boolean refresh){
        userViewModel = ViewModelProviders.of(this).get(UserSelfViewModel.class);
        userViewModel.getUserSelf(service, token, refresh).observe(this, new Observer<UserSelf>() {
            @Override
            public void onChanged(@Nullable UserSelf userSelf) {
                if(userSelf!=null){
                    if(userSelf.getMeta().getCode()==200){
                        mediaAdapter.swapUserData(userSelf.getData(), ScreenUtils.getProfileImageWidth(getApplicationContext(), 0.25,1));
                        getMediaData(token, refresh);
                    }else {
                        Log.d(TAG, "http response error code: "+String.valueOf(userSelf.getMeta().getCode()));
                        Log.d(TAG, userSelf.getMeta().getError_message());
                        Toast.makeText(getApplicationContext(), userSelf.getMeta().getError_message(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void getMediaData(String token, boolean refresh){
        mediaViewModel = ViewModelProviders.of(this).get(MediaViewModel.class);
        mediaViewModel.getMediaRecent(service, token, refresh).observe(this, new Observer<MediaRecent>() {
            @Override
            public void onChanged(@Nullable MediaRecent mediaRecent) {
                if(mediaRecent!=null){
                    if(mediaRecent.getMeta().getCode()==200){
                        mediaAdapter.swapMediaArray(mediaRecent.getDataArray(), ScreenUtils.getProfileImageWidth(getApplicationContext(), 0.9, spans));
                    }else {
                        Log.d(TAG, "http response error code: "+String.valueOf(mediaRecent.getMeta().getCode()));
                        Log.d(TAG, mediaRecent.getMeta().getError_message());
                        Toast.makeText(getApplicationContext(), mediaRecent.getMeta().getError_message(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(MediaData mediaData) {
        Toast.makeText(this, "img title:\n"+mediaData.getCaption().getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}

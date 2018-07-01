package ie.droidfactory.instagramdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import ie.droidfactory.instagramdemo.ModelView.MediaViewModel;
import ie.droidfactory.instagramdemo.ModelView.UserSelfViewModel;
import ie.droidfactory.instagramdemo.model.MediaData;
import ie.droidfactory.instagramdemo.model.MediaRecent;
import ie.droidfactory.instagramdemo.model.UserSelf;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.utils.MySharedPref;
import ie.droidfactory.instagramdemo.utils.ScreenUtils;
import okhttp3.OkHttpClient;

/**
 * Display user stats and recent activity...
 */
public class UserStatsActivity extends AppCompatActivity implements MediaAdapter.MediaAdapterOnClickHandle{

    private static final String TAG = UserStatsActivity.class.getSimpleName();
    public static final int TOKEN_REQUEST = 123;
    private String mToken;
    private TextView tvName, tvPosts, tvFollows, tvFollowsBy;
    private ImageView imgProfile;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MediaAdapter mediaAdapter;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stats_new);
        tvName = findViewById(R.id.profile_text_nick_name);
        tvPosts = findViewById(R.id.profile_text_post_value);
        tvFollows = findViewById(R.id.profile_text_follows_value);
        tvFollowsBy = findViewById(R.id.profile_text_followBy_value);
        imgProfile = findViewById(R.id.profile_imageView);
        Button btnLogout = findViewById(R.id.profile_button_logout);
        swipeRefreshLayout = findViewById(R.id.profile_swipe_refresh_layout);
        RecyclerView mRecyclerView = findViewById(R.id.user_media_recycleView);
        picasso = getPicasso();
        int spans; // spans number in grid layout (2 for portrait, 3 for landscape)
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) spans = 3;
        else spans = 2;
        mediaAdapter = new MediaAdapter(this, this, picasso, ScreenUtils.getProfileImageWidth(this, 0.9, spans));
        GridLayoutManager layoutManager = new GridLayoutManager(this, spans);
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
        UserSelfViewModel authViewModel = ViewModelProviders.of(this).get(UserSelfViewModel.class);
        authViewModel.getUserSelf(token, refresh).observe(this, new Observer<UserSelf>() {
            @Override
            public void onChanged(@Nullable UserSelf userSelf) {
                if(userSelf!=null){
                    if(userSelf.getMeta().getCode()==200){
                        setUserSelfView(userSelf, picasso);
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
        MediaViewModel mediaViewModel = ViewModelProviders.of(this).get(MediaViewModel.class);
        mediaViewModel.getMediaRecent(token, refresh).observe(this, new Observer<MediaRecent>() {
            @Override
            public void onChanged(@Nullable MediaRecent mediaRecent) {
                if(mediaRecent!=null){
                    if(mediaRecent.getMeta().getCode()==200){
                        mediaAdapter.swapMediaArray(mediaRecent.getDataArray());
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

    private void setUserSelfView(UserSelf userData, Picasso picasso){
        tvName.setText(userData.getData().getUsername());
        tvPosts.setText(String.valueOf(userData.getData().getCounts().getMedia()));
        tvFollows.setText(String.valueOf(userData.getData().getCounts().getFollows()));
        tvFollowsBy.setText(String.valueOf(userData.getData().getCounts().getFollowed_by()));
        int size = ScreenUtils.getProfileImageWidth(this, 0.25,1);
        //profile image resize to 20% of screen width
        picasso.load(userData.getData().getProfile_picture())
                .placeholder(R.drawable.ic_person_black_24dp)
                .resize(size, size)
                .into(imgProfile);
    }

    private Picasso getPicasso(){
        return new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(new OkHttpClient.Builder().build()))
                .build();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

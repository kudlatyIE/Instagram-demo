package ie.droidfactory.instagramdemo.screen.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import ie.droidfactory.instagramdemo.R;
import ie.droidfactory.instagramdemo.model.UserData;

/**
 * Holder for UsedSelf data
 */
@SuppressLint("ViewConstructor")
public class UserItemViewHolder extends RecyclerView.ViewHolder{

    private final Picasso picasso;

    @BindView(R.id.user_item_profile_text_nick_name)
    TextView tvName;

    @BindView(R.id.user_item_profile_text_post_value)
    TextView tvPosts;

    @BindView(R.id.user_item_profile_text_follows_value)
    TextView tvFollows;

    @BindView(R.id.user_item_profile_text_followBy_value)
    TextView tvFollowsBy;

    @BindView(R.id.user_item_profile_imageView)
    ImageView imgProfile;

    public UserItemViewHolder(View itemView, Picasso picasso) {
        super(itemView);
        this.picasso = picasso;
        ButterKnife.bind(this,itemView);
    }

    public void setUserItem(UserData userData, int imgSize){
        tvName.setText(userData.getUsername());
        tvPosts.setText(String.valueOf(userData.getCounts().getMedia()));
        tvFollows.setText(String.valueOf(userData.getCounts().getFollows()));
        tvFollowsBy.setText(String.valueOf(userData.getCounts().getFollowed_by()));
        //profile image resize to 20% of screen width
        picasso.load(userData.getProfile_picture())
                .placeholder(R.drawable.ic_person_black_24dp)
                .resize(imgSize, imgSize)
                .into(imgProfile);
    }
}

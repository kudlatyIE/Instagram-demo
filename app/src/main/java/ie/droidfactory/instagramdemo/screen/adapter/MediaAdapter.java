package ie.droidfactory.instagramdemo.screen.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import ie.droidfactory.instagramdemo.R;
import ie.droidfactory.instagramdemo.model.MediaData;
import ie.droidfactory.instagramdemo.model.UserData;
import ie.droidfactory.instagramdemo.screen.UserStatsActivity;

/**
 * First element inflates different layout (user self data)
 * All next elements inflate layout for MediaData
 */
public class MediaAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = MediaAdapter.class.getSimpleName();
    private Context mContext;
    private MediaData[] mediaDataArray;
    private UserData userData;
    private MediaAdapterOnClickHandle mClickHandler;
    private Picasso mPicasso;
    private int mediaSize, profileSize;
    private static final int VIEW_TYPE_USER = 0;
    private static final int VIEW_TYPE_MEDIA = 1;

    public interface MediaAdapterOnClickHandle{
        void onClick(MediaData mediaData);
    }

    @Inject
    public MediaAdapter(@NonNull UserStatsActivity context, UserStatsActivity clickHandler, Picasso picasso){
        this.mContext=context;
        this.mClickHandler=clickHandler;
        this.mPicasso=picasso;
    }

    public void swapMediaArray(MediaData[] data, int mediaSize){
        this.mediaSize=mediaSize;
        this.mediaDataArray=data;
        notifyDataSetChanged();
    }
    public void swapUserData(UserData userData, int profileSize){
        this.profileSize=profileSize;
        this.userData=userData;
        notifyItemChanged(0);
    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            parent.setElevation(10);
        switch (viewType){
            case VIEW_TYPE_USER:
                View viewUser = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
                return new UserItemViewHolder(viewUser, mPicasso);
            case VIEW_TYPE_MEDIA:
                View viewMedia = LayoutInflater.from(mContext).inflate(R.layout.media_item, parent,false);
                viewMedia.setFocusable(true);
                return new MediaItemViewHolder(viewMedia, mClickHandler, mPicasso);

                default:
                    return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_USER:
                UserItemViewHolder userHolder = (UserItemViewHolder) holder;
                userHolder.setUserItem(userData, profileSize);
                break;
            case VIEW_TYPE_MEDIA:
                MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) holder;
                mediaItemViewHolder.setMediaItem(mediaDataArray[position-1], mediaSize);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(null==mediaDataArray & null==userData) return 0;
        if(null!=userData){
            if(null!=mediaDataArray) return mediaDataArray.length+1;
            else return 1;
        }
        if(null!=mediaDataArray) return mediaDataArray.length;
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position==0 ? VIEW_TYPE_USER : VIEW_TYPE_MEDIA;
    }

}

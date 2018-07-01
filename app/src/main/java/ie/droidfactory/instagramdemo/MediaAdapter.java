package ie.droidfactory.instagramdemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import ie.droidfactory.instagramdemo.model.MediaData;

public class MediaAdapter  extends RecyclerView.Adapter<MediaAdapter.MediaAdapterViewHolder>{

    private static final String TAG = MediaAdapter.class.getSimpleName();
    private Context mContext;
    private MediaData[] mediaDataArray;
    private MediaAdapterOnClickHandle mClickHandler;
    private Picasso mPicasso;
    private int imageSize;

    public interface MediaAdapterOnClickHandle{
        void onClick(MediaData mediaData);
    }

    public MediaAdapter(@NonNull Context context, MediaAdapterOnClickHandle clickHandler, Picasso picasso, int imageSize){
        this.mContext=context;
        this.mClickHandler=clickHandler;
        this.mPicasso=picasso;
        this.imageSize=imageSize;
    }

    public void swapMediaArray(MediaData[] data){
        this.mediaDataArray=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MediaAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            parent.setElevation(10);
        View view = LayoutInflater.from(mContext).inflate(R.layout.media_item, parent, false);
        view.setFocusable(true);
        return new MediaAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaAdapterViewHolder holder, int position) {
        mPicasso.load(mediaDataArray[position].getImages().getLowResolution().getUrl())
                .placeholder(R.drawable.ic_person_black_24dp)
                .resize(imageSize, imageSize)
                .into(holder.imgThumbnail);
        holder.tvLikes.setText(String.valueOf(mediaDataArray[position].getLikes().getCount()));
    }

    @Override
    public int getItemCount() {
        if(null==mediaDataArray) return 0;
        return mediaDataArray.length;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    class MediaAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgThumbnail;
        TextView tvLikes;

        public MediaAdapterViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.media_item_image_profile);
            tvLikes = itemView.findViewById(R.id.media_item_text_likes_value);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(mediaDataArray[adapterPosition]);
        }
    }
}

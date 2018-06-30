package ie.droidfactory.instagramdemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import ie.droidfactory.instagramdemo.model.MediaData;
import okhttp3.OkHttpClient;

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

    //TODO: implement dependency injections instead of this....
    public MediaAdapter(@NonNull Context context, MediaAdapterOnClickHandle clickHandler, Picasso picasso, int imageSize){
        this.mContext=context;
        this.mClickHandler=clickHandler;
//        this.mPicasso=picasso(context, okHttp3Downloader(okHttpClient.build()));
        this.mPicasso=picasso;
        this.imageSize=imageSize;
    }

//    private OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
//
//    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
//        return new OkHttp3Downloader(okHttpClient);
//    }
//
//    private Picasso picasso(Context context, OkHttp3Downloader okHttp3Downloader) {
//        return new Picasso.Builder(context)
//                .downloader(okHttp3Downloader)
//                .build();
//    }

    public void swapMediaArray(MediaData[] data){
        Log.d(TAG, "swapping data size: "+data.length);
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
        Log.d(TAG, "oBVH image url: "+mediaDataArray[position].getImages().getLowResolution().getUrl());
        Log.d(TAG, "oBVH image title: "+mediaDataArray[position].getCaption().getText());
        mPicasso.load(mediaDataArray[position].getImages().getLowResolution().getUrl())
                .placeholder(R.drawable.ic_person_black_24dp)
                .resize(imageSize, imageSize)
                .into(holder.imgThumbnail);
        holder.tvTitle.setText(mediaDataArray[position].getCaption().getText());
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
        TextView tvTitle;

        public MediaAdapterViewHolder(View itemView) {
            super(itemView);

            imgThumbnail = itemView.findViewById(R.id.media_item_image);
            tvTitle = itemView.findViewById(R.id.media_item_text_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //TODO:implement Toast or something...
            int adapterPosition = getAdapterPosition();
            Log.d(TAG, "adapter position clicked at: "+adapterPosition);
            mClickHandler.onClick(mediaDataArray[adapterPosition]);
        }
    }
}

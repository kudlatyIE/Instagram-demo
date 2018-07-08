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
import ie.droidfactory.instagramdemo.model.MediaData;

/**
 * Data holder for MediaData
 */
@SuppressLint("ViewConstructor")
public class MediaItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final Picasso picasso;
    private MediaAdapter.MediaAdapterOnClickHandle mediaAdapterOnClickHandle;
    private MediaData mediaData;

    @BindView(R.id.media_item_image_content)
    ImageView imgMedia;

    @BindView(R.id.media_item_text_likes_value)
    TextView tvLikes;

    public MediaItemViewHolder(View itemView, MediaAdapter.MediaAdapterOnClickHandle mediaAdapterOnClickHandle, Picasso picasso) {
        super(itemView);
        this.mediaAdapterOnClickHandle=mediaAdapterOnClickHandle;
        this.picasso = picasso;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setMediaItem(MediaData mediaData, int mediaSize){
       this. mediaData=mediaData;
        tvLikes.setText(String.valueOf(mediaData.getLikes().getCount()));
        picasso.load(mediaData.getImages().getLowResolution().getUrl())
                .placeholder(R.drawable.ic_person_black_24dp)
                .resize(mediaSize, mediaSize)
                .into(imgMedia);
    }

    @Override
    public void onClick(View view) {
        mediaAdapterOnClickHandle.onClick(mediaData);
    }
}



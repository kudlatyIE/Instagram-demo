package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaLikes {

    @SerializedName("count")
    @Expose
    private int count;

    public int getCount() {
        return count;
    }
}

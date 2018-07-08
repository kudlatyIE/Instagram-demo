package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaComments {

    @SerializedName("count")
    @Expose
    private int count;

    public int getCount() {
        return count;
    }
}

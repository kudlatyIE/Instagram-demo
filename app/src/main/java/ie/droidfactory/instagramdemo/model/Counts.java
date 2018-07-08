package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts {

    @SerializedName("media")
    @Expose
    private int media;

    @SerializedName("follows")
    @Expose
    private int follows;

    @SerializedName("followed_by")
    @Expose
    private int followed_by;

    public int getMedia() {
        return media;
    }

    public int getFollows() {
        return follows;
    }

    public int getFollowed_by() {
        return followed_by;
    }
}

/*
"counts": {
      "media": 1320,
      "follows": 420,
      "followed_by": 3410
    }
 */

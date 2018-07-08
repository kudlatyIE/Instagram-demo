package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaImages {

    @SerializedName("thumbnail")
    @Expose
    private MediaObject thumbnail;

    @SerializedName("low_resolution")
    @Expose
    private MediaObject low_resolution;

    @SerializedName("standard_resolution")
    @Expose
    private MediaObject standard_resolution;

    public MediaObject getThumbnail() {
        return thumbnail;
    }

    public MediaObject getLowResolution() {
        return low_resolution;
    }

    public MediaObject getStandardResolution() {
        return standard_resolution;
    }
}

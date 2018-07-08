package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class MediaVideos {


    @SerializedName("standard_resolution")
    @Expose
    private MediaObject standard_resolution;

    @SerializedName("low_bandwidth")
    @Expose
    private MediaObject low_bandwidth;

    @SerializedName("low_resolution")
    @Expose
    private MediaObject low_resolution;

    public MediaObject getStandardResolution() {
        return standard_resolution;
    }

    public MediaObject getLowBandwidth() {
        return low_bandwidth;
    }

    public MediaObject getLowLesolution() {
        return low_resolution;
    }
}

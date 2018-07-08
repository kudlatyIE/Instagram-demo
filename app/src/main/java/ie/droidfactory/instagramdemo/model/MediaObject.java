package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * media as image and video
 * id only if media == video
 */
public class MediaObject {

    @SerializedName("width")
    @Expose
    private int width;

    @SerializedName("height")
    @Expose
    private int height;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("id")
    @Expose
    private String id;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }
}

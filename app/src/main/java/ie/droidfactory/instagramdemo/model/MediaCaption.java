package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaCaption {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("text")
    @Expose
    private String text; // uploaded media title - shows in gallery

    @SerializedName("created_time")
    @Expose
    private String created_time; // long timestamp as String

    @SerializedName("from")
    @Expose
    private MediaUser from;

    public String getId() {
        return id;
    }

    /**
     *
     * @return media title
     */
    public String getText() {
        return text;
    }

    public String getCreatedTime() {
        return created_time;
    }

    public MediaUser getFrom() {
        return from;
    }
}

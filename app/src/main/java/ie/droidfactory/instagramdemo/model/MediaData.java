package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * single media object from media/recent
 */
public class MediaData {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("user")
    @Expose
    private MediaUser user;

    @SerializedName("videos")
    @Expose
    private MediaVideos videos;

    @SerializedName("images")
    @Expose
    private MediaImages images;

    @SerializedName("created_time")
    @Expose
    private String created_time;

    @SerializedName("caption")
    @Expose
    private MediaCaption caption;

    @SerializedName("user_has_liked")
    @Expose
    private boolean user_has_liked;

    @SerializedName("likes")
    @Expose
    private MediaLikes likes;

    @SerializedName("tags")
    @Expose
    private String[] tags;

    @SerializedName("filter")
    @Expose
    private String filter;

    @SerializedName("comments")
    @Expose
    private MediaComments comments;

    @SerializedName("type")
    @Expose
    private String type; //images or video

    @SerializedName("link")
    @Expose
    private String link; //url to media on instagram page

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("attribution")
    @Expose
    private String[] attribution; //unknown stuff

    @SerializedName("users_in_photo")
    @Expose
    private String[] users_in_photo; //i guess that is array of taged user_name

    public String getId() {
        return id;
    }

    public MediaUser getUser() {
        return user;
    }

    public MediaVideos getVideos() {
        return videos;
    }

    public MediaImages getImages() {
        return images;
    }

    public String getCreated_time() {
        return created_time;
    }

    public MediaCaption getCaption() {
        return caption;
    }

    public boolean isUser_has_liked() {
        return user_has_liked;
    }

    public MediaLikes getLikes() {
        return likes;
    }

    public String[] getTags() {
        return tags;
    }

    public String getFilter() {
        return filter;
    }

    public MediaComments getComments() {
        return comments;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public String getLocation() {
        return location;
    }

    public String[] getAttribution() {
        return attribution;
    }

    public String[] getUsers_in_photo() {
        return users_in_photo;
    }
}

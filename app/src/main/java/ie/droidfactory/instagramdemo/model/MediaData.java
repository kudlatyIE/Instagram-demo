package ie.droidfactory.instagramdemo.model;

/**
 * single media object from media/recent
 */
public class MediaData {

    private String id;
    private MediaUser user;
    private MediaVideos videos;
    private MediaImages images;
    private String created_time;
    private MediaCaption caption;
    private boolean user_has_liked;
    private MediaLikes likes;
    private String[] tags;
    private String filter;
    private MediaComments comments;
    private String type; //images or video
    private String link; //url to media on instagram page
    private String location;
    private String[] attribution; //unknown stuff
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

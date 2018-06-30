package ie.droidfactory.instagramdemo.model;

public class MediaCaption {

    private String id;
    private String text; // uploaded media title - shows in gallery
    private String created_time; // long timestamp as String

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

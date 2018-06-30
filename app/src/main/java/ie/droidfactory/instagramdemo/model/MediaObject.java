package ie.droidfactory.instagramdemo.model;

/**
 * media as image and video
 * id only if media == video
 */
public class MediaObject {

    private int width;
    private int height;
    private String url;
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

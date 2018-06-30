package ie.droidfactory.instagramdemo.model;

public class MediaImages {

    private MediaObject thumbnail;
    private MediaObject low_resolution;
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

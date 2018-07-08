package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("full_name")
    @Expose
    private String full_name;

    @SerializedName("profile_picture")
    @Expose
    private String profile_picture;

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("website")
    @Expose
    private String website;

    @SerializedName("is_business")
    @Expose
    private boolean is_business;

    @SerializedName("counts")
    @Expose
    private Counts counts;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public boolean isIs_business() {
        return is_business;
    }

    public Counts getCounts() {
        return counts;
    }
    @Override
    public String toString() {
        return "UserSelf{" +
                "\nid='" + id + '\'' +
                ", \nusername='" + username + '\'' +
                ", \nfull_name='" + full_name + '\'' +
//                ", profile_picture='" + profile_picture + '\'' +
                ", \nbio='" + bio + '\'' +
                ", \nwebsite='" + website + '\'' +
                ", \nis_business= " + is_business +
                ", \nfollowed by= " + counts.getFollowed_by() +
                ", \nfollows = " + counts.getFollows() +
                ", \nmedia= " + counts.getMedia() +
                '}';
    }
}

package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * not used
 */
public class User {

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
}


/*
{
    "access_token": "fb2e77d.47a0479900504cb3ab4a1f626d174d2d",
    "user": {
        "id": "1574083",
        "username": "snoopdogg",
        "full_name": "Snoop Dogg",
        "profile_picture": "..."
    }
}
 */
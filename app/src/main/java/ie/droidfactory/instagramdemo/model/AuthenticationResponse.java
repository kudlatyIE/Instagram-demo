package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse {

    @SerializedName("access_token")
    @Expose
    private String access_token;
    @SerializedName("user")
    @Expose
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public User getUser() {
        return user;
    }
}

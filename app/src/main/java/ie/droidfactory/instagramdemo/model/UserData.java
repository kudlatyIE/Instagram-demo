package ie.droidfactory.instagramdemo.model;

public class UserData {

    private String id;
    private String username;
    private String full_name;
    private String profile_picture;
    private String bio;
    private String website;
    private boolean is_business;
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

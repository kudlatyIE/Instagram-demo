package ie.droidfactory.instagramdemo.model;

/**
 * user/self pojo model
 */
public class UserSelf {

    private UserData data;
    private MetaData meta;

    public MetaData getMeta() {
        return meta;
    }

    public UserData getData() {
        return data;
    }
}

/*

{
  "data": {
    "id": "1574083",
    "username": "snoopdogg",
    "full_name": "Snoop Dogg",
    "profile_picture": "http://distillery.s3.amazonaws.com/profiles/profile_1574083_75sq_1295469061.jpg",
    "bio": "This is my bio",
    "website": "http://snoopdogg.com",
    "is_business": false,
    "counts": {
      "media": 1320,
      "follows": 420,
      "followed_by": 3410
    }
  }
}
 */
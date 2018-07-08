package ie.droidfactory.instagramdemo.network;

public class ApiUtils {

    private final static String SEVICE_AUTH_ENDPOINT_START =
        "https://api.instagram.com/oauth/authorize/?client_id=";
    private final static String SEVICE_AUTH_ENDPOINT_END =
            "&redirect_uri=https://kudlatyie.github.io&response_type=token";
    static final String PARAM_ACCESS_TOKEN = "access_token";

    public static final String ENDPOINT_SELF = "https://api.instagram.com/v1/users/self/";
    static final String URL_RECENT_MEDIA = "media/recent/";

    public static String getAuthUrl(String clientId){
        if(null==clientId) return null;
        else return SEVICE_AUTH_ENDPOINT_START.concat(clientId).concat(REDIRECT.concat(redirectUrl).concat(RESPONSE_TYPE));
    }
}

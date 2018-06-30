package ie.droidfactory.instagramdemo.network;

public class ApiUtils {

//    public final static String SEVICE_ENDPOINT = "https://api.instagram.com/oauth/authorize/?client_id=CLIENT-ID&redirect_uri=REDIRECT-URI&response_type=token";
    private final static String SEVICE_AUTH_ENDPOINT_START =
        "https://api.instagram.com/oauth/authorize/?client_id=";
    private final static String SEVICE_AUTH_ENDPOINT_END = "&redirect_uri=https://kudlatyie.github.io&response_type=token";
    public static final String SEVICE_ENDPOINT = "https://api.instagram.com/";
    public static final String URL_AUTH = "oauth/authorize";
    public static final String PARAM_CLIENT_ID="client_id";
    public static final String PARAM_REDIRECT_URI="redirect_uri";
    public static final String PARAM_RESPONSE_TYPE="response_type";
    public static final String PARAM_ACCESS_TOKEN = "access_token";

    public static final String ENDPOINT_SELF = "https://api.instagram.com/v1/users/self/";
    public static final String URL_RECENT_MEDIA = "media/recent/";

    public static String getAuthUrl(String clientId){
        if(null==clientId) return null;
        else return SEVICE_AUTH_ENDPOINT_START.concat(clientId).concat(SEVICE_AUTH_ENDPOINT_END);
    }
}

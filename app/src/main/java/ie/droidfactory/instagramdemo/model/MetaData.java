package ie.droidfactory.instagramdemo.model;

/**
 * http response json object contains:
 * code: int (http response code),
 * error_type: String,
 * error_message: String
 */
public class MetaData {
    private int code;
    private String error_type;
    private String error_message;

    public int getCode() {
        return code;
    }

    public String getError_type() {
        return error_type;
    }

    public String getError_message() {
        return error_message;
    }
}

/*
{
	"meta": {
		"code": 400,
		"error_type": "OAuthAccessTokenException",
		"error_message": "The access_token provided is invalid."
	}
}
 */

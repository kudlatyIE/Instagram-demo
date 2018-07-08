package ie.droidfactory.instagramdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * http response json object contains:
 * code: int (http response code),
 * error_type: String,
 * error_message: String
 */
public class MetaData {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error_type")
    @Expose
    private String error_type;

    @SerializedName("error_message")
    @Expose
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

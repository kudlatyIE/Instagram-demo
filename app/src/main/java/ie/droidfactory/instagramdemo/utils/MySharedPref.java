package ie.droidfactory.instagramdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPref {

    private final static String PREF="MY_SETTINGS";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    /**
     *
     * @param context application context
     * @param token remove data if null
     */
    public static void setAccessToken(Context context, String token){
        SharedPreferences settings = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        if(null==token) editor.clear();
        else editor.putString(KEY_ACCESS_TOKEN, token);
        editor.apply();
    }

    public static String getAccessToken(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return settings.getString(KEY_ACCESS_TOKEN, null);
    }
}

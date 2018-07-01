package ie.droidfactory.instagramdemo.utils;

import android.content.Context;
import android.content.res.Configuration;

public class ScreenUtils {

    /**
     * calculate optimized width of profile picture as 20% of screen width
     * to keep small size of profile pic (span==1) keep small side of screen as width
     * For media images return size too fit span width
     * @param context current Context
     * @param ratio part of sigle span for image
     * @param spans span number for images
     * @return int profile pic width
     *
     */
    public static int getProfileImageWidth(Context context, double ratio, int spans){
        int size;
        int orientation = context.getResources().getConfiguration().orientation;
        if(spans==1 & orientation== Configuration.ORIENTATION_LANDSCAPE)
            size = context.getResources().getDisplayMetrics().heightPixels;
        else size = context.getResources().getDisplayMetrics().widthPixels;
        return (int) (size/spans*ratio);
    }

}

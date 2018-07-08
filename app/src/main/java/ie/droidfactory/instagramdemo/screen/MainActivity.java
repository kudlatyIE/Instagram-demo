package ie.droidfactory.instagramdemo.screen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ie.droidfactory.instagramdemo.R;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.utils.MySharedPref;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    public static final int TOKEN_REQUEST = 12333;
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(MySharedPref.getAccessToken(this)==null){
            //redirect to web login page if user has no access token, after logout also
            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
            intent.putExtra("url", ApiUtils.getAuthUrl(getResources().getString(R.string.insta_client_id)));
            startActivityForResult(intent, TOKEN_REQUEST);
        }else {
            //Go to user stats activity
            Intent intent = new Intent(getApplicationContext(), UserStatsActivity.class);
            intent.putExtra("token", MySharedPref.getAccessToken(getApplicationContext()));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==TOKEN_REQUEST){
            if(resultCode==Activity.RESULT_OK){
                if(data.getStringExtra("token")!=null){
                    String token = data.getStringExtra("token");
                    MySharedPref.setAccessToken(this, token);
                    Intent intent = new Intent(getApplicationContext(), UserStatsActivity.class);
                    intent.putExtra("token", token);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Log.d(TAG, "received Error: "+data.getStringExtra("error"));
                }
            }else {
                Log.d(TAG, "received Error: "+data.getStringExtra("error"));

            }
        }
    }
}

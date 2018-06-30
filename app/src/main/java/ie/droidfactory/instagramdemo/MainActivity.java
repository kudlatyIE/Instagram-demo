package ie.droidfactory.instagramdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.utils.MySharedPref;

/**
 * TODO: extract access_token from WebView onPageFinished - by tut https://www.youtube.com/watch?v=QVhy848hVSI
 * and
 * TODO: https://www.youtube.com/watch?v=HYiKeOcyemc
 */
public class MainActivity extends AppCompatActivity {

//    private Button btnLogin;
    private TextView tvInfo;
    public static final int TOKEN_REQUEST = 123;
    private final static String TAG = MainActivity.class.getSimpleName();

//    private final static String TEST_URL =
//            "https://api.instagram.com/oauth/authorize/?client_id=29138f70c21b4dc0a2e672ce534a0f97&redirect_uri=https://kudlatyie.github.io&response_type=token";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MySharedPref.getAccessToken(this)==null){
            //TODO: remove layout, move code into
            setContentView(R.layout.activity_main);
            tvInfo = findViewById(R.id.start_text_info);
//            btnLogin = findViewById(R.id.start_login_button);
//            btnLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
//                    intent.putExtra("url", TEST_URL);
//                    startActivityForResult(intent, TOKEN_REQUEST);
//                }
//            });
        }else {
            //Go to user stats
            Intent intent = new Intent(getApplicationContext(), UserStatsActivity.class);
            intent.putExtra("token", MySharedPref.getAccessToken(getApplicationContext()));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MySharedPref.getAccessToken(this)==null){
            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
            intent.putExtra("url", ApiUtils.getAuthUrl(getResources().getString(R.string.insta_client_id)));
            startActivityForResult(intent, TOKEN_REQUEST);
        }else {
            //Go to user stats
            Intent intent = new Intent(getApplicationContext(), UserStatsActivity.class);
            intent.putExtra("token", MySharedPref.getAccessToken(getApplicationContext()));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d(TAG, "Acc Result:::: request code: "+requestCode+" result code: "+resultCode);
        if(requestCode==TOKEN_REQUEST){
            if(data.getStringExtra("token")!=null){
                String token = data.getStringExtra("token");
                Log.d(TAG, "received token: "+token);
                Toast.makeText(this, "token: "+token, Toast.LENGTH_SHORT).show();
//                getMediaData(token);
                MySharedPref.setAccessToken(this, token);
                Intent intent = new Intent(getApplicationContext(), UserStatsActivity.class);
                intent.putExtra("token", token);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else {
                Log.d(TAG, "received Error: "+data.getStringExtra("error"));
                tvInfo.setTag("Sorry, we have a problem with authentication");
            }
        }
    }
}

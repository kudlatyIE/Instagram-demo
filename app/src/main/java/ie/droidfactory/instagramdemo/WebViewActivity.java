package ie.droidfactory.instagramdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *
 */
public class WebViewActivity extends AppCompatActivity {

    private final static String TAG = WebViewActivity.class.getSimpleName();

    private String accessToken;
    private String requestUrl;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.instagram_webview);
        if(getIntent().getExtras()!=null){
            requestUrl = getIntent().getExtras().getString("url", null);
            Log.d(TAG, "request url: "+requestUrl);
            loadWebView(requestUrl);
        }else {
            Log.d(TAG, "empty url from bundle");
        }
    }

    private void loadWebView(String startUrl){
        if(startUrl==null) return;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG, "on page started: "+url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Intent resultIntent = new Intent();
                Log.d(TAG, "FINISH URL: "+url);
                if(url.contains("#access_token=")){
                    Uri uri = Uri.parse(url);
                    String token = uri.getEncodedFragment();
                    accessToken = token.substring(token.lastIndexOf("=")+1);
                    Log.d(TAG, "access token: "+accessToken);
                }else {
                    Log.d(TAG, "not token in redirected url...");
                }
                if(accessToken!=null){
                    view.clearCache(true);
                    CookieSyncManager.createInstance(getApplicationContext());
                    CookieManager cookieManager = CookieManager.getInstance();
                    cookieManager.removeAllCookie();
                    resultIntent.putExtra("token",accessToken);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
        webView.loadUrl(startUrl);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO: finish app
    }
}

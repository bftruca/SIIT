package ro.magdamiu.myapplication.module3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import ro.magdamiu.myapplication.R;

public class WebViewActivity extends AppCompatActivity {

    private static final String ANDROID_URL = "https://developer.android.com/";

    private WebView mWebViewAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_example);

        initView();
    }

    private void initView() {
        mWebViewAndroid = findViewById(R.id.web_view_android);

        // allow to use javascript
        WebSettings webSettings = mWebViewAndroid.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // load the URL
        mWebViewAndroid.loadUrl(ANDROID_URL);
    }
}

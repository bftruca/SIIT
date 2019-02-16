package ro.bogdantruca.siitandroidfundamentals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebViewAndroid;

    private static final String ANDROID_URL = "https://developer.android.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_example);

        initView();
    }

    private void initView() {
        mWebViewAndroid = findViewById(R.id.web_view_android);

        //allow to use javascript
        WebSettings webSettings = mWebViewAndroid.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // load the url
        mWebViewAndroid.loadUrl(ANDROID_URL);
    }
}

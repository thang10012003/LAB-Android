package vn.edu.tdtu.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebBrowser extends AppCompatActivity {

    public static final String EXTRA_MESSAGE1 = "vn.tdtu.edu.MESSAGE";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        webView = findViewById(R.id.wv);

        Intent intent = getIntent();
        String mess = intent.getStringExtra(bai2_lab3.EXTRA_MESSAGE);
        if(mess != null){
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(mess);
        }

    }
}
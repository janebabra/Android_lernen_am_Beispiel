package com.greendog.example.webapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Roundball extends Activity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roundball);

        myWebView = (WebView) findViewById(R.id.webView1);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_asset/roundball/roundball.html");

    }

}

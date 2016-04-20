package com.pauric.fyp_gym;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutPage extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.clearCache(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                mWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('header')[0].style.display='none'; " +
                        "})()");
                mWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('topBar')[0].style.display='none'; " +
                        "})()");

                mWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('smallFooter')[0].style.display='none'; " +
                        "})()");


                mWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('footer')[0].style.display='none'; " +
                        "})()");


            }
        });
        mWebView.loadUrl("http://warehousegym.ie/the-gym/");
    }

}

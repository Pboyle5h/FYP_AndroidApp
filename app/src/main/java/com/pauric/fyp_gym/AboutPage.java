package com.pauric.fyp_gym;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutPage extends Activity {
    //declare the webview
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //To prevent the title bar from showing on each intent
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        //point to the webview in the activity
        //setting used to setup and edit webview
        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.clearCache(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
       //use a web client so that the users default browser isnt loaded.
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            //embeded javascript so that the webview only opens with the information wanted
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
        //load url
        mWebView.loadUrl("http://warehousegym.ie/the-gym/");
    }

}

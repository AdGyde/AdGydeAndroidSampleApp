package com.adgyde.example;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.adgyde.android.AdGyde;

public class WebViewActivity extends AppCompatActivity {

    WebView web_view;
    String url ="https://www.adgyde.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        web_view = (WebView) findViewById(R.id.web_view);
        web_view.setWebViewClient(new MyWebViewClient());

        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.loadUrl(url);

    }
}

class MyWebViewClient extends WebViewClient {

    Context context;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) 
    {
        view.loadUrl(url);
        String pageURL = view.getUrl();
        String pageName="";

        Log.d("MyWebViewClient", "Page URL = " + pageURL);
        int countSls=pageURL.lastIndexOf("/")+1;
        int countDot=pageURL.lastIndexOf(".");

        if((countDot-countSls)>0) 
		{
            pageName= pageURL.substring(pageURL.lastIndexOf("/") + 1, pageURL.lastIndexOf("."));
            Log.d("MyWebViewClient", "Page Name = " + pageName);
            AdGyde.setCurrentScreen(context,pageName);
        }
		else
		{
            pageName=pageURL.substring(pageURL.lastIndexOf("/") + 1, pageURL.length());
            AdGyde.setCurrentScreen(context,pageName);
            Log.d("MyWebViewClient", "Page Name = " + pageName);
        }

        return true;
    }


}

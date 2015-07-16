package com.sgxp.asgmt1.assignment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.group.studyproject.R;

public class MainWebActivity extends Activity{

	WebView main_webview;
	TextView screen_message;
	String address;
	
@Override
protected void onCreate(Bundle savedInstanceState) {

	setContentView(R.layout.asgmt1_main_web);
	
	
	main_webview = (WebView) findViewById(R.id.main_webview);
	screen_message = (TextView) findViewById(R.id.webmessage);
	
	Intent i = getIntent();
	if(i!=null){
		address = i.getStringExtra("address");
		main_webview.setWebViewClient(new MyWebViewClient());
		main_webview.getSettings().setJavaScriptEnabled(true);
		
		screen_message.setText("Starting "+address);
		

		main_webview.getSettings().setBuiltInZoomControls(true);
		main_webview.getSettings().setLoadWithOverviewMode(true);
		main_webview.getSettings().setUseWideViewPort(true);
		main_webview.loadUrl(address);
		
		
	}
	
	
	super.onCreate(savedInstanceState);
}

private class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
    
    
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
    	screen_message.setText("Loading "+address);
    	super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url)
    {
    	screen_message.setText("Loaded "+address);
        super.onPageFinished(view, url);
    }
}
}

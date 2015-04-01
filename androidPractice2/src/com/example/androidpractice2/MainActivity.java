package com.example.androidpractice2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity {
	String initialUrl;
	WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(getIntent().getStringExtra("url") == null){
        	initialUrl = "http://www.naver.com";
        } else {
        	initialUrl = getIntent().getStringExtra("url");
        }
        
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(initialUrl);
        mWebView.setWebViewClient(new WebViewClient());
        
        
        Button setUrlBtn = (Button) findViewById(R.id.urlBtn);
        
        setUrlBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("onClick","SelectChannel");
				Intent intentSubActivity =
						new Intent(MainActivity.this , SetUrlActivity.class);
				
				intentSubActivity.putExtra("tempUrl", initialUrl);
				
				startActivity(intentSubActivity);
			}
        });
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
    		mWebView.goBack();
    		return true;
    	}
      return super.onKeyDown(keyCode, event);
    	
    }
}

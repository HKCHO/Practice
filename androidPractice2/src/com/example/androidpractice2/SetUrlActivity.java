package com.example.androidpractice2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SetUrlActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.seturlactivity);
	    
	    Button naverBtn = (Button) findViewById(R.id.naverBtn);
	    Button googleBtn = (Button) findViewById(R.id.googleBtn);
	    Button daumBtn = (Button) findViewById(R.id.daumBtn);
	    Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
	    
	    naverBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("onClick","naver");
				Intent intentSubActivity =
						new Intent(SetUrlActivity.this , MainActivity.class);
				intentSubActivity.putExtra("url", "http://m.naver.com");
				startActivity(intentSubActivity);
			}
	    });
	    
	    googleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("onClick","google");
				
				Intent intentSubActivity =
						new Intent(SetUrlActivity.this , MainActivity.class);
				intentSubActivity.putExtra("url", "http://m.google.com");
				
				startActivity(intentSubActivity);
			}
	    });
	    
	    daumBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("onClick","daum");
				Intent intentSubActivity =
						new Intent(SetUrlActivity.this , MainActivity.class);
				intentSubActivity.putExtra("url", "http://m.daum.net");
				startActivity(intentSubActivity);
			}
	    });
        
        cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("onClick","Cancel");
				Intent intentSubActivity =
						new Intent(SetUrlActivity.this , MainActivity.class);
				intentSubActivity.putExtra("url", getIntent().getStringExtra("tempUrl"));
				
				startActivity(intentSubActivity);
			}
        });
	}
	


}

package com.example.apppractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnCallMain = (Button) findViewById(R.id.BtnCalltoMonthName);

		btnCallMain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Log.i("onClick", "CallMonthName");
				Intent intentSubActivity = 
						new Intent(MainActivity.this, MonthName.class);
				startActivity(intentSubActivity);
			}

		});

	}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
    }*/

}


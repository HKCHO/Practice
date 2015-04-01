package com.example.apppractice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MonthName extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subactivity);
		Button btnCallMain = (Button) findViewById(R.id.BtnCalltoMain);
		btnCallMain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("onClick", "CallMainActivity");
				finish();
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

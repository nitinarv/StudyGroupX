package com.sgxp.intenttest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.group.studyproject.R;

public class MyParcelActivity extends Activity {

	TextView parcelMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parcel_activity);
		
		parcelMessage = (TextView)findViewById(R.id.textViewparcelmessage);
		
	}

	@Override
	protected void onResume() {
		super.onResume();

		Bundle b = getIntent().getExtras();
		ObjectA obj1 = b.getParcelable("com.package.ObjectA");
		String message = "ObjectA Rcvd: int: " + obj1.getIntValue()
				+ ", String: " + obj1.getStrValue();
		Log.d("DEBUG", message);
		String value = b.getString("nicestring");
		
		parcelMessage.setText(value);
	}
}

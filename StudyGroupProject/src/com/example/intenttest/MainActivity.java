package com.example.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.group.studyproject.R;

/**
 * Different kinds of Intents and different ways intents could be used
 * */

// http://developer.android.com/guide/topics/manifest/activity-element.html#multi
// http://developer.android.com/reference/android/content/Intent.html

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		long threadid = android.os.Process.myTid();// Thread.currentThread().getId();
		Log.d("DEBUG", "MainActivity, Thread Id: " + threadid);
	}

	public void buttonclick(View v) {
		Intent i = new Intent(MainActivity.this, PageOne.class);
		startActivity(i);

	}

	public void gotolistpage(View v) {
		int REQUEST_CODE = 1;
		Intent i = new Intent(this, ListPage.class);
		startActivityForResult(i, REQUEST_CODE);
	}

	/**
	 * Explicit intent, very filthy :P
	 * */
	public void sendparcelclick(View v) {
		Intent i = new Intent(this, MyParcelActivity.class);
		ObjectA objA = new ObjectA();
		objA.setIntValue(299);
		objA.setStrValue("Nitin says android rocks!!!");
		i.putExtra("com.package.ObjectA", objA);
		i.putExtra("nicestring", "Hello to me");

		// or if we want to be boring just send a string
		// i.putExtra("com.package.ObjectA", "My message");

		startActivity(i);
	}

	/**
	 * Starting a receiver
	 * */
	public void sendbroadcastclick(View v) {
		Intent i = new Intent("com.myaction.contactme");
		sendBroadcast(i);
	}

	/**
	 * Starting a service
	 * */
	public void startserviceclick(View v) {
		Intent i = new Intent(this, MyService.class);
		startService(i);
	}

	/**
	 * Implicit Intent
	 * */
	public void implicitclick(View v) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		startActivity(i);
	}

	/**
	 * NOTE: The onActivityResult was being called prematurely. 
	 * 
	 * 
	 * There's a bug / feature (?) in Android, which immediately reports result
	 * (which has not been set yet) for activity, declared as singleTask
	 * (despite the fact that the activity continues to run). If we change
	 * (singleTask/singleInstance) to singleTop, everything works as expected - result is
	 * reported only after the activity is finished. While this behavior has
	 * certain explanation (only one singleTask activity can exist and there can
	 * happen multiple waiters for it), this is still a not logical restriction
	 * for me.
	 * 
	 * Even better you could just remove all the custom android:launchMode settings 
	 * from the Android Manifest. But not advisable in a larger project with
	 * Activities for different needs. Please read the above documentation carefully 
	 * before using any settings for launchMode. Might have adverse effects.
	 * 
	 * **/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("DEBUG", "onActivityResult: requestCode" + requestCode
				+ ", resultCode: " + resultCode);
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1:
			if (data != null && data.getExtras().containsKey("selectedItem11")) {
				String returnedData = data.getExtras().getString(
						"selectedItem11");
				Log.d("DEBUG", "ReturnedValue: " + returnedData);

				TextView listResult = (TextView) findViewById(R.id.listResult);
				listResult.setText(returnedData);

			} else {
				Log.d("DEBUG", "Intent: Is null");
			}
			break;

		default:
			break;
		}
	}


}

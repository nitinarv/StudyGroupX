package com.sgxp.asgmt1.assignment;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.sgxp.asgmt1.asynctasks.RssXmlParseTask;

public class MainIntentService  extends IntentService{

	static String TAG = "MainIntentService";
	
	public MainIntentService() {
		super(TAG);
	
	}


	@Override
	protected void onHandleIntent(Intent intent) {
		/**
		 * do dataLoading in BG
		 * */
		if(isOnline())
			new RssXmlParseTask(getContentResolver(), this).execute();
		else
			Toast.makeText(this, "No Internet Available. Try Again Later.", Toast.LENGTH_SHORT).show();
		
	}
	
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
}

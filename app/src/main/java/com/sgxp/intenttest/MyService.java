package com.sgxp.intenttest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {

		super.onStart(intent, startId);

		long threadid = android.os.Process.myTid();//Thread.currentThread().getId();

		Log.d("DEBUG", "Service here! Someone just started me! YAI!!!!");
		Log.d("DEBUG", "Service, Thread Id: "+threadid);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

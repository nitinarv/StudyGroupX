package com.example.intenttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		long threadid = android.os.Process.myTid();//Thread.currentThread().getId();
		
		Log.d("DEBUG","Receiver here! I got a message from someone!!!");
//		Log.d("DEBUG","Receiver, Thread Id: "+threadid);
	}

}

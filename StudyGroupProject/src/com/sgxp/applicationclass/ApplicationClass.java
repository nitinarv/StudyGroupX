package com.sgxp.applicationclass;

import android.app.Application;
import android.content.Intent;

import com.sgxp.asgmt1.assignment.MainIntentService;
import com.sgxp.asgmt1.sqlite.DatabaseHelper;

public class ApplicationClass extends Application{

	String TAG = "ApplicationClass";
	
	static ApplicationClass instance;
	static DatabaseHelper dbInstance;
	
	@Override
	public void onCreate() {
		ApplicationClass.instance = this;

		
		/**
		 * Assignment1
		 * */
		Intent i = new Intent(this, MainIntentService.class); 
		startService(i);
		
		super.onCreate();
	}
	
	static public ApplicationClass getInstance() {
		return instance;
	}
	
	static public DatabaseHelper getDbInstance() {
		return dbInstance;
	}
	
	static public void setDbInstance(DatabaseHelper dbInstance) {
		ApplicationClass.dbInstance=dbInstance;
	}

	
}

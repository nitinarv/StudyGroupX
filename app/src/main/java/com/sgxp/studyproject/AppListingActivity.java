package com.sgxp.studyproject;

import com.group.studyproject.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AppListingActivity extends ListActivity {

	String[] ActivityNames;
	String[] ActivityClassPaths;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_listing);
		
		ActivityNames = getResources().getStringArray(R.array.ActivityName);
		ActivityClassPaths = getResources().getStringArray(R.array.ActivityPackage);
		
		setListAdapter(new ArrayAdapter<String>(AppListingActivity.this,
				android.R.layout.simple_list_item_1, ActivityNames));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		try {
			Class<?> className = Class.forName(ActivityClassPaths[position]);
			Intent gotoPage = new Intent(AppListingActivity.this,className);
			startActivity(gotoPage);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Log.e("AppListingActivity","ClassNotFoundException,\n ",e);
		}
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.app_listing, menu);
		return true;
	}

}

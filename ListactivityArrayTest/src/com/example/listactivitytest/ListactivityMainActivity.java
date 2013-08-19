package com.example.listactivitytest;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListactivityMainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listactivity_main);
		String[] tickerSymbols = new String[] { "MSFT", "ORCL", "AMZN", "ERTS" };
		setListAdapter(new ArrayAdapter<String>(this, R.layout.row_activity,//android.R.layout.simple_list_item_1, 
				tickerSymbols));
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.d("DEBUG","List Item Select Position: "+position);
		
		super.onListItemClick(l, v, position, id);
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listactivity_main, menu);
		return true;
	}

}

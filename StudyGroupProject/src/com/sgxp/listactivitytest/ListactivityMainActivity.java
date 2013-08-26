package com.sgxp.listactivitytest;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group.studyproject.R;

public class ListactivityMainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listactivity_main_array);
		String[] tickerSymbols = new String[] { "MSFT", "ORCL", "AMZN", "ERTS" };
		setListAdapter(new ArrayAdapter<String>(this, R.layout.row_activity_array,//android.R.layout.simple_list_item_1, 
				tickerSymbols));
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.d("DEBUG","List Item Select Position: "+position);
		
		super.onListItemClick(l, v, position, id);
	}
	
	
	

}

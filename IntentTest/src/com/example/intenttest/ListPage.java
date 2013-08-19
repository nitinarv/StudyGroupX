package com.example.intenttest;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListPage extends ListActivity{
	
	String[] tickerSymbols = new String[] { "MSFT", "ORCL", "AMZN", "ERTS" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_page);
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
				tickerSymbols));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	
		super.onListItemClick(l, v, position, id);
		Log.d("DEBUG","List Item Select Position: "+position);
		
		/**
		 * getting the intent sent to use and sending it back with data
		 * */
		Intent i = getIntent();
		setResult(RESULT_OK, i);
		String valpos = tickerSymbols[position];
		i.putExtra("selectedItem11",valpos);
		
		finish();
	}
	
}

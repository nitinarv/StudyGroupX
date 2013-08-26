package com.example.listactivitycursortest;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.group.studyproject.Center;
import com.group.studyproject.R;

public class ListactivityMainActivity extends Activity {

	Cursor cursor ;
	ListView lv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listactivity_main);
		lv1 = (ListView) findViewById(R.id.list_view);
		DbHelper dbh = ((Center) getApplication()).getDbHelper(); //hmm???
		
		cursor = dbh.rawQuery("select * from employee", null);
		
		lv1.setAdapter(new SimpleCursorAdapter(this, R.layout.row_activity, cursor,
				new String[] { "lastName" }, new int[] {
				R.id.text1rowitem}));
		
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("DEBUG", "on Item position Click: " + position);
				
				cursor.moveToPosition(position);
				Log.d("DEBUG","Values: "+cursor.getString(3));
				
				/**
				 * At this point it is our responsibility to collate the values,
				 * and then maintiain the collation of the data, Best solution
				 * is to maintain a hash map to , Key = position, Value = value,
				 * Refer Hash Map, Tree Map, Dictionary, Collections
				 * 
				 * For eg.
				 * */
				/*
				if(!hMap.containsKey(Integer.toString(position))){
					hMap.put(Integer.toString(position), tickerSymbols[position]);
				}else{
					hMap.remove(Integer.toString(position));
				}
				
				String MapValues = hMap.toString();
				Log.d("DEBUG","Values: "+MapValues);
				*/
			}
		});

		
	}

	
	@Override
	protected void onPause() {
		cursor.close();
		super.onPause();
	}
	

}

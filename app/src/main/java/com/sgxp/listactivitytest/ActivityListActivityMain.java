package com.sgxp.listactivitytest;

import java.util.HashMap;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group.studyproject.R;

/**
 * Sample code for lists, using an array as your data source, doing single item
 * select, using multiple list code, so that we can show the distinction between
 * the two,
 * */

public class ActivityListActivityMain extends Activity {

	ListView lv1;
	HashMap<String, String> hMap;

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_main_array);
		Log.d("DEBUG", "onCreate Main Call");
		lv1 = (ListView) findViewById(R.id.listsamplecode);
		hMap = new HashMap<String, String>();
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);// CHOICE_MODE_SINGLE);

		final String[] tickerSymbols = getResources().getStringArray(R.array.string_array);
				
				//new String[] { "DDMMSD", "LLORK","CKRUSR", "DIOSWQ" };

		

		lv1.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice,// android.R.layout.simple_list_item_1,
				// R.layout.row_activity,//
				// android.R.layout.simple_list_item_1,
				tickerSymbols));
		Log.d("DEBUG", "onCreate code call, ");

		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("DEBUG", "on Item position Click: " + position);
				/**
				 * At this point it is our responsibility to collate the values,
				 * and then maintiain the collation of the data, Best solution
				 * is to maintain a hash map to , Key = position, Value = value,
				 * Refer Hash Map, Tree Map, Dictionary, Collections
				 * 
				 * For eg.
				 * */

				if (!hMap.containsKey(Integer.toString(position))) {
					hMap.put(Integer.toString(position),
							tickerSymbols[position]);
				} else {
					hMap.remove(Integer.toString(position));
				}

				String MapValues = hMap.toString();
				Log.d("DEBUG", "Values: " + MapValues);

			}
		});

	}

}

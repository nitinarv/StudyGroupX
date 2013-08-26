package com.sgxp.tabview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.group.studyproject.R;

public class TabViewMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_view_main);
		
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
//		spec1.setIndicator("Tab 1");   //Just Name
//		spec1.setIndicator("Tab 1",getResources().getDrawable(android.R.drawable.arrow_up_float));  //Name + Image
		//also allowed to set the spec as a view, 
		TextView tv1 = new TextView(this);
		tv1.setText("Tab 1");
		tv1.setBackgroundColor(Color.RED);
		spec1.setIndicator(tv1);  //Specific look to text

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
//		spec2.setIndicator("Tab 2");
		spec2.setIndicator("Tab 2",getResources().getDrawable(R.drawable.ic_launcher));//android.R.drawable.arrow_down_float));
		spec2.setContent(R.id.tab2);

		TabSpec spec3=tabHost.newTabSpec("Tab 3");
//		spec3.setIndicator("Tab 3");
		spec3.setIndicator("Tab 3",getResources().getDrawable(android.R.drawable.btn_plus));
		spec3.setContent(R.id.tab3);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
	}

	
}

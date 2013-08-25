package com.example.tabact;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.group.studyproject.R;

public class TabActivityMainActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_main);

        TabHost tabHost=getTabHost();
        // no need to call TabHost.Setup()        
        
        //First Tab
        TabSpec spec1=tabHost.newTabSpec("Tab 1");
        spec1.setIndicator("Tab 1",getResources().getDrawable(android.R.drawable.arrow_up_float));
        Intent in1=new Intent(this, Act1.class);
        spec1.setContent(in1);
        
        TabSpec spec2=tabHost.newTabSpec("Tab 2");
        spec2.setIndicator("Tab 2",getResources().getDrawable(android.R.drawable.arrow_down_float));
        Intent in2=new Intent(this,Act2.class);
        spec2.setContent(in2);
        
        //Dynamically Adding tabs
        TabSpec spec3=tabHost.newTabSpec("Tab 3");
        spec3.setIndicator("Tab 3",getResources().getDrawable(android.R.drawable.btn_plus));
        spec3.setContent(new TabContentFactory() {
        	   
        	   @Override
        	   public View createTabContent(String tag) {
        	    // TODO Auto-generated method stub
        		   TextView tv = new TextView(TabActivityMainActivity.this);
        		   tv.setText("HELLO WORK");
       			tv.setBackgroundColor(Color.GREEN);
        	    return (tv);//new AnalogClock(TabActivityMainActivity.this));
        	   }
        	  });

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
    }
}

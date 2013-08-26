package com.sgxp.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group.studyproject.R;

public class PageOne extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_one);
	}
	
	public void pagetwoclick(View v){
		Intent i = new Intent(this, PageTwo.class);
		startActivity(i);
	}
	
	public void mainpageclick(View v){
		finish();
	}
}

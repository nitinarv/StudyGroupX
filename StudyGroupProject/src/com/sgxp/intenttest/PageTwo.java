package com.sgxp.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group.studyproject.R;

public class PageTwo extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_two);
	}
	
	public void gotopagethree(View v){
		Intent i = new Intent(this, PageThree.class);
		startActivity(i);
	}
}

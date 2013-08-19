package com.example.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PageThree extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_three);
	}
	
	public void gotopageone(View v){
		Intent i = new Intent(this, PageOne.class);
		startActivity(i);
	}
}

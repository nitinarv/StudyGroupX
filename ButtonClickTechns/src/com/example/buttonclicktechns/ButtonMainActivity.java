package com.example.buttonclicktechns;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ButtonMainActivity extends Activity implements OnClickListener {

	// Normal Button handling
	Button button1N;
	Button button2N;
	Button button3N;

	// Using the interface to do it
	Button button7C;
	Button button8C;
	Button button9C;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button_main);

		// Normal Implementation
		button1N = (Button) findViewById(R.id.button1);
		button2N = (Button) findViewById(R.id.button2);
		button3N = (Button) findViewById(R.id.button3);

		button1N.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("DEBUG","Button1N");
				

			}
		});
		
		

		button2N.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("DEBUG", "Button2N");

			}
		});

		button3N.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("DEBUG", "Button3N");

			}
		});

		// Interface way to do this
		button7C = (Button) findViewById(R.id.button7);
		button8C = (Button) findViewById(R.id.button8);
		button9C = (Button) findViewById(R.id.button9);
		button7C.setOnClickListener(this);
		button8C.setOnClickListener(this);
		button9C.setOnClickListener(this);

	}

	// Direct Click
	public void fourclick(View v) {
		Log.d("DEBUG", "Button4B");
	}

	public void fiveclick(View v) {
		Log.d("DEBUG", "Button5B");
	}

	public void sixclick(View v) {
		Log.d("DEBUG", "Button6B");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.button_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button7:
			Log.d("DEBUG", "Button7C");
			break;
		case R.id.button8:
			Log.d("DEBUG", "Button8C");
			break;
		case R.id.button9:
			Log.d("DEBUG", "Button9C");
			break;
		}

	}
}

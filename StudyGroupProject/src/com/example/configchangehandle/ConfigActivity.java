package com.example.configchangehandle;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.group.studyproject.R;

public class ConfigActivity extends Activity {
	EditText edit;
	Button update;
	TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config_port);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		switch (newConfig.orientation) {
		case Configuration.ORIENTATION_LANDSCAPE:
			Log.d("DEBUG","ORIENTATION_LANDSCAPE");
			break;
		case Configuration.ORIENTATION_PORTRAIT:
			Log.d("DEBUG","ORIENTATION_PORTRAIT");
			break;
		case Configuration.ORIENTATION_UNDEFINED:
			Log.d("DEBUG","ORIENTATION_UNDEFINED");
			break;

		default:
			break;
		}
		switch (newConfig.hardKeyboardHidden) {
		case Configuration.HARDKEYBOARDHIDDEN_NO:
			Log.d("DEBUG","HARDKEYBOARDHIDDEN_NO");
			setContentView(R.layout.activity_config_port);
			break;
		case Configuration.HARDKEYBOARDHIDDEN_YES:
			setContentView(R.layout.activity_config_land);
			Log.d("DEBUG","HARDKEYBOARDHIDDEN_YES");
			break;
		case Configuration.HARDKEYBOARDHIDDEN_UNDEFINED:
			Log.d("DEBUG","HARDKEYBOARDHIDDEN_UNDEFINED");
			break;

		default:
			break;
		}
		super.onConfigurationChanged(newConfig);

	}

	
}

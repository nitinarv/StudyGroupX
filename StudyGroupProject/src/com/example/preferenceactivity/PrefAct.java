package com.example.preferenceactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.group.studyproject.R;

public class PrefAct extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);



	}
}

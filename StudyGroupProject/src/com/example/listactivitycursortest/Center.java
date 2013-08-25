package com.example.listactivitycursortest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Application;
import android.database.Cursor;

public class Center extends Application {

	DbHelper dbHelper = null;
	Cursor cursor = null;
	Cursor varCursor = null;

	@Override
	public void onCreate() {
		super.onCreate();
		getDbHelper();

	}

	/**
	 * Turn this to a singleton,
	 * */
	public DbHelper getDbHelper() {

		if (dbHelper == null) {
			dbHelper = new DbHelper(this);
		}
		if(!dbHelper.getDatabase().isOpen()){
			dbHelper = null;
			dbHelper = new DbHelper(this);
		}

		return dbHelper;
	}

	/**
	 * Remove this for , turning into a proper singleton
	 * */
	public void setDbHelper(DbHelper dbHelper) {

		this.dbHelper = dbHelper;
	}

	/**
	 * Do consider to turn this to a singleton as well considering the broader
	 * situation
	 * */
	public Cursor getCursor() {
		return cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	/**
	 * Do consider to turn this to a singleton as well considering the broader
	 * situation
	 * */
	public Cursor getVarCursor() {
		return varCursor;
	}

	public void setVarCursor(Cursor varCursor) {
		this.varCursor = varCursor;
	}
	
	/**
	 * Another way to boot strap a database,
	 * copy the damn thing from assets
	 * */
	public void extractAssetToDatabaseDirectory(String fileName)
			throws IOException {

		int length;
		InputStream sourceDatabase = this.getAssets().open(fileName);
		File destinationPath = this.getDatabasePath(fileName);
		OutputStream destination = new FileOutputStream(destinationPath);

		byte[] buffer = new byte[4096];
		while ((length = sourceDatabase.read(buffer)) > 0) {
			destination.write(buffer, 0, length);
		}
		sourceDatabase.close();
		destination.flush();
		destination.close();
	}

}

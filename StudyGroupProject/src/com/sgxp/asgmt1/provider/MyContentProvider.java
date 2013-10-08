package com.sgxp.asgmt1.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.sgxp.applicationclass.ApplicationClass;
import com.sgxp.asgmt1.sqlite.DatabaseHelper;


public class MyContentProvider extends ContentProvider {

	private static final String TAG = "MyContentProvider";
	private static final String AUTH="com.tv.provider.MyContentProvider";
	public static final Uri TOP_40_URI = Uri.parse("content://"+AUTH+"/"+ItunesRssDb.Top40Table.TABLE_NAME);
	final static int CHART_ITEM = 1;

	private SQLiteDatabase sqlDB;
	private DatabaseHelper dbHelper;
	
	private final static UriMatcher uriMatcher;
	
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTH, ItunesRssDb.Top40Table.TABLE_NAME, CHART_ITEM);
	}
	

	@Override
	public boolean onCreate() {
		Log.d("TESTING84","Content Provider");
		
		dbHelper = new DatabaseHelper(getContext());
		ApplicationClass.setDbInstance(dbHelper);
		return (dbHelper == null) ? false : true;
	}
	
	@Override
	public int delete(Uri uri, String s, String[] as) {
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues contentvalues) {
		// get database to insert records
		sqlDB = dbHelper.getWritableDatabase();
		
		if(uriMatcher.match(uri)==CHART_ITEM){
			// insert record in user table and get the row number of recently inserted record
			long rowId = sqlDB.insert(ItunesRssDb.Top40Table.TABLE_NAME, null, contentvalues);
			if (rowId > 0) {
				Uri rowUri = ContentUris.appendId(TOP_40_URI.buildUpon(), rowId).build();
				getContext().getContentResolver().notifyChange(rowUri, null);
				return rowUri;
			}
		}
		
		throw new SQLException("Failed to insert row into " + uri);
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		if(uriMatcher.match(uri)==CHART_ITEM){
			qb.setTables(ItunesRssDb.Top40Table.TABLE_NAME);
		}
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, null, null, null,sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues contentvalues, String s,
			String[] as) {
		return 0;
	}
}
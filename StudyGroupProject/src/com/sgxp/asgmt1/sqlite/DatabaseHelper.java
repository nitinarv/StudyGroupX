package com.sgxp.asgmt1.sqlite;

import com.sgxp.asgmt1.provider.ItunesRssDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = ItunesRssDb.DATABASE_NAME;

	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create table to store user names
		db.execSQL("Create table IF NOT EXISTS "
				+ ItunesRssDb.Top40Table.TABLE_NAME
				+ "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ItunesRssDb.Top40Table.ITUNE_ID+ " TEXT,"
				+ ItunesRssDb.Top40Table.ITUNE_MAIN_LINK+" TEXT,"
				+ ItunesRssDb.Top40Table.ENTRY_TITLE+" TEXT,"
				+ ItunesRssDb.Top40Table.ENTRY_NAME+" TEXT,"
				+ ItunesRssDb.Top40Table.ENTRY_LINK+" TEXT,"
				+ ItunesRssDb.Top40Table.ENTRY_GENRE+" TEXT,"
				+ ItunesRssDb.Top40Table.ENTRY_PREVIEW_LINK+" TEXT,"
				+ ItunesRssDb.Top40Table.ARTIST_BIO_LINK+" TEXT,"
				+ ItunesRssDb.Top40Table.ARTIST_NAME+" TEXT,"
				+ ItunesRssDb.Top40Table.ENTRY_PRICE+" TEXT,"
				+ ItunesRssDb.Top40Table.IMAGE_LINK_55+" TEXT,"
				+ ItunesRssDb.Top40Table.IMAGE_LINK_60+" TEXT,"
				+ ItunesRssDb.Top40Table.IMAGE_LINK_170+" TEXT,"
				+ ItunesRssDb.Top40Table.COPYRIGHT+" TEXT,"
				+ ItunesRssDb.Top40Table.RELEASEDATE+" TEXT"
				+");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + ItunesRssDb.DATABASE_NAME);
		onCreate(db);
	}
}


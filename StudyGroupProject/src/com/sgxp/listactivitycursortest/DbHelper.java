package com.sgxp.listactivitycursortest;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.group.studyproject.R;

public class DbHelper {

	private final String TAG = this.getClass().getSimpleName();
	private static final String DATABASE_NAME = "database";
	private static final int DATABASE_VERSION = 1;
	private OpenHelper mOpenHelper;
	private SQLiteDatabase db;
	Application application;

	// used for synchronization of all Database operations
	private Object mLock = new Object();

	public DbHelper(Application application) {
		this.application = application;
		mOpenHelper = new OpenHelper(application.getBaseContext());
		this.db = mOpenHelper.getWritableDatabase();
	}

	public void close() {
		if (db != null) {
			db.close();
			db = null;
		}
		mOpenHelper.close();
	}

	public long insert(String aTable, String aNullColumnHack,
			ContentValues aValues) {
		Log.d(TAG, "inside insert method tableName: " + aTable
				+ " dbNullness: " + db.equals(null) + "");
		long id = -1;
		synchronized (mLock) {
			if (db != null) {
				id = db.insert(aTable, aNullColumnHack, aValues);
			}
		}
		return id;
	}

	public Cursor query(String aTable, String[] aColumns, String aSelection,
			String[] aSelectionArgs, String aGroupBy, String aHaving,
			String aOrderBy) {
		Cursor cursor = null;
		synchronized (mLock) {
			cursor = db.query(aTable, aColumns, aSelection, aSelectionArgs,
					aGroupBy, aHaving, aOrderBy);
		}
		return cursor;
	}

	public Cursor query(String aTable, String[] aColumns, String aSelection,
			String[] aSelectionArgs, String aGroupBy, String aHaving,
			String aOrderBy, String aLimit) {
		Cursor cursor = null;
		synchronized (mLock) {
			cursor = db.query(aTable, aColumns, aSelection, aSelectionArgs,
					aGroupBy, aHaving, aOrderBy, aLimit);
		}
		return cursor;
	}

	public Cursor rawQuery(String aSql, String[] aSelectionArgs) {
		Cursor cursor = null;
		synchronized (mLock) {
			cursor = db.rawQuery(aSql, aSelectionArgs);
		}
		return cursor;
	}

	public int update(String aTable, ContentValues aValues,
			String aWhereClause, String[] aWhereArgs) {
		int rows = 0;
		synchronized (mLock) {
			rows = db.update(aTable, aValues, aWhereClause, aWhereArgs);
		}
		return rows;
	}

	public int delete(String aTable, String aWhereClause, String[] aWhereArgs) {
		int rows = 0;
		synchronized (mLock) {
			rows = db.delete(aTable, aWhereClause, aWhereArgs);
		}
		return rows;
	}

	public SQLiteDatabase getDatabase() {
		return db;
	}

	public static class OpenHelper extends SQLiteOpenHelper {
		Context context;
		
		public OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// Log.i("DbHelper", "onCreate(): Creating databases");
			Log.i("DbHelper", "onCreate(): Creating databases");

			executeSqlXml(db, R.raw.sql);
			executeSqlXml(db, R.raw.sql2);

			}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			try {
				dropTables(db);
			} catch (Exception e) {
				e.printStackTrace();
			}
			onCreate(db);
		}

		private void dropTables(SQLiteDatabase db) {
			db.execSQL("DROP TABLE IF EXISTS employees;");
			db.execSQL("DROP TABLE IF EXISTS variations;");
		}
		
		public void executeSqlXml(SQLiteDatabase dbase, int res) {
			String s;
			try {
				Toast.makeText(context, "1", 2000).show();
				InputStream in = context.getResources().openRawResource(res);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder();
				Document doc = builder.parse(in, null);
				NodeList statements = doc.getElementsByTagName("statement");
				for (int i = 0; i < statements.getLength(); i++) {
					s = statements.item(i).getChildNodes().item(0).getNodeValue();
					dbase.execSQL(s);
				}
			} catch (Throwable t) {
				Toast.makeText(context, t.toString(), 50000).show();
			}

		}


	}

}

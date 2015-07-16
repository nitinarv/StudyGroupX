package com.sgxp.asgmt1.asynctasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.sgxp.applicationclass.ApplicationClass;
import com.sgxp.asgmt1.provider.ItunesRssDb;
import com.sgxp.asgmt1.provider.MyContentProvider;
 

/**
 *  Will do the call to the over the
 *  internet to get the RSS in XML and then
 *  parse that information, and insert it as 
 *  and when it receives that information.
 * */ 
public class RssXmlParseTask extends AsyncTask<Void, Object, Void>{
	String TAG = "RssTask";
	int tableCount = 0;
	ContentResolver contentResolver;
	Context context;

	public RssXmlParseTask(ContentResolver contentResolver, Context context) {
		this.contentResolver = contentResolver;
		this.context = context;
	}


	/**
	 * onProgressUpdate has been modified for 
	 * inserting the data as and when we can
	 * */
	@Override
	protected void onProgressUpdate(Object... values) {
		if(values[0]!=null)
			contentResolver.insert(MyContentProvider.TOP_40_URI, (ContentValues)values[0]);
		if(values[1]!=null)
			Toast.makeText(context, (String)values[1], Toast.LENGTH_SHORT).show();

		super.onProgressUpdate(values);
	}

	ContentValues contentValues = null;

	@Override
	protected Void doInBackground(Void... params) {

		if(ApplicationClass.getDbInstance()!=null){
			Cursor cursor = ApplicationClass.getDbInstance().getReadableDatabase().rawQuery("select * from Top40Table", null);

			tableCount = cursor.getCount();

			cursor.close();
		}

		if(tableCount>0){
			publishProgress(null, "The Table is Loaded , Do not need to request again.");
			return null;  ///???? exit
		}

		publishProgress(null, "Starting request for RSS.");
		InputStream is = null;

		try {
			is = downloadUrl("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=40/xml");

			XmlPullParserFactory factory = null;
			XmlPullParser parser = null;

			try{

				factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				parser = factory.newPullParser();

				parser.setInput(is,null);

				int eventType = parser.getEventType();

				String tagName_start = new String();
				String tagName_end = new String();
				String attributeName = new String();
				String attributeNamespace = new String();
				String attributePrefix = new String();
				String attributeType = new String();
				String attributeValue = new String();

				int k=0;

				while(eventType != XmlPullParser.END_DOCUMENT){
					int attributeCount = parser.getAttributeCount();
					String currentTagName = parser.getName();

					switch (eventType) {
					case XmlPullParser.START_TAG:

						tagName_start = parser.getName();

						if(tagName_start.equalsIgnoreCase("entry")){
							if(contentValues==null)
								contentValues = new ContentValues();	
						}

						for(int i=0; i<=attributeCount-1; i++){
							attributeName = parser.getAttributeName(i);
							attributeNamespace = parser.getAttributeNamespace(i);
							attributePrefix = parser.getAttributePrefix(i);
							attributeType = parser.getAttributeType(i);
							attributeValue = parser.getAttributeValue(i);

							if(attributeValue.endsWith(".m4a")){
								if(!attributeValue.contains("\n\t"))
									if(contentValues!=null)
										contentValues.put(ItunesRssDb.Top40Table.ENTRY_PREVIEW_LINK, attributeValue);
							}


							if(tagName_start.equalsIgnoreCase("category")){
								if(attributeName.endsWith("term")){
									if(!attributeName.contains("\n\t"))
										if(contentValues!=null)
											contentValues.put(ItunesRssDb.Top40Table.ENTRY_GENRE, attributeValue);
								}
							}

							if(tagName_start.equalsIgnoreCase("releaseDate")){
								if(attributeName.endsWith("label")){
									if(!attributeName.contains("\n\t"))
										if(contentValues!=null)
											contentValues.put(ItunesRssDb.Top40Table.RELEASEDATE, attributeValue);
								}
							}
						}


						break;
					case XmlPullParser.TEXT:

						if(tagName_start.equalsIgnoreCase("title")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.ENTRY_TITLE, value);
							}

						}

						if(tagName_start.equalsIgnoreCase("name")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.ENTRY_NAME, value);
							}

						}

						if(tagName_start.equalsIgnoreCase("artist")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.ARTIST_NAME, value);
							}

						}
						if(tagName_start.equalsIgnoreCase("price")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.ENTRY_PRICE, value);
							}

						}
						if(tagName_start.equalsIgnoreCase("rights")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.COPYRIGHT, value);
							}

						}

						if(tagName_start.equalsIgnoreCase("id") 
								&& attributeName.equalsIgnoreCase("id") 
								&& attributePrefix.equalsIgnoreCase("im") 
								&& attributeType.equalsIgnoreCase("CDATA")){
							if(contentValues!=null){

								if(!attributeValue.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.ITUNE_ID, attributeValue);


								String value = parser.getText();
								if(!value.contains("\n\t"))
									if(value.contains("http"))
										contentValues.put(ItunesRssDb.Top40Table.ITUNE_MAIN_LINK, value);
							}
						}

						if(tagName_start.equalsIgnoreCase("image") && attributeValue.equalsIgnoreCase("55")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.IMAGE_LINK_55, value);
							}
						}
						if(tagName_start.equalsIgnoreCase("image") && attributeValue.equalsIgnoreCase("60")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.IMAGE_LINK_60, value);
							}
						}
						if(tagName_start.equalsIgnoreCase("image") && attributeValue.equalsIgnoreCase("170")){
							if(contentValues!=null){
								String value = parser.getText();
								if(!value.contains("\n\t"))
									contentValues.put(ItunesRssDb.Top40Table.IMAGE_LINK_170, value);
							}
						}

						break;	

					case XmlPullParser.END_TAG:

						tagName_end = parser.getName();
						if(tagName_end.equalsIgnoreCase("entry")){
							publishProgress(contentValues,null);
							contentValues = null;
						}


						break;



					default:
						break;
					}
					eventType = parser.next();
					k++;
				}


			}catch(XmlPullParserException xppe){
				Log.e(TAG,"XmlPullParserException: ",xppe);
				publishProgress(null,"XmlPullParserException Occured");
			} catch (IOException ioe) {
				Log.e(TAG,"IOException: ",ioe);
				publishProgress(null,"IOException Occured");
			}



			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} catch (IOException e) {
			publishProgress(null,"IOException Occured");
			Log.e(TAG,"IOException: ",e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					publishProgress(null,"IOException Occured");
					Log.e(TAG,"IOException: ",e);
				}
			}
		}    
		return null;
	}

	// Given a string representation of a URL, sets up a connection and gets
	// an input stream.
	private InputStream downloadUrl(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		// Starts the query
		conn.connect();
		InputStream stream = conn.getInputStream();
		return stream;
	}


}


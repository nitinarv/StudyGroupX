package com.sgxp.asgmt1.customadapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.group.studyproject.R;
import com.sgxp.asgmt1.lazylist.ImageLoader;
import com.sgxp.asgmt1.provider.ItunesRssDb;

/**
 *  I am using an Image Loader 
 *  in this program and hence 
 *  have to make my custom cursor adapter. 
 *  and handle the image loading separately.
 * */ 
public class CustomAdapter extends SimpleCursorAdapter {

	private int mSelectedPosition;
	public Cursor items;
	private Context context;
	private int layout;

	ImageView image;
	TextView title;
	TextView genre;
	TextView releasedate;
	TextView price;
	TextView album;
	TextView company;

	private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    
    
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		Cursor c = getCursor();

		final LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(layout, parent, false);


		String sTitle = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_TITLE));
		String sGenre = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_GENRE));
		String sReleasedate = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.RELEASEDATE));
		String sPrice = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_PRICE));
		String sAlbum = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_NAME));
		String sCompany = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.COPYRIGHT));

		title = (TextView) v.findViewById(R.id.title);
		setTextIfValid(title,sTitle);
		genre = (TextView) v.findViewById(R.id.genre);
		setTextIfValid(genre,sGenre);
		releasedate = (TextView) v.findViewById(R.id.releasedate);
		setTextIfValid(releasedate,sReleasedate);
		price = (TextView) v.findViewById(R.id.price);
		setTextIfValid(price,sPrice);
		album = (TextView) v.findViewById(R.id.album);
		setTextIfValid(album,sAlbum);
		company = (TextView) v.findViewById(R.id.company);
		setTextIfValid(company,sCompany);

		items = c;

		return v;
	}
	
	public void setTextIfValid(TextView tv, String sValue){
		if (tv != null) {
			tv.setText(sValue);
		}
	}




	public CustomAdapter(Context context, int layout, Cursor c, String[] from, int[] to,
			int flagRegisterContentObserver) {
		super(context, layout, c, from, to,flagRegisterContentObserver);
		this.context = context;
		this.layout = layout;
		imageLoader=new ImageLoader(context.getApplicationContext());
	}


	@Override
	public void bindView(View v, Context context, Cursor c) {

		String sImage = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.IMAGE_LINK_55));
		String sTitle = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_TITLE));
		String sGenre = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_GENRE));
		String sReleasedate = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.RELEASEDATE));
		String sPrice = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_PRICE));
		String sAlbum = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_NAME));
		String sCompany = c.getString(c.getColumnIndex(ItunesRssDb.Top40Table.COPYRIGHT));
		
		
		
		
		
		image = (ImageView) v.findViewById(R.id.image);
		title = (TextView) v.findViewById(R.id.title);
		setTextIfValid(title,sTitle);
		genre = (TextView) v.findViewById(R.id.genre);
		setTextIfValid(genre,sGenre);
		releasedate = (TextView) v.findViewById(R.id.releasedate);
		setTextIfValid(releasedate,sReleasedate);
		price = (TextView) v.findViewById(R.id.price);
		setTextIfValid(price,sPrice);
		album = (TextView) v.findViewById(R.id.album);
		setTextIfValid(album,sAlbum);
		company = (TextView) v.findViewById(R.id.company);
		setTextIfValid(company,sCompany);
		if(sImage!=null && sImage.length()>0)
			imageLoader.DisplayImage(sImage, image);

	}


	public void setSelectedPosition(int position) {
		mSelectedPosition = position;
		notifyDataSetChanged();

	}

}
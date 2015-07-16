package com.sgxp.asgmt1.provider;

import android.provider.BaseColumns;

public class ItunesRssDb {
	
	public static final String DATABASE_NAME = "ItunesRss.db";
//	public static final String AUTHORITY = "com.tv.provider.MyContentProvider";

	// BaseColumn contains _id.
	public static final class Top40Table implements BaseColumns {

		public static final String TABLE_NAME = "Top40Table";

		// Table column
		public static final String ITUNE_ID = "ITUNE_ID";
		public static final String ITUNE_MAIN_LINK = "ITUNE_MAIN_LINK";
		public static final String ENTRY_TITLE = "ENTRY_TITLE";
		public static final String ENTRY_NAME = "ENTRY_NAME";
		public static final String ENTRY_LINK = "ENTRY_LINK";
		public static final String ENTRY_GENRE = "ENTRY_GENRE";
		public static final String ENTRY_PREVIEW_LINK = "ENTRY_PREVIEW_LINK";
		public static final String ARTIST_BIO_LINK = "ARTIST_BIO_LINK";
		public static final String ARTIST_NAME = "ARTIST_NAME";
		public static final String ENTRY_PRICE = "ENTRY_PRICE";
		public static final String IMAGE_LINK_55 = "IMAGE_LINK_55";
		public static final String IMAGE_LINK_60 = "IMAGE_LINK_60";
		public static final String IMAGE_LINK_170 = "IMAGE_LINK_170";
		public static final String COPYRIGHT = "COPYRIGHT";
		public static final String RELEASEDATE = "RELEASEDATE";
		
	}
}
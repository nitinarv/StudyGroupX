package com.sgxp.asgmt1.assignment;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.group.studyproject.R;
import com.sgxp.asgmt1.customadapter.CustomAdapter;
import com.sgxp.asgmt1.provider.ItunesRssDb;
import com.sgxp.asgmt1.provider.MyContentProvider;
import com.sgxp.asgmt1.quickaction.ActionItem;
import com.sgxp.asgmt1.quickaction.QuickAction;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor>{

	private static int LOADER = 0x01;
	ListView listview;
	CustomAdapter sca;
	
	private static final int ID_DL_IMG = 1;
	private static final int ID_GOTO_WEB = 2;
	private static final int ID_DL_PRW = 3;

    private long enqueue;
    private DownloadManager dm;
    BroadcastReceiver receiver;
    
    String sImage_55;
    String sImage_60;
    String sImage_170;
    String sTitle;
    String sGenre;
    String sReleasedate;
    String sPrice;
    String sAlbum;
	String sCompany; 
	String sWebLink;
	String sPreview;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asgmt1_main_activity);
        
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    Query query = new Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c
                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c
                                .getInt(columnIndex)) {
 
                        	
                        	Toast.makeText(MainActivity.this, "Download Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };
 
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        
        
        listview = (ListView) findViewById(R.id.listview);

        /**
         * Custom quickAction menu code. 
         * */
        final QuickAction mQuickAction 	= new QuickAction(this);

        ActionItem addItem 		= new ActionItem(ID_DL_IMG, "Dload Img", getResources().getDrawable(R.drawable.asgmt1_ic_add));
        ActionItem acceptItem 	= new ActionItem(ID_GOTO_WEB, "Goto webPage", getResources().getDrawable(R.drawable.asgmt1_ic_accept));
        ActionItem uploadItem 	= new ActionItem(ID_DL_PRW, "Dload Preview", getResources().getDrawable(R.drawable.asgmt1_ic_up));

        mQuickAction.addActionItem(addItem);
        mQuickAction.addActionItem(acceptItem);
        mQuickAction.addActionItem(uploadItem);

        //setup the action item click listener
        mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
        	@Override
        	public void onItemClick(QuickAction quickAction, int pos, int actionId) {
        		ActionItem actionItem = quickAction.getActionItem(pos);

        		
        		switch (actionId) {
        		case ID_DL_IMG:{
        			Toast.makeText(getApplicationContext(), "Downloading Image ... " , Toast.LENGTH_SHORT).show();
        			dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        			Request request = new Request(Uri.parse(sImage_170));
        			enqueue = dm.enqueue(request);
        		}
        		break;

        		case ID_DL_PRW:{
        			Toast.makeText(getApplicationContext(), "Downloading Preview ... " , Toast.LENGTH_SHORT).show();
        			dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        			Toast.makeText(MainActivity.this, "Preview Link: "+sPreview, Toast.LENGTH_LONG).show();
        			Request request = new Request(Uri.parse(sPreview));
        			enqueue = dm.enqueue(request);
        		}
        		break;
        		case ID_GOTO_WEB:
        			Intent i = new Intent(MainActivity.this, MainWebActivity.class);
        			i.putExtra("address", sWebLink);
                    startActivity(i);
        			
        			
        			break;

        		default:
        			break;
        		}
        		
        	}
        });

        //setup on dismiss listener, set the icon back to normal
        mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener() {			
        	@Override
        	public void onDismiss() {

        		
        	}
        });


        String[] from = {
        };


        int[] to ={
        };

        sca = new CustomAdapter(getApplicationContext(), R.layout.asgmt1_list_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);


        listview.setAdapter(sca);

        listview.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		
        		if(sca.items!=null && !sca.items.isClosed())
        			if(sca.items.moveToPosition(position)){

        				sImage_55 = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.IMAGE_LINK_55));
        				sImage_60 = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.IMAGE_LINK_60));
        				sImage_170 = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.IMAGE_LINK_170));
        				sTitle = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_TITLE));
        				sGenre = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_GENRE));
        				sReleasedate = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.RELEASEDATE));
        				sPrice = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_PRICE));
        				sAlbum = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_NAME));
        				sCompany = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.COPYRIGHT));
        				sPreview = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.ENTRY_PREVIEW_LINK));
        				sWebLink = sca.items.getString(sca.items.getColumnIndex(ItunesRssDb.Top40Table.ITUNE_MAIN_LINK));

        				Toast.makeText(MainActivity.this, sTitle, Toast.LENGTH_SHORT).show();

        				mQuickAction.show(view);
        			}
        	}
		});
        
        getLoaderManager().restartLoader(LOADER, null, this);
        
    }

    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
 
        case R.id.action_settings:
        	if(sca!=null){
        		if(sca.imageLoader!=null)
        			sca.imageLoader.clearCache();
        		sca.notifyDataSetChanged();
        	}
        	break;
        case R.id.goto_download:
        	Intent i = new Intent();
            i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
            startActivity(i);
        	
            break;
        case R.id.try_rss_again:
        	Intent i2 = new Intent(this, MainIntentService.class);
    		startService(i2);
            break;
 
        }
 
        return true;
    }
    
    
    @Override
    protected void onDestroy() {
    	listview.setAdapter(null);
    	unregisterReceiver(receiver);
    	super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.asgmt1_main, menu);
        
        return true;
    }

    /**
     * Loader Manager code
     * */

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = {
				ItunesRssDb.Top40Table._ID,
				ItunesRssDb.Top40Table.ITUNE_ID,
				ItunesRssDb.Top40Table.ITUNE_MAIN_LINK,
				ItunesRssDb.Top40Table.ENTRY_TITLE,
				ItunesRssDb.Top40Table.ENTRY_NAME,
				ItunesRssDb.Top40Table.ENTRY_LINK,
				ItunesRssDb.Top40Table.ENTRY_GENRE,
				ItunesRssDb.Top40Table.ENTRY_PREVIEW_LINK,
				ItunesRssDb.Top40Table.ARTIST_BIO_LINK,
				ItunesRssDb.Top40Table.ARTIST_NAME,
				ItunesRssDb.Top40Table.ENTRY_PRICE,
				ItunesRssDb.Top40Table.IMAGE_LINK_55,
				ItunesRssDb.Top40Table.IMAGE_LINK_60,
				ItunesRssDb.Top40Table.IMAGE_LINK_170,
				ItunesRssDb.Top40Table.COPYRIGHT,
				ItunesRssDb.Top40Table.RELEASEDATE
	    		};
		
		CursorLoader cursorLoader = new CursorLoader(this,
				MyContentProvider.TOP_40_URI, projection, null, null, null);
		return cursorLoader;
	}



	@Override
	public void onLoadFinished(android.content.Loader<Cursor> arg0, Cursor cursor) {
		sca.swapCursor(cursor);
		
	}



	@Override
	public void onLoaderReset(android.content.Loader<Cursor> arg0) {
		sca.swapCursor(null);
		
	}
    
}

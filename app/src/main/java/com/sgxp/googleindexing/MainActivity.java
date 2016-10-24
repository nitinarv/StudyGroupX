package com.sgxp.googleindexing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.group.studyproject.R;

/**
 * Created by narvind on 24/10/16.
 */

public class MainActivity extends Activity {

    private String myWebsite = "http://123hindijokes.com/";//"http://examplepetstore.com/dogs/standard-poodle";
    private String myTitle = "Hindi jokes";//"Standard Poodle";
    private String myDescription = "The best site for hindi jokes";//"The Standard Poodle stands at least 18 inches at the withers";

    private GoogleApiClient mClient;
    private Uri mUrl;
    private String mTitle;
    private String mDescription;

    private static final String TAG = "TESTING92303209";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_indexing_main);

        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mUrl = Uri.parse(myWebsite);
        mTitle = myTitle;
        mDescription = myDescription;

        readIntent(getIntent());
    }


    /**
     * This intent will read the incomming intent and then translate its url
     * */
    private void readIntent(Intent intent) {
        String action = intent.getAction();
        String data = intent.getDataString();
        log("readIntent> action: "+action+" \n\tdata: "+data);

        if(Intent.ACTION_VIEW.equals(action) && data!=null){
            log("readIntent> ");
        }else{

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        log("onStart> Starting");

        mClient.connect();
        PendingResult<Status> result = AppIndex.AppIndexApi.start(mClient, getAction_1());

        result.setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    log("onStart> AppIndexApi Start Success");
                }else{
                    log("onStart> AppIndexApi Start Failed");
                }
            }
        });
    }

    @Override
    public void onStop() {
        log("onStop> Starting");
        PendingResult<Status> result = AppIndex.AppIndexApi.end(mClient, getAction_1());

        result.setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    log("onStop> AppIndexApi Start Success");
                }else{
                    log("onStop> AppIndexApi Start Failed");
                }
            }
        });
        mClient.disconnect();
        super.onStop();
    }


    public Action getAction_1() {
        Thing object = new Thing.Builder()
                .setName(mTitle)
                .setDescription(mDescription)
                .setUrl(mUrl)
                .build();

        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    public Action getAction_2(){
        /**
         * Receipe code had the following lines
         * */
//        final String TITLE = recipe.getTitle();
//        final Uri APP_URI = BASE_URL.buildUpon().appendPath(recipe.getId()).build();

        return Action.newAction(Action.TYPE_VIEW, mTitle, mUrl);

    }


    public void log(String message){
        android.util.Log.d(TAG,message);
    }
}

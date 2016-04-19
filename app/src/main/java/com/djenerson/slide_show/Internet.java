package com.djenerson.slide_show;

// Adapted from http://developer.android.com/training/basics/network-ops/connecting.html#AsyncTask
//Supplied by Boston University
//modified by Daniel Jenerson

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Internet extends AppCompatActivity {


    // To create a task separate from the main UI thread
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // Pre: urls[0] is the parameter of execute() (i.e., when invoked by an AsycTask object)
            // Post = Postconditions for downloadUrl(urls[0])
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String aResult) {
            // Post: aResult is toasted
            toast(aResult);
        }
    }

    // ***********************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Post: UI appears, also long Toast in top left that app started
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet2);

        Toast toast = Toast.makeText(getApplicationContext(), "APP BEGUN", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();
    }

    // ***********************************************************************
    private String downloadUrl(String aUrl) throws IOException {
        // Post1: Return = first NUM_CHARS_DISPLAYED characters of page at aUrl, if possible
        // Post2: No opened InputStreams remain

        InputStream inStream = null;
        final int NUM_CHARS_DISPLAYED = 500;

        try {
            URL url = new URL(aUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            inStream = conn.getInputStream();
            String contentAsString = readIt(inStream, NUM_CHARS_DISPLAYED);
            return contentAsString;


        // Close InputStream after the app completed use
    } finally {
        if (inStream != null) {
            inStream.close();
        }
    }
    }


// ***********************************************************************
public void onClickHandler(View view) {
    // Post1: networkInfo is current NetworkInfo value
    // Post2: No Internet connection or = postconditions of DownloadWebPageTask.execute()

    ConnectivityManager connMgr = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

    final String MSN_URL = "http://www.msn.com";
    if (networkInfo != null && networkInfo.isConnected()) {
        toast("Website access begun ...");
        new DownloadWebPageTask().execute(MSN_URL);
    } else {
        toast("No network connection available.");
    }
}

// ***********************************************************************
public String readIt(InputStream anInStream, int aLen)
        throws IOException, UnsupportedEncodingException {
    // Post: return = first aLen characters of anInStream
    Reader reader = null;
    reader = new InputStreamReader(anInStream, "UTF-8");
    char[] buffer = new char[aLen];
    reader.read(buffer);
    return new String(buffer);
}

// ***********************************************************************
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main_htt, menu);
    return true;
}

// ***********************************************************************
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
        return true;
    }
    return super.onOptionsItemSelected(item);
}

// ***********************************************************************
private void toast(String aToast){
    Toast.makeText(getApplicationContext(), aToast, Toast.LENGTH_LONG).show();
}
}

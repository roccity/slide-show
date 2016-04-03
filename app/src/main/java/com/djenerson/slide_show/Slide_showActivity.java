package com.djenerson.slide_show;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Slide_showActivity extends Activity {
    private static final String TAG = "StateChange";
    public int currentimageindex=0;
    //    Timer timer;
//    TimerTask task;
    ImageView slidingimage;

    private int[] IMAGE_IDS = {
            R.drawable.photo1, R.drawable.photo2,

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };
        int delay = 1000; // delay for 1 sec.

        int period = 15000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);



    }
    public void onClick(View v) {

        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateandSlideShow() {


        slidingimage = (ImageView)findViewById(R.id.ImageView3_Left);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);

        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        slidingimage.startAnimation(rotateimage);

        GradeDBHelper gradeDbHelper = new GradeDBHelper(getApplicationContext());
        toast("Lamborghini Rocks");

        // Data repository db is in write mode
        SQLiteDatabase db = gradeDbHelper.getWritableDatabase();
        toast("Fast");

        // Map of values created, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(GradeDBHelper.FIELD1_NAME, "Green Car" );
        toast("Faster!");

        values.put(GradeDBHelper.FIELD2_NAME, "Orange Car" );
        toast("Fastest!!");


        // values inserted in row; insertion = primary key value of the new row
        long insertion = db.insert(GradeDBHelper.GRADE_TABLE_NAME, null, values);


        // dBase is access for reading
        SQLiteDatabase dBase = gradeDbHelper.getReadableDatabase();
        toast("Photo Database accessed");

        // projection specifies columns from the database
        String[] projection = {"grade1", "grade2"};

        // Query c performed with projection
        Cursor c = dBase.query(
                GradeDBHelper.GRADE_TABLE_NAME,     // table to query
                projection,                         // columns to return
                null,                               // columns for WHERE clause
                null,                               // values for WHERE clause
                null,                               // don't group rows
                null,                               // don't filter by row groups
                null                                // sort order
        );
        toast("Query made");

        // firstGrade is grade1 in first record
        c.moveToFirst();
        int firstGrade = c.getInt(c.getColumnIndexOrThrow(GradeDBHelper.FIELD1_NAME));
        toast("First Photo = " + "Green Lamborghini");

        int secondgradeGrade = c.getInt(c.getColumnIndexOrThrow(GradeDBHelper.FIELD2_NAME));
        toast("Second Photo = " + "Orange Heat");

        dBase.close();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(String aToast){
        Toast.makeText(getApplicationContext(), aToast, Toast.LENGTH_SHORT).show();
    }






    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");

    }

    @Override

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }
}



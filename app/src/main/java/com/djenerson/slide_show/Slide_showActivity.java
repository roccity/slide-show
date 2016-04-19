package com.djenerson.slide_show;

// Activity 1 launch code courtesy of Professor Eric Braude
//Activity 2 database code courtesy Smyth, Neil (2015-12-06). Android Studio Development Essentials:
// Android 6 Edition (p.466) applied to Activity AutoDatabase by Daniel Jenerson


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
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;







public class Slide_showActivity extends Activity {
//Log class, which requires that we import android.util.Log and declare a tag
// that will enable us to filter these messages in the log output:
    private static final String TAG = "StateChange";

//   initialize index
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
        // switchButton (button4 in activity_AutoDatabase) starts AutoDatabaseActivity
        Button switchButton = (Button) findViewById(R.id.button4);
        Button internetButton = (Button) findViewById(R.id.button5);

        final Intent AutoDatabase =
                new Intent(this, AutoDatabase.class);
        final Intent Internet =
                new Intent(this, Internet.class);

        switchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(AutoDatabase);
            }
        }
        );

        internetButton.setOnClickListener(new View.OnClickListener()
                                        {
                                            public void onClick(View v) {
                                                startActivity(Internet);
                                            }
                                        }
        );



        // Create runnable for posting
       ; final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };

        // set time interval between slides

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
        // apply on click method to exit button

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

// lifecycle methods which act as event handlers when the state of an Activity changes.

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


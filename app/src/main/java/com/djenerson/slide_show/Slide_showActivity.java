package com.djenerson.slide_show;

/*
Activity 1 launch code courtesy of Professor Eric Braude
Activity 2 database code courtesy Smyth, Neil (2015-12-06). Android Studio Development Essentials:
Android 6 Edition (p.466) applied to Activity AutoDatabase by Daniel Jenerson
*/


import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
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

    private static final String TAG = "StateChange";
    private static final int MY_NOTIFICATION_ID = 1234;
    public final int DELAY = 10;

    public int currentimageindex = 0;
    ImageView slidingimage;

    private int[] IMAGE_IDS = {
            R.drawable.photo1, R.drawable.photo2,
    };

    private void AnimateandSlideShow() {

        slidingimage = (ImageView) findViewById(R.id.ImageView3_Left);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex % IMAGE_IDS.length]);
        currentimageindex++;
        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        slidingimage.startAnimation(rotateimage);
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);

        final Handler mHandler = new Handler();
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

        internetButton.setOnClickListener(new View.OnClickListener() {
                                              public void onClick(View v) {

                                                  startActivity(Internet);

                                              }
                                          }
        );

    }

    public void onClick(View v) {

        Notification theNotification = new Notification.Builder(this)
                .setContentTitle("Attention")
                .setContentText("Battery is Low")
                .setSmallIcon(R.drawable.ic_launcher)
                .build();

        final NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, theNotification);

        Intent alarmIntent = new Intent(Slide_showActivity.this, AlarmReceiver.class);
        PendingIntent pendingAlarmIntent =
                PendingIntent.getBroadcast(Slide_showActivity.this, 0, alarmIntent, 0);
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + DELAY * 1000, pendingAlarmIntent);

        finish();
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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


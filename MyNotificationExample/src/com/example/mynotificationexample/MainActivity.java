package com.example.mynotificationexample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final int MY_NOTIFICATION_ID = 1;
	private int mNotificationCount = 0;
	
	private final CharSequence tickerText = "This is really, really super long notification message";
	private final CharSequence contentTitle = "Notification";
	private final CharSequence contentText = "You've been notified";
	
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;
	
	private Uri soundURI = Uri.parse("android.resource://com.example.mynotificationexample/" + R.raw.alarm_rooster);
	private long [] mVibratePattern = { 0, 200, 200, 300};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mNotificationIntent = new Intent(getApplicationContext(), NotificationSubActivity.class);
		mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0, mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		
		Button button = (Button) findViewById(R.id.btnNotify);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
		
				Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext())
						.setTicker(tickerText)
						.setSmallIcon(R.drawable.ic_launcher)
						.setAutoCancel(true)
						.setContentTitle(contentTitle)
						.setContentText(contentText + " ( " + ++mNotificationCount + " ) ")
						.setContentIntent(mContentIntent).setSound(soundURI)
						.setVibrate(mVibratePattern);
			
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(MY_NOTIFICATION_ID, notificationBuilder.build());
			}
			
		});
		
	}


}


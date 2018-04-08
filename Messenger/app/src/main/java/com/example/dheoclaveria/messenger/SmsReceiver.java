package com.example.dheoclaveria.messenger;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsMessage;

import java.util.Date;

/**
 * Created by Bernard Lao on 8/5/2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID = 1;
    String body, sender = "";
    private int mNotificationCount = 1;
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;
    private Uri soundURI = Uri.parse("android.resource://com.example.nienie.myassignmnetmessaging/" + R.raw.popcorn);
    private long[] mVibratePattern = {0, 200, 200, 300};

    public  static boolean ifrecieved = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = null;
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                body = String.valueOf(messages[i].getMessageBody());
                sender = String.valueOf(messages[i].getOriginatingAddress());
                ifrecieved = true;
            }
            Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.add);
            NotificationCompat.BigTextStyle bigtext = new NotificationCompat.BigTextStyle();
            bigtext.bigText(body);
            bigtext.setBigContentTitle(sender);
            bigtext.setSummaryText("new message");

            NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.add)
                    .setContentTitle(sender)
                    .setContentText("You new message.. ")
                    .setLargeIcon(icon1)
                    .setContentIntent(mContentIntent).setSound(soundURI)
                    .setStyle(bigtext);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(String.valueOf((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE)),MY_NOTIFICATION_ID, notificationBuilder.build());

        }
    }
}


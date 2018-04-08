package com.example.dheoclaveria.prelim_exam;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Bernard Lao on 8/5/2017.
 */


public class SmsReceiver extends BroadcastReceiver{

    private static final int MY_NOTIFICATION_ID = 1;
    private int mNotificationCount = 0;

    private final CharSequence tickerText = "This is really, really super long notification message";
    private final CharSequence contentTitle = "Notification";
    private final CharSequence contentText = "You've been notified";

    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    private Uri soundURI = Uri.parse("android.resource://com.example.nienie.myassignmnetmessaging/" + R.raw.alarm_rooster);
    private long[] mVibratePattern = {0, 200, 200, 300};

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = null;
        String contents = "";
        if(bundle != null){
            Object[] pdus = (Object[])bundle.get("pdus");
            messages = new SmsMessage[pdus.length];
            for(int i = 0; i < messages.length;i++){
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                contents += "Message from : " + messages[i].getOriginatingAddress();
                contents += ":";
                contents += messages[i].getMessageBody().toString() + "\n";

                Notification.Builder notificationBuilder = new Notification.Builder(context)
                        .setTicker(tickerText)
                        .setSmallIcon(R.drawable.final1)
                        .setAutoCancel(true)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText + " ( " + messages[i].getOriginatingAddress() + " ) ")
                        .setContentIntent(mContentIntent).setSound(soundURI)
                        .setVibrate(mVibratePattern);
                NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(MY_NOTIFICATION_ID, notificationBuilder.build());
                mNotificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), notificationBuilder.build());



            }
            Toast.makeText(context,contents,Toast.LENGTH_LONG).show();
        }
//        Toast.makeText(context,contents + "asdsadsadsad",Toast.LENGTH_LONG).show();
//
//        Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.final1);
//        NotificationCompat.BigTextStyle bigtext = new NotificationCompat.BigTextStyle();
//        bigtext.bigText("try ko lang kung gagana talga kung hinid shet kana talga \n so yung nga no mga kapaa  \n ahhahahhaha sfdsjkahisdafhaekfjhsadkfjahseifusadhfkjsaefes \n" +
//                "  asdjfhskdjfhakfsjdfhaksdfjdsahfkadsjfhsadkfjahsdfkajdshfsadkfjhsadfkdsjfh \n hello po ate ");
//        bigtext.setBigContentTitle("Sample TITLE Notifivcation");
//        bigtext.setSummaryText("By the semnder daw to para ganun daw");
//
//
//        NotificationCompat.Builder mybuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.final1)
//                .setContentTitle("Big Notif Sample Title")
//                .setContentText("Test for  my big text notiffation!")
//                .setLargeIcon(icon1)
//                .setStyle(bigtext);
//
//        NotificationManager notifmanager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notifmanager.notify(0,mybuilder.build());
    }


}

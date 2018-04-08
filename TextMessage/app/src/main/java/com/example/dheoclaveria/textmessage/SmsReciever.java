package com.example.dheoclaveria.textmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReciever extends BroadcastReceiver {


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
            }
        }
        Toast.makeText(context,contents,Toast.LENGTH_LONG).show();
    }
}

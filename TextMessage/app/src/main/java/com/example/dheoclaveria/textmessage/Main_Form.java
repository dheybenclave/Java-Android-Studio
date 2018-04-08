package com.example.dheoclaveria.textmessage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Main_Form extends AppCompatActivity {

    Button btnsend;
    EditText txtcontacts, txtmessage;
    ListView lstmessages;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);


        lstmessages = (ListView) findViewById(R.id.lstmessages);
        btnsend = (Button) findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = txtcontacts.getText().toString();
                String message = txtmessage.getText().toString();

                if (number.length() != 0 && message.length() != 0) {

                    sendMessage(number, message);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Insert numberor message ", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtcontacts = (EditText) findViewById(R.id.txtcontacts);
        txtmessage = (EditText) findViewById(R.id.txtmessage);


        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listitem);
        lstmessages.setAdapter(adapter);

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"), null,null, null, " thread_id ASC ");
        while (cursor.moveToNext()) {
            adapter.add("From : " + cursor.getColumnName(0) + " : " + cursor.getString(0) + "\n" + cursor.getColumnName(1) + " : " + cursor.getString(1) +
                    "\n" + cursor.getColumnName(2) + " : " + cursor.getString(2) + "\n" + cursor.getColumnName(3) + " : " + cursor.getString(3) +
                    "\n" + cursor.getColumnName(4) + " : " + cursor.getString(4) + "\n" + cursor.getColumnName(5) + " : " + cursor.getString(5) +
                    "\n" + cursor.getColumnName(6) + " : " + cursor.getString(6) + "\n" + cursor.getColumnName(7) + " : " + cursor.getString(7) +
                    "\n" + cursor.getColumnName(8) + " : " + cursor.getString(8) + "\n" + cursor.getColumnName(9) + " : " + cursor.getString(9) +
                    "\n" + cursor.getColumnName(10) + " : " + cursor.getString(10) + "\n" + cursor.getColumnName(11) + " : " + cursor.getString(11) +
                    "\n" + cursor.getColumnName(12) + " : " + cursor.getString(12) + "\n" + cursor.getColumnName(13) + " : " + cursor.getString(13) +
                    "\n" + cursor.getColumnName(14) + " : " + cursor.getString(14) + "\n" + cursor.getColumnName(15) + " : " + cursor.getString(15) +
                    "\n" + cursor.getColumnName(12) + " : " + cursor.getString(12) + "\n" + cursor.getColumnName(13) + " : " + cursor.getString(13) +
                    "\n" + cursor.getColumnName(16) + " : " + cursor.getString(16) + "\n" + cursor.getColumnName(17) + " : " + cursor.getString(17) +
                    "\n" + cursor.getColumnName(18) + " : " + cursor.getString(18) + "\n" + cursor.getColumnName(19) + " : " + cursor.getString(19) +
                    "\n" + cursor.getColumnName(20) + " : " + cursor.getString(20) + "\n" + cursor.getColumnName(21) + " : " + cursor.getString(21)
            );
        }

    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if ( v == btnsend) {
                forsend();
            }
        }
    }

    public void forsend(){

        String number = txtcontacts.getText().toString();
        String message = txtmessage.getText().toString();

        if(number.toString().trim().length() != 0 && message.toString().trim().length() != 0 ){

            sendMessage(number, message);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please Insert numbero r message ",Toast.LENGTH_LONG).show();
        }
    }

    public void sendMessage(String number, String message){
        String SENT_MESSAGE = "SMS SENT";
        String DELIVERED_MESSAGE = "SMS DELIVERED";
        PendingIntent sentPI =  PendingIntent.getBroadcast(this,0,new Intent(SENT_MESSAGE),0);
        PendingIntent deliverdPI =  PendingIntent.getBroadcast(this,0,new Intent(DELIVERED_MESSAGE),0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String status = "";
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        status = "SMS Sent";break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        status = "Cannot send message"; break;
                }
            //    Toast.makeText(getApplicationContext(),"SENT MESSAGE",Toast.LENGTH_LONG).show();
            }

        }, new IntentFilter(SENT_MESSAGE));



        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(getApplicationContext(),"SMS Delivered",Toast.LENGTH_SHORT).show();break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getApplicationContext(),"SMS Cancelled",Toast.LENGTH_SHORT).show();break;
                }
                    Toast.makeText(getApplicationContext(),"DELIVERED MESSAGE",Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter(SENT_MESSAGE));

        SmsManager messager  =  SmsManager.getDefault();
        messager.sendTextMessage(number,null,message,sentPI,deliverdPI);
    }



}

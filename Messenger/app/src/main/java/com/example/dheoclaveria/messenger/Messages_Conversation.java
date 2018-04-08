package com.example.dheoclaveria.messenger;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dheoclaveria.messenger.Main_Form.passvaluekeyname;
import static com.example.dheoclaveria.messenger.Main_Form.passvaluekeynumber;
import static com.example.dheoclaveria.messenger.Main_Form.passvaluekeyphoto;
import static com.example.dheoclaveria.messenger.Main_Form.passvaluekeythread;
import static com.example.dheoclaveria.messenger.SmsReceiver.ifrecieved;

public class Messages_Conversation extends AppCompatActivity {

    TextView lblname1;
    Button btnback, btnsend, btncall;
    ListView lstconvo;
    EditText txtmessage;
    ImageView imgphoto;
    Boolean passifreeiceved = false;

    Custom_ListAdapter_Conversation adapter_conversation;
    List<Conversation_Data> conversation_datas = new ArrayList<Conversation_Data>();

    Intent intentdata;
    String passtrheadid, passname, passnumber;
    String status = "";
    private boolean running, isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages__conversation);

        passtrheadid = passvaluekeythread;
        passname = passvaluekeyname;
        passnumber = passvaluekeynumber;
        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new ButtonEvent());

        imgphoto = (ImageView) findViewById(R.id.imgphoto);
        imgphoto.setImageURI(Uri.parse(passvaluekeyphoto));

        lblname1 = (TextView) findViewById(R.id.lblname1);

        btncall = (Button) findViewById(R.id.btncall);
        btncall.setOnClickListener(new ButtonEvent());

        lstconvo = (ListView) findViewById(R.id.listviewconvo);
        adapter_conversation = new Custom_ListAdapter_Conversation(this, conversation_datas);
        lstconvo.setAdapter(adapter_conversation);

        txtmessage = (EditText) findViewById(R.id.txtmessage);

        btnsend = (Button) findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new ButtonEvent());

        lblname1.setText(passname);
        LoadConvo();
        runTimer();
    }

    public String formatnumnber(String number) {
        String formatnumber = "";
        int size = number.length();
        if (!number.contains("+63")) {
            String subnumber = number.substring(1, size);
            formatnumber = "+63" + subnumber;
            formatnumber.trim().replace(" ", "");
            number = formatnumber;
        }
        return number;
    }

    public void forcall() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            String subnmuber = passnumber;
            intent.setData(Uri.parse("tel:" + formatnumnber(subnmuber)));
            startActivity(intent);


        } catch (SecurityException ex) {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
    }

    public void forsend() {
        String number = passnumber;
        String message = txtmessage.getText().toString();

        if (number.toString().trim().length() != 0 && message.toString().trim().length() != 0) {
            sendMessage(number, message);
            txtmessage.setText("");

        } else {
            Toast.makeText(getApplicationContext(), "Please insert number or message", Toast.LENGTH_LONG).show();
        }

    }

    public String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri,
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return phoneNumber;
        }
        String contactName = phoneNumber;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        } else {
            contactName = phoneNumber;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return contactName;
    }

    private void sendMessage(String number, String message) {
        String SENT_MESSAGE = "SMS_SENT";
        String DELIVERED_MESSAGE = "SMS_DELIVERED";
        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT_MESSAGE), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(DELIVERED_MESSAGE), 0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        status = "SMS Sent";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        status = "Cannot send message";
                        break;
                }
                LoadConvo();
                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
            }

        }, new IntentFilter(SENT_MESSAGE));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        //  Toast.makeText(getApplicationContext(), "SMS Delivered", Toast.LENGTH_SHORT).show();
                        LoadConvo();
                        break;
                    case Activity.RESULT_CANCELED:
                        // Toast.makeText(getApplicationContext(), "SMS Cancelled", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        }, new IntentFilter(DELIVERED_MESSAGE));
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, sentPI, deliveredPI);
    }

    private void LoadConvo() {

        conversation_datas.clear();
        lstconvo.setAdapter(null);
        adapter_conversation = new Custom_ListAdapter_Conversation(this, conversation_datas);

        try {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, " thread_id = " + passtrheadid, null, " date ASC");

            if (cursor.moveToFirst()) {

                do {
                    String type = cursor.getString(9);
                    if (type.equals("2") || type.equals("1")) {
                        conversation_datas.add(new Conversation_Data(type, getContactName(getApplicationContext(), cursor.getString(2))
                                , cursor.getString(12), cursor.getString(4)));
                    }
                }
                while (cursor.moveToNext());
                lstconvo.setAdapter(adapter_conversation);
            }

        } catch (Exception ex) {
        }
    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnback == v) {
                setResult(1, intentdata);
                finish();
            } else if (btnsend == v) {
                forsend();
                LoadConvo();

            } else if (btncall == v) {
                forcall();
            }

        }

    }

    private void runTimer()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (ifrecieved == true) {
                    LoadConvo();
                    ifrecieved = false;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}

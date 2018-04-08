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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.dheoclaveria.messenger.Main_Form.allcontacts;
import static com.example.dheoclaveria.messenger.Main_Form.alltrhread;
import static com.example.dheoclaveria.messenger.SmsReceiver.ifrecieved;


public class AddMessage extends AppCompatActivity {
    public String passtrheadid = "";

    ListView lstconvo;
    Button btnback, btnsend, btnaddcontacts;
    MultiAutoCompleteTextView txtcontacts;
    EditText txtmessagecontent;

    Custom_ListAdapter_Conversation adapter_addmessages;
    List<Conversation_Data> addmessages_datas = new ArrayList<Conversation_Data>();
    List<String> formultitextview = new ArrayList<String>();

    List<String> passallcontacts = allcontacts;
    List<String> passalltrhread = alltrhread;


    String number = null;
    String photo = null;
    String contactnumber = null;
    String threadconvo = "";

    private Intent intentdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        LoadContacts();// for multitextview contacts (name:number )

        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new ButtonEvent());

        txtcontacts = (MultiAutoCompleteTextView) findViewById(R.id.txtcontacts);
        ArrayAdapter addpter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, formultitextview);
        txtcontacts.setAdapter(addpter);
        txtcontacts.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        txtcontacts.setOnItemClickListener(new Suggestitemcontact());

        btnaddcontacts = (Button) findViewById(R.id.btnaddcontacts);
        btnaddcontacts.setOnClickListener(new ButtonEvent());

        lstconvo = (ListView) findViewById(R.id.lstcustomviewconvo);
        adapter_addmessages = new Custom_ListAdapter_Conversation(this, addmessages_datas);
        lstconvo.setAdapter(adapter_addmessages);

        txtmessagecontent = (EditText) findViewById(R.id.txtmeessagecontent);
        btnsend = (Button) findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new ButtonEvent());

        runTimer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            if ((requestCode == 1)) {
                contactnumber = data.getStringExtra("contact1").toString();
                String name1 = data.getStringExtra("name1").toString();
                addmessages_datas.clear();
                adapter_addmessages = new Custom_ListAdapter_Conversation(this, addmessages_datas);
                txtcontacts.setText(name1);
                txtmessagecontent.setText("");

            }
        }
    }


    private void foraddcontacts() {
        Intent intent = new Intent(AddMessage.this, AddContacts.class);
        startActivityForResult(intent, 1);
        addmessages_datas.clear();
        adapter_addmessages = new Custom_ListAdapter_Conversation(this, addmessages_datas);
    }


    public void forsend() {

        String message = txtmessagecontent.getText().toString();

        if (txtcontacts.toString().trim().length() != 0 && message.toString().trim().length() != 0) {
            if (contactnumber != null) {number = contactnumber;}
            else {number = txtcontacts.getText().toString();}

            for (int i = 0; i < passallcontacts.size(); i++) {
                if (passallcontacts.get(i).contains(number)) {
                    threadconvo = alltrhread.get(i);
                }
            }
            sendMessage(number, message);
            txtmessagecontent.setText("");
        } else {Toast.makeText(getApplicationContext(), "Please insert number or message", Toast.LENGTH_LONG).show();}

    }


    private void sendMessage(String number, String message) {
        String SENT_MESSAGE = "SMS_SENT";
        String DELIVERED_MESSAGE = "SMS_DELIVERED";
        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT_MESSAGE), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(DELIVERED_MESSAGE), 0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String status = "";

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
                          Toast.makeText(getApplicationContext(), "SMS Delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                         Toast.makeText(getApplicationContext(), "SMS Cancelled", Toast.LENGTH_SHORT).show();
                        break;
                }
                LoadConvo();
            }
        }, new IntentFilter(DELIVERED_MESSAGE));
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, sentPI, deliveredPI);
    }

    public String formatnumnber(String number) {
        String formatnumber = "";
        int size = number.length();
        if (!number.contains("+63")) {
            String subnumber = number.substring(1, size);
            formatnumber = "+63" + subnumber;
            number = formatnumber;
        }
        return number.trim().replace(" ", "");
    }

    public String getContactName(Context context, String phoneNumber) {// get photo and name using specific number
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor == null) {
            photo = String.valueOf(Uri.parse("android.resource://com.example.dheoclaveria.messenger/" + R.drawable.insert));
            return null;
        }
        String contactName = null;

        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));

            String subphoto = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI));
            if (subphoto != null && subphoto != "") {
                photo = subphoto;
            } else if (subphoto == null && subphoto == "") {
                photo = String.valueOf(Uri.parse("android.resource://com.example.dheoclaveria.messenger/" + R.drawable.insert));

            } else {
                photo = String.valueOf(Uri.parse("android.resource://com.example.dheoclaveria.messenger/" + R.drawable.insert));
            }

        } else {
            contactName = phoneNumber;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        } else {
            contactName = phoneNumber;
        }
        return contactName;
    }

    public void LoadContacts() {// load all contacts for multitextview purposes 1 time load only

        formultitextview.clear();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, " display_name ASC");
        try {
            if (cursor.moveToFirst()) {
                do {

                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    formultitextview.add(name + ":" + number);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception ex) {
        }

        cursor.close();
    }


    private void LoadConvo() {

        addmessages_datas.clear();
        adapter_addmessages = new Custom_ListAdapter_Conversation(this, addmessages_datas);
        String pass = threadconvo;

        if (threadconvo != "" ) {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, " thread_id = " + pass, null, " date ASC");

            try {
                if (cursor.moveToFirst()) {

                    do {
                        String type = cursor.getString(9);
                        if (type.equals("2") || type.equals("1")) {
                            addmessages_datas.add(new Conversation_Data(type, getContactName(getApplicationContext(), cursor.getString(2))
                                    , cursor.getString(12), cursor.getString(4)));
                        }
                    }
                    while (cursor.moveToNext());
                    lstconvo.setAdapter(adapter_addmessages);
                }
            } catch (Exception ex) {
            }

        } else if (threadconvo == "") {
            int date1 = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
            addmessages_datas.add(new Conversation_Data("2", txtcontacts.getText().toString()
                    , txtmessagecontent.getText().toString(), String.valueOf(date1)));
            lstconvo.setAdapter(adapter_addmessages);
        }

    }


    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnaddcontacts == v) {
                foraddcontacts();

            } else if (btnback == v) {
                setResult(1, intentdata);
                finish();
            } else if (btnsend == v) {
                forsend();
            }
        }
    }


    private class ListViewItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    private class Suggestitemcontact implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String subtext = txtcontacts.getText().toString();
            txtcontacts.setText(String.valueOf(subtext.split(":")[0].trim()));
            contactnumber = String.valueOf(subtext.split(":")[1].replace(",",""));


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

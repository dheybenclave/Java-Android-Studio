package com.example.dheoclaveria.messenger;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.dheoclaveria.messenger.SmsReceiver.ifrecieved;

public class Main_Form extends AppCompatActivity {


    public static String passvaluekeythread, passvaluekeyname, passvaluekeynumber, passvaluekeyphoto = "";
    public static List<String> allcontacts = new ArrayList<String>();
    public static List<String> alltrhread = new ArrayList<String>();
    ListView lstinbox;
    Button btnadd;
    Custom_ListAdapter_Messages adapter_messages;
    List<Messages_Data> messages_datas = new ArrayList<Messages_Data>();
    String photo = null;
    private int second = 0;
    private boolean running, isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);


        btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new ButtonEvent());

        lstinbox = (ListView) findViewById(R.id.lstcustomview);
        adapter_messages = new Custom_ListAdapter_Messages(this, messages_datas);
        lstinbox.setAdapter(adapter_messages);
        lstinbox.setOnItemClickListener(new ListViewItemClick());

        LoadInbox();
        runTimer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            if (requestCode == 1) {
                //  Intent intemt = new Intent(Main_Form.this, AddContacts.class);
                // startActivity(intemt);
                LoadInbox();
            }
        }
    }

    public void foradd() {

        Intent intent = new Intent(Main_Form.this, AddMessage.class);
        startActivityForResult(intent, 1);
    }

    public String getContactName(Context context, String phoneNumber) {
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

    private void LoadInbox() {

        lstinbox.setAdapter(null);
        adapter_messages = new Custom_ListAdapter_Messages(this, messages_datas);
        messages_datas.clear();

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, " thread_id NOT NULL ) GROUP BY ( thread_id ", null, " _id DESC");

        if (cursor.moveToFirst()) {

            do {
                // id , threadid , name, number, body, date, photo
                messages_datas.add(new Messages_Data(cursor.getString(0), cursor.getString(1),
                        getContactName(getApplicationContext(), cursor.getString(cursor.getColumnIndexOrThrow("address"))),
                        cursor.getString(cursor.getColumnIndexOrThrow("address")), cursor.getString(12), photo));

                alltrhread.add(cursor.getString(1));
                allcontacts.add(cursor.getString(2));
            }
            while (cursor.moveToNext());
            lstinbox.setAdapter(adapter_messages);

        }
        cursor.close();
    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnadd == v) {
                foradd();
            }

        }
    }

    private class ListViewItemClick implements android.widget.AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            passvaluekeythread = messages_datas.get(i).getMessages_threadid();
            passvaluekeyname = messages_datas.get(i).getMessages_name();
            passvaluekeynumber = messages_datas.get(i).getMessage_number();
            passvaluekeyphoto = messages_datas.get(i).getMessages_photo();
            Intent intent1 = new Intent(Main_Form.this, Messages_Conversation.class);
            startActivityForResult(intent1, 1);

        }

    }
    private void runTimer()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (ifrecieved == true) {
                 LoadInbox();
                    ifrecieved = false;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}

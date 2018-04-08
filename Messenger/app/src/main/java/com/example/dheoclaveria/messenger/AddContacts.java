package com.example.dheoclaveria.messenger;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddContacts extends AppCompatActivity {

    ListView lstcontacts;
    Custom_ListAdapter_Messages adapter_contacts;
    List<Messages_Data> contacts_datas = new ArrayList<Messages_Data>();
    Intent intentdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        adapter_contacts = new Custom_ListAdapter_Messages(this, contacts_datas);

        lstcontacts = (ListView) findViewById(R.id.lstcustomviewaddcontacts);
        lstcontacts.setAdapter(adapter_contacts);
        lstcontacts.setOnItemClickListener(new ListViewItemClick());

        LoadContacts();
    }


    public void LoadContacts() {

        contacts_datas.clear();
        lstcontacts.setAdapter(null);
        adapter_contacts = new Custom_ListAdapter_Messages(this, contacts_datas);

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, " display_name ASC");
        if (cursor.getCount() <= 0) {
            Toast.makeText(getApplicationContext(), "no contacts", Toast.LENGTH_LONG).show();
        }

        try {
            if (cursor.moveToFirst()) {

                do {

                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contacts_datas.add(new Messages_Data(cursor.getString(0), cursor.getString(1), name, number, number,
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI))));
                }
                while (cursor.moveToNext());
                lstcontacts.setAdapter(adapter_contacts);

            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    private class ListViewItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            intentdata = getIntent();

            String number1 = contacts_datas.get(i).getMessages_body();
            String name1 = contacts_datas.get(i).getMessages_name();
            intentdata.putExtra("contact1", number1);
            intentdata.putExtra("name1", name1);

            setResult(1, intentdata);
            finish();

        }
    }
}

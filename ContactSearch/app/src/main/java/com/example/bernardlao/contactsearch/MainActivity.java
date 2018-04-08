package com.example.bernardlao.contactsearch;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button btnRead,btnCall;
    ListView lstContacts;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (Button)findViewById(R.id.btnRead);
        btnCall = (Button)findViewById(R.id.btnCall);
        lstContacts = (ListView)findViewById(R.id.lstContacts);

        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.listitem);
        lstContacts.setAdapter(adapter);

        btnRead.setOnClickListener(new MyButtonListener());
        btnCall.setOnClickListener(new MyButtonListener());
    }

    private class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v == btnRead){
                adapter.clear();
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,null,null," display_name ASC ");
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    adapter.add(name + " - " + number);
                }
                //cursor.close();
                //int x = 1;
            }else if (v == btnCall){
                try{
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:+639150578286"));
                    startActivity(intent);
                }catch (SecurityException e){

                }
            }
        }
    }
}

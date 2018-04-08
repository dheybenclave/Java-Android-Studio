package com.example.dheoclaveria.readandcall;

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
import android.widget.Toast;

public class Main_Form extends AppCompatActivity {

    Button btnread, btncall;
    ListView lstcontacts;

    ArrayAdapter<String> adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        btnread = (Button) findViewById(R.id.btnread);
        btnread.setOnClickListener(new ButtonEvent());

        btncall = (Button) findViewById(R.id.btncall);
        btncall.setOnClickListener(new ButtonEvent());

        lstcontacts = (ListView) findViewById(R.id.lstcontacts);

            adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
            lstcontacts.setAdapter(adapter);
    }


    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if ( v == btnread) {
                forread();
            } else if ( v == btncall) {

                forcall();
            }
        }
    }

    public void forread(){

        adapter.clear();

        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, " display_name ASC");

            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                adapter.add(name + " " + number);
            }

        }catch(Exception ex){
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();}
    }

    public void forcall(){
        try{

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+639495960064"));
            startActivity(intent);
        }catch(SecurityException ex){
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
        }



    }
}

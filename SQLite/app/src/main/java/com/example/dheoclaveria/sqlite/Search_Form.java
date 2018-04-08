package com.example.dheoclaveria.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Search_Form extends Activity {

    Button btnsearch;
    EditText txtsearch;

    Cursor cursor;
    DBTools dbTools;
    Intent intentdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__form);

        btnsearch = (Button) findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(new ButtonEvent());

        txtsearch = (EditText) findViewById(R.id.txtsearch);

    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnsearch == v) {
                forsearch();
            }
        }
    }

    public void forsearch(){

        try{

            if(txtsearch.getText().toString().trim().length() != 0 ){
              intentdata = getIntent();
                intentdata.putExtra("searchval",txtsearch.getText().toString().trim());
                setResult(1, intentdata);
                finish();
            }
            else{  Toast.makeText(getApplicationContext(),"Please input data for search.",Toast.LENGTH_LONG).show();}

        }catch (Exception ex){}

    }

}

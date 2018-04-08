package com.example.dheoclaveria.assignment_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView lblsummary;
    String  finalsummary;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

            Summary();
    }

    private  void Summary()
    {
        lblsummary = (TextView) findViewById(R.id.lblsummary);
        Intent intent = getIntent();
        finalsummary =intent.getExtras().getString("finalsummary");
        lblsummary.setText(finalsummary);
    }
}

package com.example.dheoclaveria.assignment_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class secondactivity extends AppCompatActivity {

    TextView lbloutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);

        lbloutput = (TextView) findViewById(R.id.lbloutput);

        lbloutput.setText(getIntent().getExtras().getString("Value"));

    }
}

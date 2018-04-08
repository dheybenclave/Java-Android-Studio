package com.example.ruiz.newactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Second_Activity extends AppCompatActivity {

    TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        txtView = (TextView) findViewById(R.id.txtView);
        Intent intent = getIntent();
        String message =intent.getExtras().getString("greet");

        txtView.setText(message);

    }
}

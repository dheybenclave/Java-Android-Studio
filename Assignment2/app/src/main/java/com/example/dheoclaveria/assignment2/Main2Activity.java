package com.example.dheoclaveria.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    String fullname;
    String gender1;
    String flavors;
    String sizes;
    String addons;
    String requests;
    TextView txtFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtFinal = (TextView) findViewById(R.id.txtFinal);
        fullname = getIntent().getStringExtra("fullname");
        gender1 = getIntent().getStringExtra("genders");
        flavors = getIntent().getStringExtra("flavors");
        sizes = getIntent().getStringExtra("sizes");
        addons = getIntent().getStringExtra("addons");
        requests = getIntent().getStringExtra("requests");
            txtFinal.setText(gender1 + " " + fullname + " ordered a " + sizes + " " + flavors + " Pizza " + addons + " " + requests);


    }
    public void passdatanew(View view)
    {
        Toast.makeText(Main2Activity.this, "Your order has been submitted.", Toast.LENGTH_LONG).show();
        txtFinal.setText("");
        Intent intentnew = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intentnew);
    }
}

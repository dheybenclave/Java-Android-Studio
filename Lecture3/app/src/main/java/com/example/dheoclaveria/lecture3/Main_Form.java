package com.example.dheoclaveria.lecture3;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main_Form extends AppCompatActivity {

    TextView lblview;
    EditText txtinput1;
    Button btngeo,btnreversegeo;
    Geocoder geocoder;
    List<Address> address = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        lblview = (TextView) findViewById(R.id.lblview);
        txtinput1 = (EditText) findViewById(R.id.txtinput1);


        btngeo = (Button) findViewById(R.id.btngeo);
        btngeo.setOnClickListener(new ButtonEvent());

        btnreversegeo = (Button) findViewById(R.id.btnreversegeo);
        btnreversegeo.setOnClickListener(new ButtonEvent());

        geocoder = new Geocoder(getApplicationContext());


    }

    private class ButtonEvent implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(btngeo == view){

                String[] val = txtinput1.getText().toString().trim().split(",");
                try {
                    address.clear();
                    address = geocoder.getFromLocation(Double.valueOf(val[0]),Double.valueOf(val[1]),5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(address.size() > 0 ) {
                    for (int i = 0; i < address.get(0).getMaxAddressLineIndex(); i++) {
                        lblview.append(address.get(0).getAddressLine(i) + " \n");
                    }
                }

            }
            else if (btnreversegeo == view){

                int  address1  = 5;
                Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
                String loc = txtinput1.getText().toString();

                try{
                   address = geo.getFromLocationName(loc,5);
                    if(address.size() > 0){
                        lblview.setText("Lat  :" + address.get(0).getLatitude() + " long  : " + address.get(0).getLongitude());
                    }
                }
                catch (Exception ex){}
            }
        }
    }
}

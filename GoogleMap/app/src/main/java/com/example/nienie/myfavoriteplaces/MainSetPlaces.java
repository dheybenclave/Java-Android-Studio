package com.example.nienie.myfavoriteplaces;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainSetPlaces extends AppCompatActivity {
    Spinner spType;
    RatingBar rbRate;
    Button btnSave;
    EditText etPlace, etSubName, etAddress;
    DBUtilities dbUtilities;
    Cursor cursor;
    int id = 0;
    private float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_set_places);
        spType = (Spinner) findViewById(R.id.spType);
        rbRate = (RatingBar) findViewById(R.id.rbRate);
        etPlace = (EditText) findViewById(R.id.etPlace);
        etSubName = (EditText) findViewById(R.id.etSubName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        btnSave = (Button) findViewById(R.id.btnSave);
        dbUtilities = new DBUtilities(getApplicationContext());
        List<String> list = new ArrayList<String>();
        list.add("Hospital");
        list.add("Restaurant");
        list.add("School");
        list.add("Office");
        list.add("Park");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        spType.setAdapter(dataAdapter);
        btnSave.setOnClickListener(new ButtonEvent());
        rbRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = rating;
                Toast.makeText(MainSetPlaces.this, "The rating is testing: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
         id = getIntent().getIntExtra("id",0);
        btnSave.setText(getIntent().getStringExtra("SU"));
        if(btnSave.getText().toString().equals("UPDATE"))
        {
            etPlace.setText(getIntent().getStringExtra("place"));
            etSubName.setText(getIntent().getStringExtra("subName"));
            spType.setSelection(list.indexOf(getIntent().getStringExtra("type")));
            etAddress.setText(getIntent().getStringExtra("address"));
            rbRate.setRating(Float.parseFloat(String.valueOf(getIntent().getDoubleExtra("rate",0))));
        }
      //  Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
        etPlace.setOnFocusChangeListener(new Focus());
        etSubName.setOnFocusChangeListener(new Focus());
        etAddress.setOnFocusChangeListener(new Focus());
    }

    private class Focus implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View view, boolean b) {
            if(view == etPlace)
                if(b)
                    etPlace.setHint("");
                else
                    etPlace.setHint("Place");
            if(view == etSubName)
                if(b)
                    etSubName.setHint("");
                else
                    etSubName.setHint("Sub Name");
            if(view == etAddress)
                if(b)
                    etAddress.setHint("");
                else
                    etAddress.setHint("Address");
        }
    }

    public class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view == btnSave) {
                if(btnSave.getText().toString().equals("SAVE")) {
                    if (etPlace.getText().toString().trim().length() != 0 &&
                            etSubName.getText().toString().trim().length() != 0 &&
                            etAddress.getText().toString().trim().length() != 0) {
                        SaveLocation();
                    } else {
                        Toast.makeText(MainSetPlaces.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    }
                }
                if(btnSave.getText().toString().equals("UPDATE")) {
                    if (etPlace.getText().toString().trim().length() != 0 &&
                            etSubName.getText().toString().trim().length() != 0 &&
                            etAddress.getText().toString().trim().length() != 0) {
                        UpdateLocation();
                    } else {
                        Toast.makeText(MainSetPlaces.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",id);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void SaveLocation()
    {
        double lati = 0;
        double longi = 0;
        List<Address> address = new ArrayList<>();
        address.clear();
        Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
        String loc = etAddress.getText().toString();
        try {
            address = geo.getFromLocationName(loc, 5);
            if (address.size() > 0) {
                lati = address.get(0).getLatitude();
                longi=  address.get(0).getLongitude();
            }
        } catch (Exception ex) {

        }
        dbUtilities.insertLocation(getIntent().getIntExtra("id",0),
                etPlace.getText().toString(),
                etSubName.getText().toString(),
                rate,
                lati,
                longi,
                etAddress.getText().toString(),
                spType.getSelectedItem().toString());

        Intent intent = new Intent(MainSetPlaces.this, MainHome.class);
        intent.putExtra("id",id);
        intent.putExtra("name",getIntent().getStringExtra("name"));
        startActivity(intent);
        finish();
    }

    private void UpdateLocation()
    {
        double lati = 0;
        double longi = 0;
        List<Address> address = new ArrayList<>();
        address.clear();
        Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
        String loc = etAddress.getText().toString();
        try {
            address = geo.getFromLocationName(loc, 5);
            if (address.size() > 0) {
                lati = address.get(0).getLatitude();
                longi=  address.get(0).getLongitude();
            }
        } catch (Exception ex) {

        }
        dbUtilities.updateLocation(getIntent().getIntExtra("locId",0),
                getIntent().getIntExtra("id",0),
                etPlace.getText().toString(),
                etSubName.getText().toString(),
                rate,
                lati,
                longi,
                etAddress.getText().toString(),
                spType.getSelectedItem().toString());
        Intent intent = new Intent(MainSetPlaces.this, MainHome.class);
        intent.putExtra("id",id);
        intent.putExtra("name",getIntent().getStringExtra("name"));
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainSetPlaces.this, MainHome.class);
        intent.putExtra("id",id);
        intent.putExtra("name",getIntent().getStringExtra("name"));
        startActivity(intent);
        finish();
    }
}

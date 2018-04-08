package com.example.dheoclaveria.lecture2;

import android.app.Service;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Form extends AppCompatActivity {

    TextView txt1, txt2;

    Location location;
    LocationManager locationManager;
    MyLocationListener listener = new MyLocationListener();
    Criteria criteria;
    String bestprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        txt1 = (TextView) findViewById(R.id.txt1);

        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setPowerRequirement(criteria.POWER_LOW);
        criteria.setCostAllowed(true);

        locationManager = (LocationManager)getSystemService(Service.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        }catch (SecurityException ex){}

        bestprovider = locationManager.getBestProvider(criteria,true);
        Toast.makeText(getApplicationContext(),bestprovider,Toast.LENGTH_LONG).show();

    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            txt1.setText( "Latitude : " + location.getLatitude() + "\n Longtitude : " + location.getLongitude() );
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}

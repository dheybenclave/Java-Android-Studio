package com.example.dheoclaveria.lecture4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Main_Form extends FragmentActivity implements OnMapReadyCallback{


    private GoogleMap gMap ;
    private CameraPosition cPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        SupportMapFragment supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(googleMap.MAP_TYPE_NORMAL);
        cPosition = new CameraPosition(new LatLng(14.6715,120.93488),15,0,0);
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(cPosition);
        gMap.animateCamera(update);
        gMap.addMarker(new MarkerOptions().position(new LatLng(14.6715,120.93488)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).
                        title("Navotas City").snippet("Fishing Captila of the Philippines").draggable(true));
        gMap.getUISettings().setZoomControlIsEnabled(true);
        gMap.getUISettings().setMyLocationButtonEnabled(true);
        
        gMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker arg0) {
                Toast.makeText(Main_Form.this,"Latitude : " + arg0.getPosition().latitude+ " \n" +
                                            "Longtitude : " + arg0.getPosition().longitude,Toast.LENGTH_SHORT).show();

            }
        });
    }
}

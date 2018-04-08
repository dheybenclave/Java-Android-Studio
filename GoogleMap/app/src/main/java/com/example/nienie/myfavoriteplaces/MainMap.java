package com.example.nienie.myfavoriteplaces;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private CameraPosition position;
    List<Location> location = new ArrayList<Location>();
    DBUtilities dbUtilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        dbUtilities = new DBUtilities(getApplicationContext());
      //  LoadLocation(getIntent().getIntExtra("id", 0));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if(getIntent().getStringExtra("from").equals("MAINHOME")) {
            LoadLocationAll(getIntent().getIntExtra("id", 0));
        }
        if(getIntent().getStringExtra("from").equals("CUSTOMLISTVIEW"))
        {
            LoadLocationSingle(getIntent().getIntExtra("id", 0));
        }
        gMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(MainMap.this,
                        "Latitude: " + marker.getPosition().latitude + "\n" +
                                "Longitude: " + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoadLocationAll(int id) {

        location.clear();
        Cursor c = dbUtilities.getAllLocationByUserId(id);
        if (c.moveToFirst()) {
            do {
                int locationId = c.getInt(0);
                int userId = c.getInt(1);
                String place = c.getString(2);
                String subName = c.getString(3);
                double rate = c.getDouble(4);
                double lati = c.getDouble(5);
                double longi = c.getDouble(6);
                String address = c.getString(7);
                String type = c.getString(8);
                int bitmap= 0;
                if (type.toUpperCase().equals("HOSPITAL"))
                {
                    bitmap = R.mipmap.hospital;
                }
                else if(type.toUpperCase().equals("RESTAURANT"))
                {
                    bitmap = R.mipmap.restaurant;
                }
                else if(type.toUpperCase().equals("SCHOOL"))
                {
                    bitmap = R.mipmap.school;
                }
                else if(type.toUpperCase().equals("OFFICE"))
                {
                    bitmap = R.mipmap.office;
                }
                else if(type.toUpperCase().equals("PARK"))
                {
                    bitmap = R.mipmap.park;
                }
                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                position = new CameraPosition(new LatLng(lati, longi), 15, 0, 0);
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
                gMap.animateCamera(update);
                gMap.getUiSettings().setZoomControlsEnabled(true);
                gMap.getUiSettings().setMyLocationButtonEnabled(false);
                gMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lati, longi))
                        .icon(BitmapDescriptorFactory.fromResource(bitmap))
                        .title(place)
                        .snippet(subName)
                        .draggable(false));
            } while (c.moveToNext());
        } else {
            Toast.makeText(getApplicationContext(), "There is no existing record", Toast.LENGTH_SHORT).show();
        }
    }

    private void LoadLocationSingle(int id) {

        location.clear();
        Cursor c = dbUtilities.getLocationById(id);
        if (c.moveToFirst()) {
            do {
                int locationId = c.getInt(0);
                int userId = c.getInt(1);
                String place = c.getString(2);
                String subName = c.getString(3);
                double rate = c.getDouble(4);
                double lati = c.getDouble(5);
                double longi = c.getDouble(6);
                String address = c.getString(7);
                String type = c.getString(8);
                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                int bitmap= 0;
                if (type.toUpperCase().equals("HOSPITAL"))
                {
                    bitmap = R.mipmap.hospital;
                }
                else if(type.toUpperCase().equals("RESTAURANT"))
                {
                    bitmap = R.mipmap.restaurant;
                }
                else if(type.toUpperCase().equals("SCHOOL"))
                {
                    bitmap = R.mipmap.school;
                }
                else if(type.toUpperCase().equals("OFFICE"))
                {
                    bitmap = R.mipmap.office;
                }
                else if(type.toUpperCase().equals("PARK"))
                {
                    bitmap = R.mipmap.park;
                }
                position = new CameraPosition(new LatLng(lati, longi), 15, 0, 0);
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
                gMap.animateCamera(update);
                gMap.getUiSettings().setZoomControlsEnabled(true);
                gMap.getUiSettings().setMyLocationButtonEnabled(false);
                gMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lati, longi))
                        .icon(BitmapDescriptorFactory.fromResource(bitmap))
                        .title(place)
                        .snippet(subName)
                        .draggable(false));
            } while (c.moveToNext());
        } else {
            Toast.makeText(getApplicationContext(), "There is no existing record", Toast.LENGTH_SHORT).show();
        }
    }
}

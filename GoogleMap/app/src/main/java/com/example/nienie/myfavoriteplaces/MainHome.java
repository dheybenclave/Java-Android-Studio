package com.example.nienie.myfavoriteplaces;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainHome extends AppCompatActivity {
    Button btnViewAllPlaces;
    ListView lvPlace;
    private CustomListView adapter;
    List<User> user = new ArrayList<User>();
    List<Location> location = new ArrayList<Location>();
    DBUtilities dbUtilities;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        setTitle(getIntent().getStringExtra("name"));
        btnViewAllPlaces = (Button) findViewById(R.id.btnViewAllPlaces);
        adapter = new CustomListView(this, location);
        dbUtilities = new DBUtilities(getApplicationContext());
        lvPlace = (ListView) findViewById(R.id.lvPlaces);
        lvPlace.setAdapter(adapter);
        id = getIntent().getIntExtra("id", 0);
       // Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
        LoadLocation(id);
        btnViewAllPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHome.this, MainMap.class);
                intent.putExtra("id", id);
                intent.putExtra("from", "MAINHOME");
                startActivity(intent);
                // finish();
            }
        });
      //  lvPlace.setOnItemClickListener(new ListViewItemCLick());
        lvPlace.setOnItemLongClickListener(new ListViewItemLongClick());
        lvPlace.setOnItemClickListener(new ListViewItemCLick());
        LoadActionBar();
    }

    private void LoadActionBar() {
        getSupportActionBar().setDisplayOptions(android.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();
        TextView tvTitleBar = (TextView) view.findViewById(R.id.tvtitleBar);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_bar_back);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tvTitleBar.setText(getIntent().getStringExtra("name"));
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBoxLogout();
            }
        });
    }


    private class ListViewItemCLick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainHome.this, MainSetPlaces.class);
            intent.putExtra("locId",location.get(i).getLocationID());
            intent.putExtra("id", id);
            intent.putExtra("place", location.get(i).getFldPlace());
            intent.putExtra("subName", location.get(i).getFldSubName());
            intent.putExtra("rate", location.get(i).getFldRate());
            intent.putExtra("lat", location.get(i).getFldLat());
            intent.putExtra("long", location.get(i).getFldLong());
            intent.putExtra("address", location.get(i).getFldAddress());
            intent.putExtra("type", location.get(i).getFldType());
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("SU","UPDATE");
            startActivity(intent);
            finish();
        }
    }
    public class ListViewItemLongClick implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            DialogBoxDelete(location.get(i).getLocationID());
            return true;
        }
    }

    private void LoadLocation(int id) {
        location.clear();
        lvPlace.setAdapter(null);
        adapter = new CustomListView(this, location);
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
                Location l = new Location(locationId, userId, place, subName, rate, lati, longi, address, type);
                location.add(l);
            } while (c.moveToNext());
            lvPlace.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), "There is no loaded place", Toast.LENGTH_SHORT).show();
        }
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            Intent intent = new Intent(MainHome.this, MainSetPlaces.class);
            intent.putExtra("id", this.id);
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("SU","SAVE");
            startActivity(intent);
            finish();
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //   return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void DialogBoxDelete(final int locId) {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                MainHome.this);
        alertDialog2.setTitle("Are you sure you want to Delete this 'PLACE'?");
        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbUtilities.deleteMyLocation(locId);
                        LoadLocation(id);
                    }
                });
        alertDialog2.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
    }

    public void dialogBoxLogout() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                MainHome.this);
        alertDialog2.setTitle("Are you sure you want to Logout?");
        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainHome.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        alertDialog2.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
    }
}

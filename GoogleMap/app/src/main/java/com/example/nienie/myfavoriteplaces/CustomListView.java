package com.example.nienie.myfavoriteplaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nienie on 17/09/2017.
 */

public class CustomListView extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    List<Location> location;

    public CustomListView(Activity a, List<Location> l) {
        this.activity = a;
        this.location = l;
    }

    @Override
    public int getCount() {
        return location.size();
    }

    @Override
    public Object getItem(int i) {
        return location.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.listviewitem, null);
        }

        TextView tvPlace = (TextView) view.findViewById(R.id.tvPlace);
        TextView tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        TextView tvType = (TextView) view.findViewById(R.id.tvType);
        Button btnViewMap = (Button) view.findViewById(R.id.btnvViewMap);
        RatingBar rbRate = (RatingBar) view.findViewById(R.id.rbRate);
        final Location l = location.get(i);
        tvPlace.setText("Place: " + l.getFldPlace().toUpperCase());
        tvAddress.setText("Address: " + l.getFldAddress().toUpperCase());
        tvType.setText("Type: " + l.getFldType().toUpperCase());
        rbRate.setRating(Float.parseFloat(String.valueOf(l.getFldRate())));

        btnViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainMap.class);
                intent.putExtra("from", "CUSTOMLISTVIEW");
                intent.putExtra("id",l.getLocationID());
                activity.startActivity(intent);
            }
        });
        return view;
    }
}

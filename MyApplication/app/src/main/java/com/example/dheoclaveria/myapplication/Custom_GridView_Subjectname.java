package com.example.dheoclaveria.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dheo Claveria on 10/1/2017.
 */

public class Custom_GridView_Subjectname extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<GridView_Subjectname_Data> lstdata;

    Resource res = new Resource();
    GridView_Subjectname_Data gsd;
    ImageView image;
    TextView title;
    LinearLayout panel;

    public static boolean isselectionmode = false;

    public Custom_GridView_Subjectname(Activity a, List<GridView_Subjectname_Data> e) {
        this.activity = a;
        this.lstdata = e;
    }

    @Override
    public int getCount() {
        return lstdata.size();
    }

    @Override
    public Object getItem(int pos) {
        return lstdata.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup vgroup ) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
           view = inflater.inflate(R.layout.grid_item, null);
        }

            image = view.findViewById(R.id.grid_item_image);
            title = view.findViewById(R.id.grid_item_title);
            panel = view.findViewById(R.id.grid_item_panel);

        gsd = lstdata.get(pos);

        image.setImageURI(res.forimagechoice(gsd.getImage()));
        panel.setBackgroundDrawable(res.forcolorchoice(gsd.getColor()));

        title.setText(gsd.getTitle());

        return view;
    }



}

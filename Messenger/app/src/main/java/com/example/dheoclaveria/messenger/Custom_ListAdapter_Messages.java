package com.example.dheoclaveria.messenger;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dheo Claveria on 8/12/2017.
 */

public class Custom_ListAdapter_Messages extends BaseAdapter {

    Messages_Data md;
    Uri imgphoto = null;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Messages_Data> lstdata;

    public Custom_ListAdapter_Messages(Activity a, List<Messages_Data> e) {
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
    public View getView(int pos, View view, ViewGroup viewGroup) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) view = inflater.inflate(R.layout.listview_iteminbox, null);

        md = lstdata.get(pos);
        int s = pos;
        ImageView photo = view.findViewById(R.id.photo);
        TextView lblname = view.findViewById(R.id.lblname);
        TextView lblbody = view.findViewById(R.id.lblbody);


        if (md.getMessages_photo() != null && md.getMessages_photo() != "") {
            imgphoto = Uri.parse(md.getMessages_photo());
        } else if (md.getMessages_photo() == null && md.getMessages_photo() != "") {
            imgphoto = Uri.parse("android.resource://com.example.dheoclaveria.messenger/" + R.drawable.insert);
        } else {
            imgphoto = Uri.parse("android.resource://com.example.dheoclaveria.messenger/" + R.drawable.insert);
        }
        photo.setImageURI(imgphoto);
        lblname.setText(md.getMessages_name());
        lblbody.setText(md.getMessages_body());

        return view;

    }


}

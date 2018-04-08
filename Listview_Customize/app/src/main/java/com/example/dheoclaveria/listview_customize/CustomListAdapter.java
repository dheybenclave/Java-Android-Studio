package com.example.dheoclaveria.listview_customize;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dheo Claveria on 7/15/2017.
 */


public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Employee> lstepmployee;

    public CustomListAdapter(Activity a, List<Employee> e) {
        this.activity = a;
        this.lstepmployee = e;

    }

    public int getCount() {
        return lstepmployee.size();
    }

    public Object getItem(int pos) {
        return lstepmployee.get(pos);
    }

    public long getItemId(int pos) {
        return pos;
    }

    public View getView(int pos, View view, ViewGroup vgroup) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) view = inflater.inflate(R.layout.activity_listitem, null);

        TextView lbl_id = view.findViewById(R.id.lblid);
        TextView lbl_fullname = view.findViewById(R.id.lblfullname);
        TextView lbl_position = view.findViewById(R.id.lblposition);
        TextView lbl_address = view.findViewById(R.id.lbladdress);
        TextView lbl_mobile = view.findViewById(R.id.lblmobile);
        TextView lbl_email = view.findViewById(R.id.lblemail);


        Employee e = lstepmployee.get(pos);
        lbl_id.setText(e.getId());
        lbl_fullname.setText((e.getFullname()));
        lbl_position.setText(e.getPosition());
        lbl_address.setText(e.getAddress());
        lbl_mobile.setText(e.getMobile());
        lbl_email.setText(e.getEmail());

        return view;
    }

}


package com.example.ruiz.customlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ruiz on 7/15/2017.
 */

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Employee> employees;

    public  CustomListAdapter(Activity  a , List<Employee> e){
        this.activity = a;
        this.employees = e;
    }
    public  int getCount(){
        return  employees.size();
    }
    public Object getItem(int pos){
        return  employees.get(pos);
    }
    public long getItemId(int pos){
        return  pos;
    }
    public View getView(int pos , View view , ViewGroup vGroup){
        if(inflater == null){
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.activity_list_item , null);
        }

        TextView txt_ID = (TextView) view.findViewById(R.id.txtID);
        TextView txt_fname = (TextView) view.findViewById(R.id.txtfullname);
        TextView txtpos = (TextView) view.findViewById(R.id.txtPosition);
        TextView txtaddr = (TextView) view.findViewById(R.id.txtAddress);
        TextView txtmobile = (TextView) view.findViewById(R.id.txtMobile);
        TextView txtemail = (TextView) view.findViewById(R.id.txtEmail);

        Employee e = employees.get(pos);
        txt_ID.setText(e.getID());
        txt_fname.setText(e.getFname());
        txtpos.setText(e.getPosition());
        txtaddr.setText(e.getAddress());
        txtmobile.setText(e.getMobile());
        txtemail.setText(e.getEmail());

        return view;
    }
}

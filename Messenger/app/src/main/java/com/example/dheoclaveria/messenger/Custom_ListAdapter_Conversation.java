package com.example.dheoclaveria.messenger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dheo Claveria on 8/12/2017.
 */

public class Custom_ListAdapter_Conversation extends BaseAdapter {
    Conversation_Data cd;
    String body = "";
    String date = "";
    private Activity activity;
    private LayoutInflater inflater;
    private List<Conversation_Data> lstdata;

    public Custom_ListAdapter_Conversation(Activity a, List<Conversation_Data> e) {
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

        if (view == null) view = inflater.inflate(R.layout.listview_itemconvo, null);

        TextView lblbody = view.findViewById(R.id.lblbody);
        TextView lbldate = view.findViewById(R.id.lbldate);

        LinearLayout pnlleft = view.findViewById(R.id.pnlleft);
        LinearLayout pnlright = view.findViewById(R.id.pnlright);

        cd = lstdata.get(pos);


        int type = Integer.valueOf(cd.getType());

        if (type == 1) {
            lblbody.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            lbldate.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            pnlleft.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#329581")));
            body = cd.getBody();
            date = cd.getDate();

        } else if (type == 2) {
            lblbody.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            lbldate.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            pnlright.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#329581")));
            body = cd.getBody();
            date = cd.getDate();
        }

        lblbody.setText(body);
        lbldate.setText(date);

        return view;
    }
}

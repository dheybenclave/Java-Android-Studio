package com.example.dheoclaveria.prelim_exam;

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
 * Created by Dheo Claveria on 7/22/2017.
 */
//dheo claveria and noel atazar prelim examaniation
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Todo_Data> lstdata;
    Todo_Data td;



    public CustomListAdapter(Activity a, List<Todo_Data> e) {
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
    public View getView(int pos, View view, ViewGroup vgroup) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) view = inflater.inflate(R.layout.activity_listview_item__form, null);

        TextView lbl_id = view.findViewById(R.id.imgperson);
        TextView lbl_title = view.findViewById(R.id.lbltitle);
        TextView lbl_details = view.findViewById(R.id.lbldetails);
        TextView lbl_frmdate = view.findViewById(R.id.lblfrmdate);
        TextView lbl_todate = view.findViewById(R.id.lbltodate);
        LinearLayout prioritylevel =view.findViewById(R.id.pnlitem);


        td = lstdata.get(pos);

        lbl_id.setText(td.getId());
        lbl_title.setText(td.getTitle());
        lbl_details.setText(td.getDetails());
        lbl_frmdate.setText("fr : " +td.getFrmdate());
        lbl_todate.setText("to : " +td.getTodate());
        int level = td.getPrioritylevel();
        if (level == 0) {
            //low
            prioritylevel.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2dcc70")));
            lbl_id.setBackgroundResource(R.drawable.low);
        } else if (level == 1) {
            //meduim
            prioritylevel.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffae00")));
            lbl_id.setBackgroundResource(R.drawable.medium);

        } else if (level == 2) {
            //high
            prioritylevel.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c23a2c")));
            lbl_id.setBackgroundResource(R.drawable.high);
        }

        return view;

    }

    public String overloadtitle(String title) {
        int sizetitle = td.getTitle().length();
        if (sizetitle >= 25) {
            String sub = td.getTitle().substring(25, sizetitle);
            String title1 = td.getTitle().replace(sub, "......");
            title = title1;
        } else {
        }
        return title;
    }

    public String overloaddetails(String details) {
        int sizedetails = td.getDetails().length();
        if (sizedetails >= 35) {
            String sub = td.getDetails().substring(35, sizedetails);
            String details1 = td.getDetails().replace(sub, "......");
            details = details1;
        } else {
        }
        return details;
    }
}

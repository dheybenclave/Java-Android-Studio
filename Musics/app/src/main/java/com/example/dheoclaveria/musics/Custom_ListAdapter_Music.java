package com.example.dheoclaveria.musics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static com.example.dheoclaveria.musics.Main_Form.playingid;

/**
 * Created by Dheo Claveria on 8/23/2017.
 */

public class Custom_ListAdapter_Music extends BaseAdapter {
    Music_Data md;
    Uri imgphoto = null;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Music_Data> lstdata;
    int counter = 0;

    public Custom_ListAdapter_Music(Activity a, List<Music_Data> e) {
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

        if (view == null) view = inflater.inflate(R.layout.list_itemmusicinfo, null);

        md = lstdata.get(pos);


        ImageView imgstatus = view.findViewById(R.id.imgstatus);
        TextView lbllistsongname = view.findViewById(R.id.lblsongname);
        TextView lbllistartistname = view.findViewById(R.id.lblartistname);
        TextView lbllistduration = view.findViewById(R.id.lblduration);

        LinearLayout pnllist = view.findViewById(R.id.pnllist);


        Uri isplayphoto = null;
        if (playingid != null) {

            if (playingid.equals(md.getMusicid())) {

                    isplayphoto = Uri.parse("android.resource://com.example.dheoclaveria.musics/" + R.drawable.isplaying);
                    pnllist.setBackground(new ColorDrawable(Color.parseColor("#545454")));
                }
                else{
                isplayphoto = Uri.parse("android.resource://com.example.dheoclaveria.musics/" + R.drawable.blank);
                pnllist.setBackground(new ColorDrawable(Color.parseColor("#121212")));
                lbllistsongname.setEllipsize(TextUtils.TruncateAt.END);
            }
            }
            else{
            isplayphoto = Uri.parse("android.resource://com.example.dheoclaveria.musics/" + R.drawable.blank);
            lbllistsongname.setEllipsize(TextUtils.TruncateAt.END);
            pnllist.setBackground(new ColorDrawable(Color.parseColor("#121212")));
        }


        imgstatus.setImageURI(isplayphoto);
        lbllistsongname.setText(md.getMusicname());
        lbllistartistname.setText(md.getArtist());
        lbllistduration.setText(getTimeString(Long.valueOf(md.duration)));
        return view;
    }

    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();

        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buf
                .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }
}

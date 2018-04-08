package com.example.dheoclaveria.projectos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static com.example.dheoclaveria.projectos.Resource.subcolor;
import static com.example.dheoclaveria.projectos.Resource.subimage;

/**
 * Created by Dheo Claveria on 10/1/2017.
 */

public class Custom_GridView_Iconselection extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<GridView_Iconselection_Data> lstdata;

    Resource res = new Resource();
    GridView_Iconselection_Data gsd;
    ImageView image;
    TextView title;
    LinearLayout panel;

    public static boolean isselectionmode = false;

    public Custom_GridView_Iconselection(Activity a, List<GridView_Iconselection_Data> e) {
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

//            if(isselectionmode == true){view = inflater.inflate(R.layout.grid_item_selection, null);}
//            else{view = inflater.inflate(R.layout.grid_item, null);}
            view = inflater.inflate(R.layout.grid_item_selection, null);
        }
//
//        if(isselectionmode == true)
//        {
//            image = view.findViewById(R.id.grid_item_selection_image);
//            title = view.findViewById(R.id.grid_item_selection_title);
//            panel = view.findViewById(R.id.grid_item_selection_panel);
//        }
//        else {
            image = view.findViewById(R.id.grid_item_selection_image);
            title = view.findViewById(R.id.grid_item_selection_title);
            panel = view.findViewById(R.id.grid_item_selection_panel);
      //  }

        try {
            gsd = lstdata.get(pos);
            Uri sube = forimagechoice(gsd.getImage());
            Drawable subp = forcolorchoice(gsd.getColor());
            image.setImageURI(sube);
            panel.setBackgroundDrawable(subp);
            title.setText(gsd.getTitle());
        }catch(Exception ex){}
        return view;
    }
    public Uri forimagechoice(String imagename) {

        try {

            switch (imagename) {
                case "book":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.book);
                    break;
                case "bookmark":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.bookmark);
                    break;
                case "bookopen":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.bookopen);
                    break;
                case "books":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.books);
                    break;
                case "compass":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.compass);
                    break;
                case "diploma":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.diploma);
                    break;
                case "erase":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.erase);
                    break;
                case "formula":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.formula);
                    break;
                case "notepad":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.notepad);
                    break;
                case "pipette":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.pipette);
                    break;
                case "projection":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.projection);
                    break;
                case "quill":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.quill);
                    break;
                case "studenthat":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.studenthat);
                    break;
                case "transparent":
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.transparent);
                    break;
                default:
                    subimage = Uri.parse("android.resource://com.example.dheoclaveria.projectos/" + R.drawable.book);

            }

        } catch (Exception ex) {}
        return subimage;
    }

    public  ColorDrawable forcolorchoice(String color) {
        switch (color) {
            case "dark":
                subcolor = new ColorDrawable(Color.parseColor("#242424"));
                break;
            case "red":
                subcolor = new ColorDrawable(Color.parseColor("#f6402b"));
                break;
            case "darkpink":
                subcolor = new ColorDrawable(Color.parseColor("#eb1461"));
                break;
            case "violet":
                subcolor = new ColorDrawable(Color.parseColor("#9c1ab2"));
                break;
            case "blue":
                subcolor = new ColorDrawable(Color.parseColor("#1193f5"));
                break;
            case "lightblue":
                subcolor = new ColorDrawable(Color.parseColor("#00bbd6"));
                break;
            case "bluegreen":
                subcolor = new ColorDrawable(Color.parseColor("#009788"));
                break;
            case "green":
                subcolor = new ColorDrawable(Color.parseColor("#01b876"));
                break;
            case "yellow":
                subcolor = new ColorDrawable(Color.parseColor("#ffec16"));
                break;
            case "orange":
                subcolor = new ColorDrawable(Color.parseColor("#ff9801"));
                break;
            case "darkorange":
                subcolor = new ColorDrawable(Color.parseColor("#ff5505"));
                break;
            case "darkbrown":
                subcolor = new ColorDrawable(Color.parseColor("#7a5447"));
                break;
            case "silver":
                subcolor = new ColorDrawable(Color.parseColor("#9d9d9d"));
                break;
            case "gray":
                subcolor = new ColorDrawable(Color.parseColor("#5f7c8c"));
                break;

            default:
                subcolor = new ColorDrawable(Color.parseColor("#5f7c8c"));
        }
        return subcolor;
    }




}

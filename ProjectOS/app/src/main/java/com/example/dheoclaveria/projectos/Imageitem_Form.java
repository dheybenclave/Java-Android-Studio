package com.example.dheoclaveria.projectos;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dheoclaveria.projectos.Main_Form.adapter_subject;
import static com.example.dheoclaveria.projectos.Main_Form.gettitle;


public class Imageitem_Form extends AppCompatActivity {


    Button btnaddgroupnow;
    ImageView btnaddimage;
    EditText txtgroupname;
    TextView lblchangebackground;
    RelativeLayout pnlmain;
    GridView grdgroup;
    Resource res = new Resource();

    private Intent intentdata;
    public static final int CAMERA_REQUEST = 123;
    String image, background, passgetid, passgetcolor, passgetimage, passgettitle = "";
    Uri passsubimage;
    ColorDrawable passsubcolor;

    ImageView imgview;

    Bitmap bmp;

    int count = 0;
    List<Bitmap> lstbmp = new ArrayList<Bitmap>();
    PackageManager packageManager;
    private DBTools dbTools;
    private Cursor cursor;

    public static String getidimage = "";
    Button btnopencamera, btnback;
    public static int getindex = 0;
    GridView grdimage;

    public static Custom_GridView_ImageItem adapter_image;
    List<GridView_ImageItem_Data> image_datas = new ArrayList<GridView_ImageItem_Data>();
    private boolean cameeraopen = false;
    private DialogInterface.OnClickListener dialoglistener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageitem__form);

        dbTools = new DBTools(getApplication());
        grdimage = (GridView) findViewById(R.id.grdimage);
        adapter_image = new Custom_GridView_ImageItem(this, image_datas);
        grdimage.setAdapter(adapter_image);
        grdimage.setOnItemClickListener(new GridViewonItemClick());
        grdimage.setOnItemLongClickListener(new GridViewonLongPress());
        registerForContextMenu(grdimage);

        btnopencamera = (Button) findViewById(R.id.btnopencamera);
        btnopencamera.setOnClickListener(new ButtonHandler());

        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new ButtonHandler());

        load();

    }


    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (btnopencamera == v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            } else if (btnback == v) {
                finish();
            }

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CAMERA_REQUEST) && resultCode == Activity.RESULT_OK) {
            try {
                cameeraopen = true;
                bmp = (Bitmap) data.getExtras().get("data");
                dbTools.insertitem("None", gettitle, "None", "None", "None", "None", res.BitMapToString(bmp));
                String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());

                Uri songuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
             //   String songlist = MediaStore.Images.Media. + " != 0";
                cursor = managedQuery(songuri, new String[]{"*"},null, null,MediaStore.Images.Media._ID + " DESC LIMIT 1");
                if (cursor != null) {
                    if (cursor.moveToFirst()) {

                        do {
                            String as = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                            String as1 = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            imgview.setImageAlpha(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            Toast.makeText(this, as + as1, Toast.LENGTH_SHORT).show();

                        } while (cursor.moveToNext());
                    }
                }


            } catch (Exception ex) {
                ex.getMessage();
            }

        }
        load();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(1, intentdata);
        finish();
    }

    public void load() {
        adapter_image = new Custom_GridView_ImageItem(this, image_datas);
        image_datas.clear();


        try {
            String query = "SELECT * FROM " + dbTools.tbl_name + " WHERE " + dbTools.fld_subjectname + " = '" + gettitle.toString() + "' ";
            cursor = dbTools.executeQuery(query);

            if (cursor.moveToFirst()) {

                do {
                    int getid = cursor.getInt(0);
                    String getimage = cursor.getString(7);
                    // Toast.makeText(this, getimage, Toast.LENGTH_SHORT).show();
                    image_datas.add(new GridView_ImageItem_Data(String.valueOf(getid), getimage));
                }
                while (cursor.moveToNext());
                grdimage.setAdapter(adapter_image);
            }
        } catch (Exception ex) {
            image_datas.clear();
        }
    }


    private class GridViewonItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            getidimage = image_datas.get(i).getId().toString();
            getindex = i;

            Intent intent = new Intent(Imageitem_Form.this, Content_Form.class);
            startActivityForResult(intent, 1);
        }
    }

    private class GridViewonLongPress implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            getidimage = image_datas.get(i).getId().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(Imageitem_Form.this);
            builder.setMessage("Do you want to delete this item? .").setPositiveButton("Yes", dialoglistener).setNegativeButton("No", dialoglistener).show();

            return false;

        }
    }


    public void fordelete() {

        dbTools.deleteData(dbTools.tbl_subjectname, Integer.valueOf(getidimage));
        // DELETE FROM `db_scheduler_system`.`tbl_schedule` WHERE `schedule_id`='0';
//        String s = "DELETE FROM " + dbTools.tbl_name + "  WHERE " + dbTools.fld_id + "='" + getidimage + "'";
//        dbTools.db.execSQL(s);
        load();
        Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT).show();
        adapter_subject.notifyDataSetChanged();

    }

    public void dialoglistener() {
        dialoglistener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        fordelete();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        adapter_subject.notifyDataSetChanged();
    }

    private class Date extends java.util.Date {
    }
}

package com.example.dheoclaveria.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main_Form extends AppCompatActivity {

    public static  final int CAMERA_REQUEST = 123;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static String getid,gettitle,getimage,getcolor = null;
    public static boolean isupdate =false;
    public static Custom_GridView_Subjectname adapter_subject;
    Button btnaddgroupcell;
    GridView grdgroup;
    LinearLayout forimages;
    Resource res = new Resource();
    DBTools dbTools;
    Cursor cursor;
    Calendar c;
    DialogInterface.OnClickListener dialoglistener;
    List<GridView_Subjectname_Data> subject_datas = new ArrayList<GridView_Subjectname_Data>();
    List<String> path = new ArrayList<>();
    private TextView mTextMessage;
    private boolean islongpress;
    private Bitmap bmp;
    private List<Bitmap> lstbmp = new ArrayList<>();
    private int count  = -1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("Images");
                    Intent intent = new Intent(Main_Form.this,ImageSlider.class);
                    startActivityForResult(intent,1);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("Browser");
                    return true;
            }
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        dbTools = new DBTools(getApplication());
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        btnaddgroupcell= (Button) findViewById(R.id.btnaddgroupcell);



        grdgroup = (GridView) findViewById(R.id.grdgroup);
        adapter_subject = new Custom_GridView_Subjectname(this,subject_datas);
        grdgroup.setAdapter(adapter_subject);
        grdgroup.setOnItemClickListener(new GridViewonItemClick());
        grdgroup.setOnItemLongClickListener(new GridViewonLongPress());
        registerForContextMenu(grdgroup);

        setContentView(R.layout.activity_main__form);


       // forimages = (LinearLayout) findViewById(R.id.forimages);

        LoadGroupSubject();
        dialoglistener();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            if (requestCode == 1) {
                adapter_subject.notifyDataSetChanged();
                isupdate =false;
              //  isselectionmode = false;
            }
        }

        if((requestCode == CAMERA_REQUEST) && resultCode == Activity.RESULT_OK){

            bmp  = (Bitmap) data.getExtras().get("data");
            lstbmp.add(bmp);
            //  imgview.setImageBitmap(bmp);

            for(int i  = 0 ; i < lstbmp.size();i++)
            {
                Toast.makeText(getApplicationContext(), lstbmp.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
            count = lstbmp.size();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }

        adapter_subject.notifyDataSetChanged();
        LoadGroupSubject();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Option");
        menu.add("Add Image");
        menu.add("Update");
        menu.add("Delete");

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Add Image") {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);

        } else if (item.getTitle() == "Update") {
            //Toast.makeText(getApplicationContext(), getid + " " + gettitle, Toast.LENGTH_LONG).show();
            isupdate = true;
            Intent intent = new Intent(Main_Form.this, AddGroup_Form.class);
            startActivityForResult(intent, 1);

        } else if (item.getTitle() == "Delete") {
            AlertDialog.Builder builder = new AlertDialog.Builder(Main_Form.this);
            builder.setMessage("Do you want to delete " + gettitle + " group ?.").setPositiveButton("Yes", dialoglistener).setNegativeButton("No", dialoglistener).show();

            islongpress =false;
        }
        return true;
    }

    public void LoadGroupSubject() {
        grdgroup.setAdapter(null);
        adapter_subject = new Custom_GridView_Subjectname(this, subject_datas);
        subject_datas.clear();


        try {
            String query = "SELECT * FROM " + dbTools.tbl_subjectname + " ; ";
            cursor = dbTools.executeQuery(query);

            if (cursor.moveToFirst()) {

                do {
                    int getid = cursor.getInt(0);
                    gettitle = cursor.getString(1);
                    getimage = cursor.getString(2);
                    getcolor = cursor.getString(3);
                    subject_datas.add(new GridView_Subjectname_Data(String.valueOf(getid), gettitle, getimage,getcolor));
                }
                while (cursor.moveToNext());
                grdgroup.setAdapter(adapter_subject);
            }
        } catch (Exception ex) {
            subject_datas.clear();

        }
    }

    public void fordelete() {

        dbTools.deleteData(dbTools.tbl_subjectname,Integer.valueOf(getid));
        adapter_subject.notifyDataSetChanged();
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

    }

    private class GridViewonItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            getid = subject_datas.get(i).getId().toString();
            gettitle = subject_datas.get(i).getTitle().toString();
            if (!islongpress) {
                Toast.makeText(getApplicationContext(),getid + " " + gettitle,Toast.LENGTH_LONG).show();
              // Intent intent = new Intent(Main_Form.this, Content_Form.class);
               //startActivityForResult(intent, 1);


            }
            adapter_subject.notifyDataSetChanged();
            islongpress = false;
        }
    }

    private class GridViewonLongPress implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            getid = subject_datas.get(i).getId().toString();
            gettitle = subject_datas.get(i).getTitle().toString();
            islongpress  = true;
            return false;
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (btnaddgroupcell == v)
            {
                Intent intent = new Intent(Main_Form.this, AddGroup_Form.class);
                startActivityForResult(intent, 1);
            }
        }
    }

}

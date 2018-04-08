package com.example.dheoclaveria.projectos;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.dheoclaveria.projectos.Custom_GridView_Subjectname.isselectionmode;

public class Main_Form extends AppCompatActivity {


    Button btnaddgroup;
    GridView grdgroup;

    LinearLayout forimages;
    Resource res = new Resource();

    DBTools dbTools;
    Cursor cursor;
    Calendar c;
    DialogInterface.OnClickListener dialoglistener;
    public static String getid,gettitle,getimage,getcolor = null;
    public static boolean isupdate =false;


    public static Custom_GridView_Subjectname adapter_subject;
    List<GridView_Subjectname_Data> subject_datas = new ArrayList<GridView_Subjectname_Data>();

    public static  final int CAMERA_REQUEST = 123;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private boolean islongpress;

    List<String> path = new ArrayList<>();
    private Bitmap bmp;
    public static List<Bitmap> lstbmp = new ArrayList<>();
    private int count  = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        dbTools = new DBTools(getApplication());

        btnaddgroup = (Button) findViewById(R.id.btnaddgroup);
        btnaddgroup.setOnClickListener(new ButtonHandler());


        grdgroup = (GridView) findViewById(R.id.grdgroup);
        adapter_subject = new Custom_GridView_Subjectname(this,subject_datas);
        grdgroup.setAdapter(adapter_subject);
        grdgroup.setOnItemClickListener(new GridViewonItemClick());
        grdgroup.setOnItemLongClickListener(new GridViewonLongPress());
        registerForContextMenu(grdgroup);

        forimages = (LinearLayout) findViewById(R.id.forimages);

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
                isselectionmode = false;
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


    private class GridViewonItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            getid = subject_datas.get(i).getId().toString();
            gettitle = subject_datas.get(i).getTitle().toString();
            if (!islongpress) {
                Toast.makeText(getApplicationContext(),getid + " " + gettitle,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Main_Form.this, Imageitem_Form.class);
                startActivityForResult(intent, 1);

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

            if (btnaddgroup == v)
            {
                Intent intent = new Intent(Main_Form.this, AddGroup_Form.class);
                startActivityForResult(intent, 1);
            }
        }
    }

    public void fordelete() {

        dbTools.deleteData(dbTools.tbl_subjectname,Integer.valueOf(getid));
       // DELETE FROM `db_scheduler_system`.`tbl_schedule` WHERE `schedule_id`='0';

        String s ="DELETE FROM "+dbTools.tbl_name+"  WHERE "+dbTools.fld_subjectname+"='"+gettitle+"'";
        dbTools.db.execSQL(s);
        Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT).show();
        LoadGroupSubject();;

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


}

package com.example.dheoclaveria.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static com.example.dheoclaveria.myapplication.Main_Form.adapter_subject;
import static com.example.dheoclaveria.myapplication.Main_Form.getid;
import static com.example.dheoclaveria.myapplication.Main_Form.isupdate;

//import static com.example.dheoclaveria.projectos.Custom_GridView_Subjectname.isselectionmode;
//import static com.example.dheoclaveria.projectos.Main_Form.adapter_subject;
//import static com.example.dheoclaveria.projectos.Main_Form.getid;
//import static com.example.dheoclaveria.projectos.Main_Form.isupdate;
//import static com.example.dheoclaveria.projectos.Selection_Form.isiconselection;

public class AddGroup_Form extends Activity {

    Button btnaddgroupnow;
    ImageView btnaddimage;
    EditText txtgroupname;
    TextView lblchangebackground;
    RelativeLayout pnlmain;

    Resource res = new Resource();

    DBTools dbTools;
    Cursor cursor;
    private Intent intentdata;

    String image, background, passgetcolor,passgetimage,passgettitle = "";
    Uri passsubimage;
    ColorDrawable passsubcolor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group__form);

        dbTools = new DBTools(getApplicationContext());

        pnlmain = (RelativeLayout) findViewById(R.id.pnlmain);

        btnaddimage = (ImageView) findViewById(R.id.btnaddimage);
        btnaddimage.setOnClickListener(new ImageViewEventonClick());

        txtgroupname = (EditText) findViewById(R.id.txtgroupname);

        btnaddgroupnow = (Button) findViewById(R.id.btnaddgroupnow);
        btnaddgroupnow.setOnClickListener(new ButtonEventonClick());

        lblchangebackground = (TextView) findViewById(R.id.lblchangebackground);
        lblchangebackground.setOnClickListener(new EditTextonClick());

        passgetcolor = "default";
        passgetimage = "default";

        if (isupdate ) {forsubjectgroupupdate();}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
     //   isselectionmode = false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            if (requestCode == 1) {

                passgetimage = data.getStringExtra("imagename");
                passgetcolor = data.getStringExtra("color");
//                if(isiconselection){btnaddimage.setImageURI(res.forimagechoice(passgetimage));}
//                else{pnlmain.setBackgroundDrawable(res.forcolorchoice(passgetcolor));}
//                isselectionmode = false;
            }
        }

    }

    private class ButtonEventonClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (btnaddgroupnow == view) {
                foraddinggroup();
            }
        }
    }

    private class ImageViewEventonClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (btnaddimage == view) {
//                Intent intent = new Intent(AddGroup_Form.this, Selection_Form.class);
//                startActivityForResult(intent, 1);
//                isiconselection = true;
            }
        }
    }

    private class EditTextonClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if(lblchangebackground == view)
            {
//                Intent intent = new Intent(AddGroup_Form.this, Selection_Form.class);
//                startActivityForResult(intent, 1);
//                isiconselection = false;
            }
        }
    }

    public void forsubjectgroupupdate() {

        try {
            String query = "SELECT * FROM " + dbTools.tbl_subjectname + " WHERE "+dbTools.sn_id + " = " + getid +" ; ";
            cursor = dbTools.executeQuery(query);

            if (cursor.moveToFirst()) {

                do {
                    passgettitle = cursor.getString(1);
                    passgetimage = cursor.getString(2);
                    passgetcolor = cursor.getString(3);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception ex) {
        }

        txtgroupname.setText(passgettitle);

        btnaddimage.setImageURI(res.forimagechoice(passgetimage));
        pnlmain.setBackgroundDrawable(res.forcolorchoice(passgetcolor));

        getid = null; isupdate = false;

    }
    public void createfolder(String title)
    {
        try {
            String rootPath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +"/CaptureLecture/"+title;
            File file= new File(rootPath);
            if(!file.exists()){
                file.mkdirs();
            }
        }catch (Exception ex){}
    }
    private void foraddinggroup() {

        String title = txtgroupname.getText().toString().toLowerCase();
        String query = "SELECT * FROM " + dbTools.tbl_subjectname + " WHERE " + dbTools.sn_title + " LIKE '%" + title + "%'";
        cursor = dbTools.executeQuery(query);

        try {
            if (title.length() != 0) {
                if (cursor.getCount() != 0) {
                    Toast.makeText(getApplicationContext(), title + " is already exist!.", Toast.LENGTH_LONG).show();
                } else {
                    intentdata = getIntent();
                    dbTools.insertsubject(title, passgetimage, passgetcolor);
                    createfolder(title);
                    Toast.makeText(getApplicationContext(), "Adding group success!.", Toast.LENGTH_LONG).show();
                    setResult(1, intentdata);
                    finish();
                    adapter_subject.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please insert title group!.", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){}
    }
    public void resetupdate()
    {
        passgetcolor = null;
        passgetimage = null;
        passgettitle = null;
        passsubimage = null;
        passsubcolor= null;
        isupdate = false;
    }


}

package com.example.dheoclaveria.projectos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.dheoclaveria.projectos.Imageitem_Form.getindex;
import static com.example.dheoclaveria.projectos.Main_Form.CAMERA_REQUEST;
import static com.example.dheoclaveria.projectos.Main_Form.gettitle;

public class Content_Form extends AppCompatActivity {


    Button btnopencamera, btnprev, btnext, btnshowdetails;
    ImageView imgview;
    Resource res = new Resource();
    TextView lblgroupname;
    Bitmap bmp;

    int count = 0;
    List<Bitmap> lstbmp = new ArrayList<Bitmap>();
    PackageManager packageManager;
    private DBTools dbTools;
    private Cursor cursor;
    private boolean cameeraopen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content__form);
        dbTools = new DBTools(getApplication());
        btnopencamera = (Button) findViewById(R.id.btnopencamera);
        btnopencamera.setOnClickListener(new ButtonEvent());

        btnshowdetails = (Button) findViewById(R.id.btnshowdetails);
        btnshowdetails.setOnClickListener(new ButtonEvent());

        btnprev = (Button) findViewById(R.id.btnprev);
        btnprev.setOnClickListener(new ButtonEvent());

        btnext = (Button) findViewById(R.id.btnnext);
        btnext.setOnClickListener(new ButtonEvent());

        imgview = (ImageView) findViewById(R.id.imgview);

        packageManager = getPackageManager();

        lblgroupname = (TextView) findViewById(R.id.lblgroupname);


        asd();
    }

    private void forshowdetails() {

        Intent intent = new Intent(Content_Form.this, ImageDetails_Form.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        InputStream inputStream;
        if ((requestCode == CAMERA_REQUEST) && resultCode == Activity.RESULT_OK) {
            try {
                cameeraopen = true;
                Bundle extras = data.getExtras();
                bmp = (Bitmap) extras.get("data");

                dbTools.insertitem("None", gettitle, "None", "None", "None", "None", res.BitMapToString(bmp));
//                Uri songuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                //   String songlist = MediaStore.Images.Media. + " != 0";
//                cursor = managedQuery(songuri, new String[]{"*"}, null, null, MediaStore.Images.Media._ID + " DESC LIMIT 1");
//                if (cursor != null) {
//                    if (cursor.moveToFirst()) {
//
//                        do {
//                            String as = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
//                            Uri as1 = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
//                            imgview.setImageURI(as1);
//                            Toast.makeText(this, as + as1, Toast.LENGTH_SHORT).show();
//
//                        } while (cursor.moveToNext());
//                    }
//                }

               asd();
            } catch (Exception ex) {
                ex.getMessage();
            }


        }

    }

    private Uri getImageUri(String data) {
        Uri m_imgUri = null;
        File m_file;
        try {
            SimpleDateFormat m_sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String m_curentDateandTime = m_sdf.format(new Date());
            String m_imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + m_curentDateandTime + ".jpg";
            m_file = new File(m_imagePath);
            m_imgUri = Uri.fromFile(m_file);
        } catch (Exception p_e) {
        }
        return m_imgUri;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(1, getIntent());
        finish();
    }

    private void asd() {
        lstbmp.clear();
        String query = "SELECT * FROM " + dbTools.tbl_name + " WHERE " + dbTools.fld_subjectname + " = '" + gettitle.toString() + "' ";
        //cursor = dbTools.getsingleDatasub(gettitle);

        cursor = dbTools.executeQuery(query);
        if (cursor.moveToFirst()) {

            do {
                //  Toast.makeText(this, cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3)+cursor.getString(4)+cursor.getString(5)+cursor.getString(7), Toast.LENGTH_SHORT).show();
                lstbmp.add(res.StringToBitMap(cursor.getString(7)));
                lblgroupname.setText(cursor.getString(2));
                count = lstbmp.size();
            } while (cursor.moveToNext());
        }
        if (cameeraopen) {

            imgview.setImageBitmap(lstbmp.get(count - 1));
        } else {
            imgview.setImageBitmap(lstbmp.get(getindex));
        }
    }

    private class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (btnopencamera == view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);

            } else if (btnshowdetails == view) {
                forshowdetails();
            } else if (btnprev == view) {
                try {
                    count--;
                    imgview.setImageBitmap(lstbmp.get(count - 1));
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "This is last image", Toast.LENGTH_SHORT).show();
                    count = 0;
                }
            } else if (btnext == view) {

                try {
                    count++;
                    imgview.setImageBitmap(lstbmp.get(count - 1));
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "This is latest image", Toast.LENGTH_SHORT).show();
                    count = lstbmp.size() - 1;
                }
            }

        }
    }


}

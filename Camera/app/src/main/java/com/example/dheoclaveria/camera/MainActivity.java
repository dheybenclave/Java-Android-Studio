package com.example.dheoclaveria.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnopencamera,btnprev,btnext;
    ImageView imgview;

    Bitmap bmp;

    int count = 0 ;
    List<Bitmap> lstbmp = new ArrayList<Bitmap>();
    PackageManager packageManager;

    public static  final int CAMERA_REQUEST = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnopencamera = (Button) findViewById(R.id.btnopencamera);
        btnopencamera.setOnClickListener(new ButtonEvent());

        btnprev = (Button) findViewById(R.id.btnprev);
        btnprev.setOnClickListener(new ButtonEvent());

        btnext = (Button) findViewById(R.id.btnnext);
        btnext.setOnClickListener(new ButtonEvent());

        imgview = (ImageView) findViewById(R.id.imgview);

        packageManager = getPackageManager();
    }


    private class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (btnopencamera == view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);

            }
            else if (btnprev == view)
            {
                try {
                    count--;
                    imgview.setImageBitmap(lstbmp.get(count - 1));
                }
                catch (Exception ex){
                    Toast.makeText(MainActivity.this, "This is last iamge", Toast.LENGTH_SHORT).show();
                }
            }
            else if (btnext == view)
            {

                try {
                    count++;
                    imgview.setImageBitmap(lstbmp.get(count - 1));
                }
                catch (Exception ex){        Toast.makeText(MainActivity.this, "This is latest image", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int targetW = imgview.getWidth();
        int targetH = imgview.getHeight();
        InputStream inputStream;
        if((requestCode == CAMERA_REQUEST) && resultCode == Activity.RESULT_OK){
            try{

                bmp = (Bitmap) data.getExtras().get("data");
                lstbmp.add(bmp);
                imgview.setImageBitmap(bmp);

                for(int i  = 0 ; i < lstbmp.size();i++)
                {
                    Toast.makeText(getApplicationContext(), lstbmp.get(i).toString(), Toast.LENGTH_SHORT).show();
                }
                count = lstbmp.size();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
            catch (Exception ex){ex.getMessage(); }

        }

    }

}

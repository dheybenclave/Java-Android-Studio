package com.example.dheoclaveria.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.QRCode;

import java.io.File;
import java.io.FileOutputStream;

public class Main_Form extends AppCompatActivity {


    EditText txtinput;
    Button btngenerateqrcode, btnsaveqrcode;
    QRCode qrcode;
    ImageView img;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        qrcode = new QRCode();

        txtinput = (EditText) findViewById(R.id.txtinput);

        btngenerateqrcode = (Button) findViewById(R.id.btngenerateqrcode);
        btngenerateqrcode.setOnClickListener(new ButtonEvent());

        btnsaveqrcode = (Button) findViewById(R.id.btnsaveqrcode);
        btnsaveqrcode.setOnClickListener(new ButtonEvent());

        img = (ImageView) findViewById(R.id.img);
    }

    private class ButtonEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if(btngenerateqrcode ==v){
                qrcode.setData(txtinput.getText().toString());
                qrcode.setDataMode(QRCode.M_AUTO);
                qrcode.setVersion(10);
                qrcode.setEcl(qrcode.getEcl());
                qrcode.setX(3f);
                qrcode.setLeftMargin(15f);
                qrcode.setRightMargin(15f);
                qrcode.setTopMargin(15f);
                qrcode.setBottomMargin(15f);
                qrcode.setResolution(500);
                qrcode.setForeColor(AndroidColor.black);
                qrcode.setBackColor(AndroidColor.white);
                RectF bound = new RectF(60,60,0,0);
                bmp = Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_8888);
                Canvas canvas =  new Canvas(bmp);

                try{
                    qrcode.drawBarcode(canvas,bound);
                }
                catch (Exception ex){ex.getMessage();}
                img.setImageBitmap(bmp);

            }
            else if (btnsaveqrcode == v){
                String path = Environment.getExternalStorageDirectory()+"/myQRPic.jpg";
                File imglocation = new File(path);
                try{
                    FileOutputStream fos = new FileOutputStream(imglocation);
                    bmp.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    Saveit(path);
                }
                catch (Exception ex){ex.getMessage();}
            }

        }
    }

    public void Saveit(String path) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri uri =  Uri.fromFile(f);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }
}

package com.example.lenovo.thefirststepoflearning;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static android.os.Environment.getExternalStorageDirectory;

public class Main6Activity extends AppCompatActivity {
    private CanvassView canvassView;
    Bitmap b;
    FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        canvassView = (CanvassView) findViewById(R.id.canvasCanvas);
    }
    public void clearCanvas(View view){
        canvassView.reset();
    }
    public void saveFile(View view)
    {
        canvassView.setDrawingCacheEnabled(true);
        b = canvassView.getDrawingCache();
        b.setHasAlpha(true);
        Date date = new Date();
        String filename = String.valueOf(date);
        String[] newfile = filename.split(" ");
        filename = newfile[newfile.length-1];
        filename += newfile[1];
        filename += newfile[2];

        String [] time1 = newfile[3].split(":");
        filename += time1[0];
        filename += time1[1];
        filename += time1[2];

        try {
            String folderPath = Environment.getExternalStorageDirectory() + "/Drawings";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                File wallpaperDirectory = new File(folderPath);
                wallpaperDirectory.mkdirs();
            }
            File newFile = new File(folderPath, filename + ".png");
            newFile.createNewFile();
            FileOutputStream ostream = new FileOutputStream(newFile);
            b.compress(Bitmap.CompressFormat.PNG, 0, ostream);
            ostream.close();
            Toast.makeText(Main6Activity.this,newFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        }catch (IOException e){ e.printStackTrace();}


    }
    public void clickBlack(View view){
        canvassView.changeColor("black");
    }
    public void clickWhite(View view){
        canvassView.changeColor("white");
    }
    public void clickRed(View view){
        canvassView.changeColor("red");
    }
    public void clickYellow(View view){
        canvassView.changeColor("yellow");
    }
    public void clickBlue(View view){
        canvassView.changeColor("blue");
    }
    public void clickGreen(View view){
        canvassView.changeColor("green");
    }
    public void clickMagenta(View view){
        canvassView.changeColor("magenta");
    }
}

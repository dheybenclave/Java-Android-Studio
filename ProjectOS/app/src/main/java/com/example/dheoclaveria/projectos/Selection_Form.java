package com.example.dheoclaveria.projectos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dheoclaveria.projectos.Custom_GridView_Subjectname.isselectionmode;

public class Selection_Form extends Activity {

    Button btnback;
    GridView grdgroupselection;
    String[] fixediconname = {"book", "bookmark", "book open", "books", "compass", "diploma", "erase", "formula", "notepad", "pipette", "projection", "quill", "student hat"};
    int[] fixediconimage = {R.drawable.book, R.drawable.bookmark, R.drawable.bookopen, R.drawable.books, R.drawable.compass, R.drawable.diploma, R.drawable.erase, R.drawable.formula,
            R.drawable.notepad, R.drawable.pipette, R.drawable.projection, R.drawable.quill, R.drawable.studenthat};

    String[] color = {"dark","red","darkpink","blue","lightblue","bluegreen","green","yellow","orange","darkorange","darkbrown","silver"};
    private ArrayAdapter adapterlst;
    private String searchval = null;
    private List<GridView_Iconselection_Data> selection_datas = new ArrayList<>();
    Custom_GridView_Iconselection adapter_selection ;
    private Intent intentdata;
    public static boolean isiconselection ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection__form);

        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new ButtoneventonClick());

        grdgroupselection = (GridView) findViewById(R.id.grdgroupselection);
        grdgroupselection.setOnItemClickListener(new GridViewonItemClick());


        try {

            if(isiconselection) {
                adapter_selection = new Custom_GridView_Iconselection(this, selection_datas);
                grdgroupselection.setAdapter(adapter_selection);
                for (int i = 0; i < fixediconname.length; i++) {
                    selection_datas.add(new GridView_Iconselection_Data(String.valueOf(i), fixediconname[i].replace(" ", ""), fixediconname[i].replace(" ", ""), "default"));
                    grdgroupselection.setAdapter(adapter_selection);
                }
            }
            else
            {
                adapter_selection = new Custom_GridView_Iconselection(this, selection_datas);
                grdgroupselection.setAdapter(adapter_selection);
                for (int i = 0 ; i < color.length;i++)
                {
                    selection_datas.add(new GridView_Iconselection_Data(String.valueOf(i),color[i].replace(" ",""),"transparent",color[i].replace(" ","")));
                    grdgroupselection.setAdapter(adapter_selection);
                }
            }
        }
        catch (Exception ex) {
        }
    }

    private class ButtoneventonClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (btnback == view) {
                setResult(1, getIntent());
                finish();
            }

        }
    }

    private class GridViewonItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getApplicationContext(),fixediconimage[i] + color[i],Toast.LENGTH_SHORT).show();
            intentdata = getIntent();
            intentdata.putExtra("imagename", fixediconname[i].replace(" " ,""));
            intentdata.putExtra("color",color[i].replace(" ",""));
            isselectionmode = false;
            setResult(1, intentdata);
            finish();

        }
    }

    public void LoadSelection() {
        adapter_selection = new Custom_GridView_Iconselection(this, selection_datas);
        grdgroupselection.setAdapter(adapter_selection);

        try {

            if(isiconselection) {
                for (int i = 0; i < fixediconname.length; i++) {
                    selection_datas.add(new GridView_Iconselection_Data(String.valueOf(i), fixediconname[i].replace(" ", ""), fixediconname[i].replace(" ", ""), "default"));
                    grdgroupselection.setAdapter(adapter_selection);
                }
            }
            else
            {
                for (int i = 0 ; i < color.length;i++)
                {
                    selection_datas.add(new GridView_Iconselection_Data(String.valueOf(i),color[i].replace(" ",""),"transparent",color[i].replace(" ","")));
                    grdgroupselection.setAdapter(adapter_selection);
                }
            }
        }
        catch (Exception ex) {
        }
    }

}

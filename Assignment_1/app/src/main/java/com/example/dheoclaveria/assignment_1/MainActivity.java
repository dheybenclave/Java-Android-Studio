package com.example.dheoclaveria.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd, btndelete, btnshow;
    EditText txtinput;
    Spinner lstcountries;
    ListView lv;
    ArrayList<String> list1 = new ArrayList<String>();
    String selected = "";

    private int second = 0;
    private boolean running, isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){

                list1 = savedInstanceState.getStringArrayList("list1");
        }

        btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener( new MyButtonEventHandler());

        txtinput = (EditText) findViewById(R.id.txtinput);

        lstcountries = (Spinner) findViewById(R.id.lstcountries);
        lv = (ListView) findViewById(R.id.lvcountries);
        BindtoList();

        btndelete = (Button) findViewById(R.id.btndelete);
        btndelete.setOnClickListener( new MyButtonEventHandler());

        btnshow = (Button) findViewById(R.id.btnshow);
        btnshow.setOnClickListener( new MyButtonEventHandler());
    }



    public  void BindtoList()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list1);
        lstcountries.setAdapter(adapter);
        lv.setAdapter(adapter);


        lstcountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected = lstcountries.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "No Selected Country", Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lstcountries.setSelection(position);
            }
        });

    }
    private class MyButtonEventHandler implements View.OnClickListener{

        public void onClick (View v)
        {
            String getinput = txtinput.getText().toString();

            if(btnadd.isPressed()) {

                if ( getinput.trim().length() != 0) {
                    if(!list1.contains(getinput))
                    {
                        running =true;
                        list1.add(getinput.trim());
                        Toast.makeText(MainActivity.this, "Add Success", Toast.LENGTH_SHORT).show();
                    }else{ Toast.makeText(MainActivity.this,getinput + " is already exist!", Toast.LENGTH_SHORT).show();}
                } else {

                    Toast.makeText(MainActivity.this, "Please Fill the data", Toast.LENGTH_SHORT).show();
                }
            }
                else if(btndelete.isPressed()){
                if(list1.contains(selected))
                {
                    list1.remove(selected.toString());
                    Toast.makeText(MainActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                }
                else{ Toast.makeText(MainActivity.this, "No Selected Country", Toast.LENGTH_SHORT).show();}
            }
            else if(btnshow.isPressed()){

                Intent intent = new Intent(MainActivity.this, secondactivity.class);
                intent.putExtra("Value", txtinput.getText().toString());
                startActivity(intent);
                finish();
            }

            BindtoList();

            txtinput.setText("");
        }


    }


    @Override
    public void onSaveInstanceState(Bundle s) {
        super.onSaveInstanceState(s);
        s.putStringArrayList("list1",list1);
    }



}

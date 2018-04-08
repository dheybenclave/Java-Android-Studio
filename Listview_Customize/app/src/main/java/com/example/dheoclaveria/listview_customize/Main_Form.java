package com.example.dheoclaveria.listview_customize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main_Form extends AppCompatActivity {

    Button btnadd;
    ListView lstview;
    private List<Employee> employee = new ArrayList<Employee>();
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        lstview = (ListView) findViewById(R.id.lstcustomview);
        adapter = new CustomListAdapter(this,employee);
        btnadd = (Button) findViewById(R.id.btnadd) ;
        btnadd.setOnClickListener(new ButtonEvent());

    }

    public void addemployee(){

        lstview.setAdapter(adapter);
        Intent intent = new Intent(Main_Form.this, AddEmployee_Form.class);
        startActivityForResult(intent , 1);

    }

    protected void onActivityResult(int requestCode , int resultCode , Intent data){

        if(requestCode == 1){
            if(resultCode == 1){
                String id = data.getStringExtra("id");
                String fullname = data.getStringExtra("fullname");
                String position = data.getStringExtra("position");
                String address = data.getStringExtra("address");
                String mobile = data.getStringExtra("mobile");
                String email = data.getStringExtra("email");

                lstview.setAdapter(adapter);
                employee.add(new Employee(id , fullname , position, address , mobile , email));
            }
        }
    }


    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnadd == v) {
                addemployee();
            }
        }
    }
}

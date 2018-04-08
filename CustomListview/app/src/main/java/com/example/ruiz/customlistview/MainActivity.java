package com.example.ruiz.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnadd;
    private List<Employee> employees = new ArrayList<Employee>();
    ListView list;
    private CustomListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = (Button) findViewById(R.id.btnaddnew);
        list = (ListView) findViewById(R.id.mylist);
        adapter = new CustomListAdapter(this , employees);
        list.setAdapter(adapter);

        employees.add(new Employee("1" , "Ivy Rose Ruiz" , "Student" , "Tondo, Manila" , "09281469733" , "ruizohivyrose@yahoo.com"));

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent , 1);
            }
        });
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

                employees.add(new Employee(id , fullname , position, address , mobile , email));
            }
        }
    }

}

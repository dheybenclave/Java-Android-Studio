package com.example.dheoclaveria.customlistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
        lstview.setAdapter(adapter);



        btnadd = (Button) findViewById(R.id.btnadd) ;
        btnadd.setOnClickListener(new ButtonEvent());

    }

    public void addemployee(){

        employee.add(new Employee("1","Raymond Martinez","Programmer","navotas city","12312312312","pogi@yahoo.com"));
        employee.add(new Employee("2","Dheo claveria","mangtataho","navotas city","12312312312","dhey@yahoo.com"));
        employee.add(new Employee("3","eton kami","god of all","heaven","just pray","internet"));
        employee.add(new Employee("4","pogi","Programmer","navotas city","12312312312","pogi@yahoo.com"));
        employee.add(new Employee("5","Raymond Martinez","Programmer","navotas city","12312312312","pogi@yahoo.com"));
        employee.add(new Employee("6","Raymond Martinez","Programmer","navotas city","12312312312","pogi@yahoo.com"));
    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnadd == v) {
                addemployee();
            }
        }
    }
}

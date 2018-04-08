package com.example.dheoclaveria.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnGender;
    String fullname;
    EditText txtFullname;
    RadioButton optHawai;
    RadioButton optPepper;
    RadioButton optHam;
    RadioButton optSmall;
    RadioButton optMedium;
    RadioButton optLarge;
    CheckBox chkCheese;
    CheckBox chkMushroom;
    CheckBox chkGarlic;
    EditText txtAdds;
    String gender1;
    String flavor;
    String sizes;
    String cheese = "";
    String mushroom = "";
    String garlic = "";
    String adds1;

    ArrayList<String> genders = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFullname = (EditText) findViewById(R.id.txtFullname);
        spnGender = (Spinner) findViewById(R.id.SpnGender);
        optHawai = (RadioButton) findViewById(R.id.optHawai);
        optPepper = (RadioButton) findViewById(R.id.optPepper);
        optHam = (RadioButton) findViewById(R.id.optHam);
        optSmall = (RadioButton) findViewById(R.id.optSmall);
        optMedium = (RadioButton) findViewById(R.id.optMedium);
        optLarge = (RadioButton) findViewById(R.id.optLarge);
        chkCheese = (CheckBox) findViewById(R.id.chkCheese);
        chkMushroom = (CheckBox) findViewById(R.id.chkMushroom);
        chkGarlic = (CheckBox) findViewById(R.id.chkGarlic);
        txtAdds = (EditText) findViewById(R.id.txtAddreq);
        cheese = "";
        mushroom = "";
        garlic = "";
        BindToList();
    }

    public String getGender()
    {
        gender1 = spnGender.getSelectedItem().toString();
        return gender1;
    }
    public String getFlavor()
    {
        if(optHawai.isChecked())
        {
            flavor = optHawai.getText().toString();
        }
        else if(optPepper.isChecked())
        {
            flavor = optPepper.getText().toString();
        }
        else if(optHam.isChecked())
        {
            flavor = optHam.getText().toString();
        }
        return flavor;
    }
    public String getSize()
    {
        if(optSmall.isChecked())
        {
            sizes = optSmall.getText().toString();
        }
        else if(optMedium.isChecked())
        {
            sizes = optMedium.getText().toString();
        }
        else if(optLarge.isChecked())
        {
            sizes = optLarge.getText().toString();
        }
        return sizes;
    }
    public void BindToList()
    {
        String[] gender1 = {"Mr","Ms","Mrs"};
        ArrayAdapter<String> adapt1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gender1);
        spnGender.setAdapter(adapt1);

    }
    public void clickedCheese(View view){
        if(chkCheese.isChecked() != true) {
            cheese = "";
            Toast.makeText(MainActivity.this, cheese, Toast.LENGTH_SHORT).show();
        }
        else
        {
            cheese = chkCheese.getText().toString();
            Toast.makeText(MainActivity.this, cheese, Toast.LENGTH_SHORT).show();

        }
    }
    public void clickedMushroom(View view){
        if(chkMushroom.isChecked() != true) {
            mushroom = "";
            Toast.makeText(MainActivity.this, mushroom, Toast.LENGTH_SHORT).show();
        }
        else
        {
            mushroom = chkMushroom.getText().toString();
            Toast.makeText(MainActivity.this, mushroom, Toast.LENGTH_SHORT).show();
        }
    }
    public void clickedGarlic(View view){
        if(chkGarlic.isChecked() != true) {
            garlic = "";
            Toast.makeText(MainActivity.this, garlic, Toast.LENGTH_SHORT).show();
        }
        else
        {
            garlic = chkGarlic.getText().toString();
            Toast.makeText(MainActivity.this, garlic, Toast.LENGTH_SHORT).show();
        }

    }
    public void passdata(View view)
    {
       String finaladds = "";
        if(cheese != ""  && mushroom == "" && garlic == "")
        {
            finaladds = " with an additional cheese on top";
        }
        else if(cheese == "" && mushroom != "" && garlic == "")
        {
            finaladds = " with an additional mushroom on top";
        }
        else if(cheese == "" && mushroom == "" && garlic != "")
        {
            finaladds = " with an additional garlic on top";
        }
        else if(cheese != "" && mushroom != "" && garlic == "")
        {
            finaladds = " with an additional cheese and mushroom on top";
        }
        else if(cheese != "" && mushroom == "" && garlic != "")
        {
            finaladds = " with an additional cheese and garlic on top";
        }
        else if(cheese == "" && mushroom != "" && garlic != "")
        {
            finaladds = " with an additional mushroom and garlic on top";
        }
        else if(cheese != "" && mushroom != "" && garlic != "")
        {
            finaladds = " with an additional cheese, mushroom, and garlic on top";
        }
        else
        {
            finaladds = "";
        }
        String addreq = "";
        addreq = txtAdds.getText().toString();
        String addfinal = "";
        if(addreq.equals(" ") || addreq.equals(""))
        {
            addfinal = "";
        }
        else
        {
            addfinal = " with an additional request of " + addreq;
        }

        Intent passing = new Intent(MainActivity.this, Main2Activity.class);
        fullname = txtFullname.getText().toString();
        passing.putExtra("genders",getGender());
        passing.putExtra("fullname",fullname);
        passing.putExtra("flavors",getFlavor());
        passing.putExtra("sizes",getSize());
        passing.putExtra("addons",finaladds);
        passing.putExtra("requests",addfinal);
        startActivity(passing);

    }
}

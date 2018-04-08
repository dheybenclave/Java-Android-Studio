package com.example.dheoclaveria.assignment_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioButton rdhawaiian, rdpepperoni, rdhamcheese, rdsolo, rdmedium, rdlarge;
    RadioGroup rdgrp;
    String selectedflavor, selectedsize, addtionalrequest, genderd, fullname, finalsummary;
    String checkitems = "";
    CheckBox chkcheese, chkmushroom, chkgarlic, chk;
    Spinner lstgender;
    EditText txtcustomername, txtaddtionalrequest;
    Button btnproceed;
    ImageView imghawaiian, imgpepperoni, imghamcheese;

    ArrayList<String> lstcheckitems = new ArrayList<String>();
    Boolean ischecksize, ischecktoppings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (savedInstanceState != null) {
                selectedflavor = savedInstanceState.getString("flavor");
                selectedsize = savedInstanceState.getString("size");
                lstcheckitems = savedInstanceState.getStringArrayList("checkitems");
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
        }

        txtcustomername = (EditText) findViewById(R.id.txtinput);

        lstgender = (Spinner) findViewById(R.id.cmbgender);

        imghawaiian = (ImageView) findViewById(R.id.imghawaiian);
        imghawaiian.setOnClickListener(new ButtonEvent());
        imgpepperoni = (ImageView) findViewById(R.id.imghapepperoni);
        imgpepperoni.setOnClickListener(new ButtonEvent());
        imghamcheese = (ImageView) findViewById(R.id.imghamcheese);
        imghamcheese.setOnClickListener(new ButtonEvent());

        rdhawaiian = (RadioButton) findViewById(R.id.rdhawaiian);
        rdpepperoni = (RadioButton) findViewById(R.id.rdpepperoni);
        rdhamcheese = (RadioButton) findViewById(R.id.rdhamcheese);

        rdgrp = (RadioGroup) findViewById(R.id.rdgflavor);

        rdsolo = (RadioButton) findViewById(R.id.rdsolo);
        rdmedium = (RadioButton) findViewById(R.id.rdmedium);
        rdlarge = (RadioButton) findViewById(R.id.rdlarge);

        chkcheese = (CheckBox) findViewById(R.id.chkcheese);
        chkmushroom = (CheckBox) findViewById(R.id.chkmushroom);
        chkgarlic = (CheckBox) findViewById(R.id.chkgarlic);

        txtaddtionalrequest = (EditText) findViewById(R.id.txtaddrequest);

        btnproceed = (Button) findViewById(R.id.btnproceed);
        btnproceed.setOnClickListener(new ButtonEvent());


        BindtoList();
//        RadioButtonListner();
        CheckboxListner();
    }

    private void Submit() {
        radiobutton();
        checklist();
        addtionalrequest = txtaddtionalrequest.getText().toString();
        fullname = txtcustomername.getText().toString();


        try {

            if (fullname.trim().length() == 0) {
                Toast.makeText(MainActivity.this, "Please input your name.", Toast.LENGTH_SHORT).show();
            } else {

                if (checkitems == "" && addtionalrequest.trim().length() == 0) {
                    finalsummary = genderd + " " + fullname + " ordered a " + selectedsize + " " + selectedflavor + " pizza";
                } else if (addtionalrequest.trim().length() != 0 && checkitems != "") {
                    finalsummary = genderd + " " + fullname + " ordered a " + selectedsize + " " + selectedflavor + " pizza and additional " + checkitems + " toppings. Also with additional request " + addtionalrequest;
                } else if (checkitems != "" && addtionalrequest.trim().length() == 0) {
                    finalsummary = genderd + " " + fullname + " ordered a " + selectedsize + " " + selectedflavor + " pizza with additional " + checkitems + " toppings";
                } else if (addtionalrequest.trim().length() != 0 && checkitems == "") {
                    finalsummary = genderd + " " + fullname + " ordered a " + selectedsize + " " + selectedflavor + " pizza with additional request " + addtionalrequest;
                }

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("finalsummary", finalsummary);
                startActivity(intent);
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void checklist() {
        for (int i = 0; i < lstcheckitems.size(); i++) {
            if (lstcheckitems.size() == 2) {
                checkitems = lstcheckitems.get(0) + " and " + lstcheckitems.get(1);
            } else if (lstcheckitems.size() == 3) {
                checkitems = lstcheckitems.get(0) + ", " + lstcheckitems.get(1) + " and " + lstcheckitems.get(2);
            } else {
                checkitems = lstcheckitems.get(i);
            }
        }
    }

    public void CheckboxListner() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chk = (CheckBox) v;
                if ((chk.isChecked())) {
                    lstcheckitems.add(chk.getText().toString());
                } else {
                    lstcheckitems.remove(chk.getText().toString());
                }
                checkitems = "";
               checklist();
                if(checkitems!=""){Toast.makeText(MainActivity.this, checkitems, Toast.LENGTH_SHORT).show();}
            }
        };
        chkcheese.setOnClickListener(listener);
        chkmushroom.setOnClickListener(listener);
        chkgarlic.setOnClickListener(listener);
    }


    public void RadioButtonListner() {
//
//        selectedflavor = rdhawaiian.getText().toString();
//        selectedsize = rdsolo.getText().toString();
//
//        View.OnClickListener flavorlistener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                RadioButton rb = (RadioButton) v;
//                if (((RadioButton) v).isChecked()) {
//                    rb.getText();
//                    selectedflavor = rb.getText().toString();
//                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        rdhawaiian.setOnClickListener(flavorlistener);
//        rdpepperoni.setOnClickListener(flavorlistener);
//        rdhamcheese.setOnClickListener(flavorlistener);
//
//        View.OnClickListener sizelistener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                RadioButton rb = (RadioButton) v;
//                if (((RadioButton) v).isChecked()) {
//                    rb.getText();
//                    selectedsize = rb.getText().toString();
//                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        rdsolo.setOnClickListener(sizelistener);
//        rdmedium.setOnClickListener(sizelistener);
//        rdlarge.setOnClickListener(sizelistener);
    }

    public  void radiobutton()
    {
        if(rdhawaiian.isChecked() == true)
        { selectedflavor = rdhawaiian.getText().toString();}
        else if(rdpepperoni.isChecked() == true)
        {selectedflavor = rdpepperoni.getText().toString();}
        else if(rdhamcheese.isChecked() == true)
        { selectedflavor = rdhamcheese.getText().toString();}

        if(rdsolo.isChecked() == true)
        { selectedsize = rdsolo.getText().toString();}
        else if(rdmedium.isChecked() == true)
        {selectedsize = rdmedium.getText().toString();}
        else if(rdlarge.isChecked() == true)
        { selectedsize = rdlarge.getText().toString();}
    }

    private void BindtoList() {
        String[] gender = {"Mr", "Ms", "Mrs",};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gender);
        lstgender.setAdapter(adapter);
        genderd = lstgender.getSelectedItem().toString();
    }


    @Override
    public void onSaveInstanceState(Bundle s) {
        super.onSaveInstanceState(s);
        s.putString("flavor", selectedflavor);
        s.putString("size", selectedsize);
        s.putStringArrayList("checkitems", lstcheckitems);

    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnproceed == v) {
                Submit();
            } else if (imghawaiian == v) {
                rdhawaiian.setChecked(true);
                selectedflavor = rdhawaiian.getText().toString();
            } else if (imgpepperoni == v) {
                rdpepperoni.setChecked(true);
                selectedflavor = rdpepperoni.getText().toString();
            } else if (imghamcheese == v) {
                rdhamcheese.setChecked(true);
                selectedflavor = rdhamcheese.getText().toString();
            }
        }
    }

}

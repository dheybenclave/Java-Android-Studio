package com.example.dheoclaveria.listview_customize;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.dheoclaveria.listview_customize.R.id.txtaddid;

public class AddEmployee_Form extends AppCompatActivity {

    private List<Employee> employees = new ArrayList<Employee>();
    Intent dataIntent;
    EditText txtid ,txtfullname , txtaddress , txtposition , txtmobile, txtemail;
    Button btnsave;
    DialogInterface.OnClickListener dialoglistener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee__form);

        txtid = (EditText)findViewById(txtaddid);
        txtfullname = (EditText)findViewById(R.id.txtaddfullname);
        txtaddress = (EditText)findViewById(R.id.txtaddaddress);
        txtposition = (EditText)findViewById(R.id.txtaddposition);
        txtmobile = (EditText) findViewById(R.id.txtaddmobile);
        txtemail = (EditText) findViewById(R.id.txtemail);
        btnsave = (Button) findViewById(R.id.btnsave);

        dialoglistener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE :
                        SaveData();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        btnsave.setOnClickListener(new ButtonEvent());

    }

    private class ButtonEvent implements View.OnClickListener{
        public  void onClick(View v){
            AlertDialog.Builder builder = new AlertDialog.Builder(AddEmployee_Form.this);
            builder.setMessage("Do you want to save? ").setPositiveButton("Yes" , dialoglistener).setNegativeButton("No" , dialoglistener).show();
        }
    }
   public  void SaveData()
    {
        try {

            if(txtid.getText().toString().trim().length() == 0 || txtfullname.getText().toString().trim().length() == 0 || txtposition.getText().toString().trim().length() == 0||
                    txtaddress.getText().toString().trim().length() == 0 || txtmobile.getText().toString().trim().length() == 0 || txtemail.getText().toString().trim().length() == 0){
                Toast.makeText(AddEmployee_Form.this, "Please all data", Toast.LENGTH_SHORT).show();
            }
            else {
                dataIntent = getIntent();
                dataIntent.putExtra("id", txtid.getText().toString());
                dataIntent.putExtra("fullname", txtfullname.getText().toString());
                dataIntent.putExtra("position", txtposition.getText().toString());
                dataIntent.putExtra("address", txtaddress.getText().toString());
                dataIntent.putExtra("mobile", txtmobile.getText().toString());
                dataIntent.putExtra("email", txtemail.getText().toString());
                setResult(1, dataIntent);
                finish();
                Toast.makeText(AddEmployee_Form.this, "Successfully Added!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {

        }

    }

}

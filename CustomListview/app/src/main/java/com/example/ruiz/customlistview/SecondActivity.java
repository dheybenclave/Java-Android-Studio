package com.example.ruiz.customlistview;

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

public class SecondActivity extends AppCompatActivity {

    private List<Employee> employees = new ArrayList<Employee>();
    Intent dataIntent;
    EditText id ,name , address , position , mobile, email;
    Button  btnsave;
    DialogInterface.OnClickListener dialoglistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        id = (EditText)findViewById(R.id.lblID);
        name = (EditText)findViewById(R.id.lblfullname);
        address = (EditText)findViewById(R.id.lbladdress);
        position = (EditText)findViewById(R.id.lblposition);
        mobile = (EditText) findViewById(R.id.lblmobile);
        email = (EditText) findViewById(R.id.lblemail);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setMessage("Do you want to save? ").setPositiveButton("Yes" , dialoglistener).setNegativeButton("No" , dialoglistener).show();
        }
    }
    public  void SaveData()
    {
        /*String ids = id.getText().toString();
        String name1 = name.getText().toString();
        String pos = position.getText().toString();
        String addr = address.getText().toString();
        if(ids.trim().length() != 0 && name1.trim().length() != 0 && pos.trim().length() != 0 && addr.trim().length() != 0) {*/
            try {

                dataIntent = getIntent();
                dataIntent.putExtra("id", id.getText().toString());
                dataIntent.putExtra("fullname", name.getText().toString());
                dataIntent.putExtra("position", position.getText().toString());
                dataIntent.putExtra("address", address.getText().toString());
                dataIntent.putExtra("mobile", mobile.getText().toString());
                dataIntent.putExtra("email", email.getText().toString());
                setResult(1, dataIntent);
                finish();
                Toast.makeText(SecondActivity.this, "Successfully Added!", Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {

            }

    }

}

package com.example.dheoclaveria.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Button btnbButton, btnLogin;
    EditText txtName, txtPhonenumber, txtEmail;
    Spinner lstcounttry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnbButton = (Button) findViewById(R.id.btngreet);
        txtName = (EditText) findViewById(R.id.txtname);

        btnLogin = (Button) findViewById(R.id.btnlogin);
        txtPhonenumber = (EditText) findViewById(R.id.txtnumber);
        txtEmail = (EditText) findViewById(R.id.txtemail);

        lstcounttry = (Spinner) findViewById(R.id.lstconuntries);
        bindValuelist();



        btnLogin.setOnClickListener( new MyButtonEventHandler());



    btnbButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String name1 = txtName.getText().toString();

            Toast.makeText(MainActivity.this, "Hello " + name1, Toast.LENGTH_LONG).show();
        }
    });
    }


    private void bindValuelist()
    {
         String[] countries ={"use","phil","china","japan"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries);

        lstcounttry.setAdapter(adapter);


        lstcounttry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = lstcounttry.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, selected, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    private class MyButtonEventHandler implements View.OnClickListener{

        public void onClick (View v)
        {
            final String DEFAULT_PHONENUMBER = "phonenumber";
            final String DEFAULT_EMAIL = "email";

            String pnum = txtPhonenumber.getText().toString();
            String email = txtEmail.getText().toString();

            if( !pnum.equals("") && !email.equals("") )
            {
                Toast.makeText(MainActivity.this, "your number  is :" +  pnum +"and your email is : " +email, Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Nothing...", Toast.LENGTH_LONG).show();
            }
        }
    }


}

package com.example.ruiz.newactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSend;
    EditText txtText;
   RadioButton choice1 , choice2 , choice3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button) findViewById(R.id.btnSend);
        txtText = (EditText) findViewById(R.id.txtText);

        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice3 = (RadioButton) findViewById(R.id.choice3);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.putExtra("greet", txtText.getText().toString());

                startActivity(intent);
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton rb = (RadioButton) v;
                if(((RadioButton) v).isChecked())
                {

                    rb.getText();
                    Toast.makeText(MainActivity.this , rb.getText() , Toast.LENGTH_SHORT).show();
                }
            }
        };
        choice1.setOnClickListener(listener);
        choice2.setOnClickListener(listener);
        choice3.setOnClickListener(listener);
    }

}

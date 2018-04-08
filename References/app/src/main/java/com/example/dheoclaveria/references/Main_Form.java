package com.example.dheoclaveria.references;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main_Form extends AppCompatActivity {

    Button btnopenpref,btnloadepref;
    TextView txt1,txt2,txt3;
    SharedPreferences sp ;
    boolean isPlaying;
    String message,songname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        btnopenpref = (Button) findViewById(R.id.btnopenpref);
        btnopenpref.setOnClickListener(new ButtonEvent());

        btnloadepref= (Button) findViewById(R.id.btnloadepref);
     btnloadepref.setOnClickListener(new ButtonEvent());


        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
    }



    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if(btnopenpref == v){
                Intent intent = new Intent(Main_Form.this,MyPreference.class);
                startActivity(intent);
            }
            else if (btnloadepref == v){

                sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
                isPlaying = sp.getBoolean("mymusics",false);
                message = sp.getString("myMessage","");
                songname = sp.getString("songs","");
                txt1.setText(isPlaying? "true":"false");
                txt2.setText(message);
                txt3.setText(songname);



            }
        }
    }

}

package com.example.lenovo.thefirststepoflearning;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    DbToolsLetters dbToolsLetters;
    Cursor cursor;
    TextView txtQuiz;
    int high = 0;
    TextView txtScore;
    int presentedscored;
    EditText txtC,txtF,txtG,txtI,txtL,txtP,txtR,txtT,txtW,txtZ;
    ArrayList<String> username = new ArrayList<String>();
    ArrayList<String> score = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        txtQuiz = (TextView) findViewById(R.id.txtQuiz);
        txtScore = (TextView) findViewById(R.id.txtScore);
        dbToolsLetters = new DbToolsLetters(getApplicationContext());
        txtC = (EditText) findViewById(R.id.txtC);
        txtF = (EditText) findViewById(R.id.txtF);
        txtG = (EditText) findViewById(R.id.txtG);
        txtI = (EditText) findViewById(R.id.txtI);
        txtL = (EditText) findViewById(R.id.txtL);
        txtP = (EditText) findViewById(R.id.txtP);
        txtR = (EditText) findViewById(R.id.txtR);
        txtT = (EditText) findViewById(R.id.txtT);
        txtW = (EditText) findViewById(R.id.txtW);
        txtZ = (EditText) findViewById(R.id.txtZ);
        initialize();
    }
    public void initialize()
    {
        try {
            LoadData();
            txtQuiz.setText(username.get(0));
            txtScore.setText(score.get(0));
            for (int i = 0; i < score.size(); i++) {
                if (Integer.valueOf(score.get(i)) >= high) {
                    high = Integer.valueOf(score.get(i));
                    txtScore.setText(String.valueOf(high));
                    txtQuiz.setText(username.get(i));
                }
            }
        }catch (Exception e){}
    }
    protected void onRestart() {
        LoadData();
        initialize();
        super.onRestart();
        LoadData();
        initialize();
    }

    @Override
    protected void onResume() {
        LoadData();
        initialize();
        super.onResume();
        LoadData();
        initialize();
    }
    public void Pass(View view){
        if(txtC.getText().toString().toLowerCase().equals("c"))
        {
            presentedscored++;
        }
        if(txtF.getText().toString().toLowerCase().equals("f"))
        {
            presentedscored++;
        }
        if(txtG.getText().toString().toLowerCase().equals("g"))
        {
            presentedscored++;
        }
        if(txtI.getText().toString().toLowerCase().equals("i"))
        {
            presentedscored++;
        }
        if(txtL.getText().toString().toLowerCase().equals("l"))
        {
            presentedscored++;
        }
        if(txtP.getText().toString().toLowerCase().equals("p"))
        {
            presentedscored++;
        }
        if(txtR.getText().toString().toLowerCase().equals("r"))
        {
            presentedscored++;
        }
        if(txtT.getText().toString().toLowerCase().equals("t"))
        {
            presentedscored++;
        }
        if(txtW.getText().toString().toLowerCase().equals("w"))
        {
            presentedscored++;
        }
        if(txtZ.getText().toString().toLowerCase().equals("z"))
        {
            presentedscored++;
        }
        String finalscore = String.valueOf(presentedscored);
        Intent intent = new Intent(Quiz.this,Login.class);
        intent.putExtra("score",finalscore);
        intent.putExtra("type","letters");
        presentedscored = 0;
        startActivity(intent);
    }
    public void LoadData(){
        try {
            username.clear();
            score.clear();
            ArrayList<String> user = new ArrayList<String>();
            ArrayList<String> sc = new ArrayList<String>();
            cursor = dbToolsLetters.getAllUsers();
            if (cursor.moveToFirst()) {
                do {
                    user.add(cursor.getString(1));
                    sc.add(cursor.getString(2));

                } while (cursor.moveToNext());
            } else {
                Toast.makeText(getApplicationContext(), "No record", Toast.LENGTH_LONG).show();
            }
            username.addAll(user);
            score.addAll(sc);
        }catch (Exception e){}

    }

}

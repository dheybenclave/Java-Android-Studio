package com.example.lenovo.thefirststepoflearning;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizNumber extends AppCompatActivity {

    TextView txtNumHighName;
    DbToolsNumber dbToolsNumber;
    TextView txtNumHighScore;
    Button btnSaveNum;
    int high;
    Cursor cursor;
    TextView txtNum1,txtNum2,txtNum3,txtNum4,txtNum5;
    ArrayList<String> username = new ArrayList<String>();
    ArrayList<String> scores = new ArrayList<String>();
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_number);
        txtNumHighName = (TextView) findViewById(R.id.txtNumHighName);
        txtNumHighScore = (TextView) findViewById(R.id.txtNumHighScore);
        dbToolsNumber = new DbToolsNumber(getApplicationContext());
        btnSaveNum = (Button) findViewById(R.id.btnSaveNum);
        txtNum1 = (TextView) findViewById(R.id.txtNum1);
        txtNum2 = (TextView) findViewById(R.id.txtNum2);
        txtNum3 = (TextView) findViewById(R.id.txtNum3);
        txtNum4 = (TextView) findViewById(R.id.txtNum4);
        txtNum5 = (TextView) findViewById(R.id.txtNum5);
        try {
            initialize();
        }catch (Exception e){}
    }

    public void initialize()
    {
        try {
            LoadData();
            txtNumHighName.setText(username.get(0));
            txtNumHighScore.setText(scores.get(0));
            for (int i = 0; i < scores.size(); i++) {
                if (Integer.valueOf(scores.get(i)) >= high) {
                    high = Integer.valueOf(scores.get(i));
                    txtNumHighName.setText(username.get(i));
                    txtNumHighScore.setText(String.valueOf(high));
                }
            }
        }catch (Exception e){
            txtNumHighName.setText("High Score:");
            txtNumHighScore.setText("0");
        }
    }
    public void LoadData(){
        try {
            username.clear();
            scores.clear();
            ArrayList<String> user = new ArrayList<String>();
            ArrayList<String> sc = new ArrayList<String>();
            cursor = dbToolsNumber.getAllUsers();
            if (cursor.moveToFirst()) {
                do {
                    user.add(cursor.getString(1));
                    sc.add(cursor.getString(2));

                } while (cursor.moveToNext());
            } else {
                Toast.makeText(getApplicationContext(), "No record", Toast.LENGTH_LONG).show();
            }
            username.addAll(user);
            scores.addAll(sc);
        }catch (Exception e){}

    }

    public void ResultNum(View view)
    {
        if(txtNum1.getText().toString().toLowerCase().equals("d"))
        {
            score++;
        }
        if(txtNum2.getText().toString().toLowerCase().equals("c"))
        {
            score++;
        }
        if(txtNum3.getText().toString().toLowerCase().equals("a"))
        {
            score++;
        }
        if(txtNum4.getText().toString().toLowerCase().equals("b"))
        {
            score++;
        }
        if(txtNum5.getText().toString().toLowerCase().equals("e"))
        {
            score++;
        }
        Intent intent = new Intent(QuizNumber.this,Login.class);
        String finalscore =String.valueOf(score);
        intent.putExtra("type","number");
        intent.putExtra("scorenum",finalscore);
        txtNum1.setText("");
        txtNum2.setText("");
        txtNum3.setText("");
        txtNum4.setText("");
        txtNum5.setText("");
        score = 0;
        startActivity(intent);
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
}

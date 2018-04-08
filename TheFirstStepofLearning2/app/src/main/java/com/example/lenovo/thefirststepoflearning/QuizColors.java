package com.example.lenovo.thefirststepoflearning;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizColors extends AppCompatActivity {

    EditText txtColorWhite,txtColorBlack,txtColorRed,txtColorBlue,txtColorOrange,txtColorPurple,txtColorGreen,txtColorYellow;
    TextView txtColHighName;
    DbToolsColor dbToolsColor;
    TextView txtColHighScore;
    Button btnSaveCol;
    int high;
    Cursor cursor;
    ArrayList<String> username = new ArrayList<String>();
    ArrayList<String> scores = new ArrayList<String>();
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_color);
        txtColHighName = (TextView) findViewById(R.id.txtColHighName);
        txtColHighScore = (TextView) findViewById(R.id.txtColHighScore);
        dbToolsColor = new DbToolsColor(getApplicationContext());
        btnSaveCol = (Button) findViewById(R.id.btnSaveCol);
        txtColorBlack = (EditText) findViewById(R.id.txtColorBlack);
        txtColorWhite = (EditText) findViewById(R.id.txtColorWhite);
        txtColorRed = (EditText) findViewById(R.id.txtColorRed);
        txtColorBlue = (EditText) findViewById(R.id.txtColorBlue);
        txtColorOrange = (EditText) findViewById(R.id.txtColorOrange);
        txtColorPurple = (EditText) findViewById(R.id.txtColorPurple);
        txtColorGreen = (EditText) findViewById(R.id.txtColorGreen);
        txtColorYellow = (EditText) findViewById(R.id.txtColorYellow);
        try {
            initialize();
        }catch (Exception e){}
    }
    public void initialize()
    {
        LoadData();
        try {


            txtColHighName.setText(username.get(0));
            txtColHighScore.setText(scores.get(0));
        }
        catch (Exception e)
        {
            txtColHighName.setText("Highscore: ");
            txtColHighScore.setText("0");
        }
        for(int i =0; i < scores.size();i++)
        {
            if(Integer.valueOf(scores.get(i)) >= high)
            {
                high = Integer.valueOf(scores.get(i));
                txtColHighName.setText(username.get(i));
                txtColHighScore.setText(String.valueOf(high));
            }
        }
    }
    public void ResultColor(View view)
    {
        if(txtColorYellow.getText().toString().toLowerCase().equals("yellow"))
        {
            score++;
        }
        if(txtColorGreen.getText().toString().toLowerCase().equals("green"))
        {
            score++;
        }
        if(txtColorOrange.getText().toString().toLowerCase().equals("orange"))
        {
            score++;
        }
        if(txtColorPurple.getText().toString().toLowerCase().equals("purple"))
        {
            score++;
        }
        if(txtColorRed.getText().toString().toLowerCase().equals("red"))
        {
            score++;
        }
        if(txtColorBlue.getText().toString().toLowerCase().equals("blue"))
        {
            score++;
        }
        if(txtColorWhite.getText().toString().toLowerCase().equals("white"))
        {
            score++;
        }
        if(txtColorBlack.getText().toString().toLowerCase().equals("black"))
        {
            score++;
        }
        txtColorBlack.setText("");
        txtColorWhite.setText("");
        txtColorRed.setText("");
        txtColorBlue.setText("");
        txtColorGreen.setText("");
        txtColorYellow.setText("");
        txtColorPurple.setText("");
        txtColorOrange.setText("");
        Intent intent = new Intent(QuizColors.this,Login.class);
        String finalscore =String.valueOf(score);
        intent.putExtra("type","color");
        intent.putExtra("scorecol",finalscore);
        score = 0;
        startActivity(intent);
    }
    public void LoadData(){
        try {
            username.clear();
            scores.clear();
            ArrayList<String> user = new ArrayList<String>();
            ArrayList<String> sc = new ArrayList<String>();
            cursor = dbToolsColor.getAllUsers();
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

    @Override
    protected void onResume() {
        LoadData();
        initialize();
        super.onResume();
        LoadData();
        initialize();
    }

    @Override
    protected void onRestart() {
        LoadData();
        initialize();
        super.onRestart();
        LoadData();
        initialize();
    }
}

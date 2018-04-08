package com.example.lenovo.thefirststepoflearning;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    DbToolsLetters dbToolsLetters;
    DbToolsNumber dbToolsNumber;
    DbToolsColor dbToolsColor;
    Button btnLogin;
    Cursor cursor;
    DbToolsShape dbToolsShapes;
    String score;
    String user;
    ArrayList<String> username;
    TextView txtScore;
    String type;
    EditText txtUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtScore = (TextView) findViewById(R.id.txtScore);
        dbToolsLetters = new DbToolsLetters(getApplicationContext());
        dbToolsNumber = new DbToolsNumber(getApplicationContext());
        dbToolsShapes = new DbToolsShape(getApplicationContext());
        dbToolsColor = new DbToolsColor(getApplicationContext());
        username = new ArrayList<String>();
        type = getIntent().getStringExtra("type");
        if(type.equals("letters")) {
            String scored = getIntent().getStringExtra("score");
            score = scored;
            if(Integer.valueOf(score) <= 3) {
                txtScore.setText("Awww! Betterluck Next Time! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) <=7)
            {
                txtScore.setText("Not Bad! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) >=8)
            {
                txtScore.setText("Wow! You scored " + score + "!");
            }
        }
        else if(type.equals("shape"))
        {
            String scored = getIntent().getStringExtra("scoreshape");
            score = scored;
            if(Integer.valueOf(score) <= 2) {
                txtScore.setText("Awww! Betterluck Next Time! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) <=4)
            {
                txtScore.setText("Not Bad! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) >4)
            {
                txtScore.setText("Wow! You scored " + score + "!");
            }
        }
        else if(type.equals("color"))
        {
            String scored = getIntent().getStringExtra("scorecol");
            score = scored;
            if(Integer.valueOf(score) <= 3) {
                txtScore.setText("Awww! Betterluck Next Time! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) <=6)
            {
                txtScore.setText("Not Bad! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) >6)
            {
                txtScore.setText("Wow! You scored " + score + "!");
            }
        }
        else if(type.equals("number"))
        {
            String scored = getIntent().getStringExtra("scorenum");
            score = scored;
            if(Integer.valueOf(score) <= 2) {
                txtScore.setText("Awww! Betterluck Next Time! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) <=4)
            {
                txtScore.setText("Not Bad! You scored " + score + "!");
            }
            else if(Integer.valueOf(score) ==5)
            {
                txtScore.setText("Wow! You scored " + score + "!");
            }
        }


    }
    public void LoadData(){
        ArrayList<String> user = new ArrayList<String>();
        cursor = dbToolsLetters.getAllUsers();
        if(cursor.moveToFirst()){
            do {
                user.add(cursor.getString(1));

            }while(cursor.moveToNext());
        }
        else {
            Toast.makeText(getApplicationContext(), "No record", Toast.LENGTH_LONG).show();
        }
        username.addAll(user);

    }
    public void Login(View view)
    {
        if(type.equals("letters")) {
            InsertUser();
        }
        else if(type.equals("number"))
        {
            InsertUserNumber();
        }
        else if(type.equals("color"))
        {
            InsertUserColor();
        }
        else if(type.equals("shape"))
        {
            InsertUserShape();
        }
        this.finish();
    }
    public void InsertUser(){

        user = txtUsername.getText().toString();
        dbToolsLetters.insertUser(user,score);
    }
    public void InsertUserNumber(){
        user = txtUsername.getText().toString();
        dbToolsNumber.insertUser(user,score);

    }
    public void InsertUserColor(){
        user = txtUsername.getText().toString();
        dbToolsColor.insertUser(user,score);

    }
    public void InsertUserShape(){
        user = txtUsername.getText().toString();
        dbToolsShapes.insertUser(user,score);
    }
}

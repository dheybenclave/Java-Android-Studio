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

public class QuizShapes extends AppCompatActivity {
    EditText txtStar,txtCircle,txtRectangle,txtSquare,txtHeart,txtTriangle;
    TextView txtShapeHighName;
    DbToolsShape dbToolsShapes;
    TextView txtShapeHighScore;
    Button btnSaveShape;
    int high;
    Cursor cursor;
    ArrayList<String> username = new ArrayList<String>();
    ArrayList<String> scores = new ArrayList<String>();
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_shapes);
        txtShapeHighName = (TextView) findViewById(R.id.txtShapeHighName);
        txtShapeHighScore = (TextView) findViewById(R.id.txtShapeHighScore);
        dbToolsShapes = new DbToolsShape(getApplicationContext());
        btnSaveShape = (Button) findViewById(R.id.btnSaveShape);
        txtStar = (EditText) findViewById(R.id.txtStar);
        txtCircle = (EditText) findViewById(R.id.txtCircle);
        txtSquare = (EditText) findViewById(R.id.txtSquare);
        txtRectangle = (EditText) findViewById(R.id.txtRectangle);
        txtTriangle = (EditText) findViewById(R.id.txtTriangle);
        txtHeart = (EditText) findViewById(R.id.txtHeart);

        try {
            initialize();
        }catch (Exception e){}
    }
    public void initialize()
    {
        LoadData();
        try {
            txtShapeHighName.setText(username.get(0));
            txtShapeHighScore.setText(scores.get(0));
        }catch (Exception e)
        {
            txtShapeHighName.setText("Highscore: ");
            txtShapeHighScore.setText("0");
        }
        for(int i =0; i < scores.size();i++)
        {
            if(Integer.valueOf(scores.get(i)) >= high)
            {
                high = Integer.valueOf(scores.get(i));
                txtShapeHighName.setText(username.get(i));
                txtShapeHighScore.setText(String.valueOf(high));
            }
        }
    }
    public void ResultShape(View view)
    {
        if(txtHeart.getText().toString().toLowerCase().equals("heart"))
        {
            score++;
        }
        if(txtTriangle.getText().toString().toLowerCase().equals("triangle"))
        {
            score++;
        }
        if(txtCircle.getText().toString().toLowerCase().equals("circle"))
        {
            score++;
        }
        if(txtSquare.getText().toString().toLowerCase().equals("square"))
        {
            score++;
        }
        if(txtRectangle.getText().toString().toLowerCase().equals("rectangle"))
        {
            score++;
        }
        if(txtStar.getText().toString().toLowerCase().equals("star"))
        {
            score++;
        }
        Intent intent = new Intent(QuizShapes.this,Login.class);
        String finalscore =String.valueOf(score);
        intent.putExtra("type","shape");
        intent.putExtra("scoreshape",finalscore);
        txtHeart.setText("");
        txtStar.setText("");
        txtRectangle.setText("");
        txtSquare.setText("");
        txtCircle.setText("");
        txtTriangle.setText("");
        score =0;
        startActivity(intent);
    }

    @Override
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

    public void LoadData(){
        try {
            username.clear();
            scores.clear();
            ArrayList<String> user = new ArrayList<String>();
            ArrayList<String> sc = new ArrayList<String>();
            cursor = dbToolsShapes.getAllUsers();
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
}

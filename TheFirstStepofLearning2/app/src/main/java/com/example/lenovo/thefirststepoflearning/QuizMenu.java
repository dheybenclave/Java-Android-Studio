package com.example.lenovo.thefirststepoflearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuizMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
    }
    public void QuizLetter(View view)
    {
        Intent intent = new Intent(QuizMenu.this,Quiz.class);
        startActivity(intent);
    }
    public void QuizNumber(View view)
    {
        Intent intent = new Intent(QuizMenu.this,QuizNumber.class);
        startActivity(intent);
    }
    public void QuizColor(View view)
    {
        Intent intent = new Intent(QuizMenu.this,QuizColors.class);
        startActivity(intent);
    }
    public void QuizShape(View view)
    {
        Intent intent = new Intent(QuizMenu.this,QuizShapes.class);
        startActivity(intent);
    }
}

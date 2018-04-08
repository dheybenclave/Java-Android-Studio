package com.example.lenovo.thefirststepoflearning;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public interface OnBackPressedListener {
        public void doBack();
    }
    @Override
    public void onResume(){
        super.onResume();
        try {
            if (SwipeAdapter.mediaPlayer.isPlaying()) {
                SwipeAdapter.mediaPlayer.stop();
                SwipeAdapter.mediaPlayer = null;
            }
        }catch (Exception ex){}
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    public void letters(View view)
    {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
    public void numbers(View view)
    {
        Intent intent = new Intent(MainActivity.this,Main3Activity.class);
        startActivity(intent);
    }
    public void colors(View view)
    {
        Intent intent = new Intent(MainActivity.this,Main4Activity.class);
        startActivity(intent);
    }
    public void rhymes (View view)
    {
        Intent intent = new Intent(MainActivity.this,Main5Activity.class);
        startActivity(intent);
    }
    public void login(View view)
    {
        Intent intent = new Intent(MainActivity.this, QuizMenu.class);
        startActivity(intent);
    }
    public void painting(View view)
    {
        Intent intent = new Intent(MainActivity.this,Main6Activity.class);
        startActivity(intent);
    }
    public void shapes(View view)
    {
        Intent intent = new Intent(MainActivity.this,Shapes.class);
        startActivity(intent);
    }
}

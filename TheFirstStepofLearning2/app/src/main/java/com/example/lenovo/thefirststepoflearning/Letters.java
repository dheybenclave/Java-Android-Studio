package com.example.lenovo.thefirststepoflearning;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Letters extends Activity{

    public static MediaPlayer mediaPlayer;
    Intent intent;
    Bundle bundle;
    ImageView imgpic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters);
        imgpic = (ImageButton) findViewById(R.id.imgLetters);
        bundle = new Bundle();
        getIntent();
        mediaPlayer = new MediaPlayer();
        String letter = getIntent().getStringExtra("a");
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer = null;
            }
        }catch (Exception e){}
        if(letter.equals("a"))
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.a1);

        }
        else if(letter.equals("b"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.b);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.b1);
        }
        else if(letter.equals("c"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.c);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.c1);
        }
        else if(letter.equals("d"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.d);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.d1);
        }
        else if(letter.equals("e"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.e);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.e1);
        }
        else if(letter.equals("f"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.f);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.f1);
        }
        else if(letter.equals("g"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.g);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.g1);
        }
        else if(letter.equals("h"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.h);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.h1);
        }
        else if(letter.equals("i"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.i);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.i1);
        }
        else if(letter.equals("j"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.j);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.j1);
        }
        else if(letter.equals("k"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.k);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.k1);
        }
        else if(letter.equals("l"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.l);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.l1);
        }
        else if(letter.equals("m"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.m);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.m1);
        }
        else if(letter.equals("n"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.n);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.n1);
        }
        else if(letter.equals("o"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.o);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.o1);
        }
        else if(letter.equals("p"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.p);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.p1);
        }
        else if(letter.equals("q"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.q);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.q1);
        }
        else if(letter.equals("r"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.r);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.r1);
        }
        else if(letter.equals("s"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.s);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.s1);
        }
        else if(letter.equals("t"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.t);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.t1);
        }
        else if(letter.equals("u"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.u);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.u1);
        }
        else if(letter.equals("v"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.v);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.v1);
        }
        else if(letter.equals("w"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.w);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.w1);
        }
        else if(letter.equals("x"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.x);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.x1);
        }
        else if(letter.equals("y"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.y);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.y1);
        }
        else if(letter.equals("z"))
        {
            try{
                mediaPlayer.stop();
                mediaPlayer = null;
            }catch (Exception ex){}
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.z);
            mediaPlayer.start();
            imgpic.setImageResource(R.drawable.z1);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            mediaPlayer.stop();
            mediaPlayer = null;
            return true;
        }
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        try{
            mediaPlayer.stop();
            mediaPlayer = null;
        }catch (Exception e){}
        super.onBackPressed();
        try{
            mediaPlayer.stop();
            mediaPlayer = null;
        }catch (Exception e){}
    }
}

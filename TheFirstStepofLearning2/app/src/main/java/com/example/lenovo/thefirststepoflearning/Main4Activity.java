package com.example.lenovo.thefirststepoflearning;

import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Locale;

public class Main4Activity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    MediaPlayer mediaPlayer;
    public TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mediaPlayer = new MediaPlayer();
        tts = new TextToSpeech(this, this);
    }
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //buttonSpeak.setEnabled(true);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
    public void colorblue(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.blue);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void colorred(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.red);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void coloryellow(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yellow);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void colororange(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.orange);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void colorbrown(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.brown);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void colorgreen(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.green);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void colorpurple(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.purple);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void colorwhite(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.white);
            mediaPlayer.start();
        }catch (Exception e){}
    }
    public void colorblack(View view) {

        try {
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.black);
            mediaPlayer.start();
        }catch (Exception e){}
    }
}

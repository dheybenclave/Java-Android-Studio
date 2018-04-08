package com.example.lenovo.thefirststepoflearning;

import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Locale;

public class Shapes extends AppCompatActivity implements TextToSpeech.OnInitListener{

    MediaPlayer mediaPlayer;
    public TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);
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
    public void star(View view) {

        tts.speak("star", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void circle(View view) {

        tts.speak("circle", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void square(View view) {

        tts.speak("square", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void rectangle(View view) {

        tts.speak("rectangle", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void heart(View view) {

        tts.speak("heart", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void triangle(View view) {

        tts.speak("triangle", TextToSpeech.QUEUE_FLUSH, null);
    }
}

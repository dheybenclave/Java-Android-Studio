package com.example.lenovo.thefirststepoflearning;

import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Locale;

public class Main3Activity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    MediaPlayer mediaPlayer;
    public TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tts = new TextToSpeech(this, this);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tenlittle);
        mediaPlayer.start();
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

    public void number1(View view) {

        tts.speak("one", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number2(View view) {

        tts.speak("two", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number3(View view) {

        tts.speak("three", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number4(View view) {

        tts.speak("four", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number5(View view) {

        tts.speak("five", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number6(View view) {

        tts.speak("six", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number7(View view) {

        tts.speak("seven", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number8(View view) {

        tts.speak("eight", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number9(View view) {

        tts.speak("nine", TextToSpeech.QUEUE_FLUSH, null);
    }
    public void number10(View view) {

        tts.speak("ten", TextToSpeech.QUEUE_FLUSH, null);
    }
}

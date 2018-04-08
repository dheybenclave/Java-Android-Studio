package com.example.lenovo.thefirststepoflearning;

import android.content.Intent;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    public static MediaPlayer mediaPlayer;
    public TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tts = new TextToSpeech(this, this);
        tts.setPitch(1);


        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alphabet);
        mediaPlayer.start();
    }
    public void clickA(View view)
    {

    }
    @Override
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
    public void letterA(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","a");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterB(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","b");
        mediaPlayer.stop();
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        mediaPlayer.start();
        super.onResume();

    }

    public void letterC(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","c");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterD(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","d");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterE(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","e");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterF(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","f");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterG(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","g");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterH(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","h");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterI(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","i");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterJ(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","j");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterK(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","k");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterL(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","l");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterM(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","m");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterN(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","n");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterO(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","o");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterP(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","p");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterQ(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","q");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterR(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","r");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterS(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","s");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterT(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","t");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterU(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","u");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterV(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","v");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterW(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","w");
        mediaPlayer.stop();
        startActivity(intent);
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

    public void letterX(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","x");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterY(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","y");
        mediaPlayer.stop();
        startActivity(intent);
    }
    public void letterZ(View view) {

        Intent intent = new Intent(Main2Activity.this, Letters.class);
        intent.putExtra("a","z");
        mediaPlayer.stop();
        startActivity(intent);
    }
}

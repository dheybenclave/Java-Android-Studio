package com.example.dheoclaveria.audiorecorder;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main_Form extends AppCompatActivity {

    ToggleButton tgbtnrecord, tgbtnplayback;

    public final String filename = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";
    public MediaRecorder recorder;
    public AudioManager audiomanager;
    public MediaPlayer player;

    boolean ischecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        tgbtnrecord = (ToggleButton) findViewById(R.id.tgbtnrecord);
        tgbtnrecord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tgbtnplayback.setEnabled(!b);
                onRecordPressed(b);

            }
        });
        tgbtnplayback = (ToggleButton) findViewById(R.id.tgbtnstopplayback);
        tgbtnplayback.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                tgbtnrecord.setEnabled(!b);
                onPlayPressed(b);
            }
        });

       // recorder = new MediaRecorder();
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audiomanager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
       // player = new MediaPlayer();


    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (tgbtnrecord == v) {
                // forrecord();
            } else if (tgbtnplayback == v) {

                //  forplayback();
            }
        }

    }

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {

            if (i == AudioManager.AUDIOFOCUS_LOSS) {
                audiomanager.abandonAudioFocus(afChangeListener);

                if (player.isPlaying()) {
                    stopplay();
                }
            }
        }
    };

    public void onRecordPressed(Boolean shouldrecord) {

        if (shouldrecord) {
            startrecord();
        } else {
            stoprecord();
        }

    }

    public void onPlayPressed(Boolean shouldplay) {

        if (shouldplay) {
            startplay();
        } else {
            stopplay();
        }

    }

    public void startrecord() {

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        recorder.setOutputFile(filename);

        try {

            recorder.prepare();

        } catch (Exception ex) {

            Toast.makeText(getApplicationContext(), "Could not start media record ! ", Toast.LENGTH_LONG).show();
        }
        recorder.start();
    }

    public void stoprecord() {

        if (null != recorder) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }

    }

    public void startplay() {
        player = new MediaPlayer();
        try {

            player.setDataSource(filename);
            player.prepare();
            player.start();

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Could not start media record ! ", Toast.LENGTH_LONG).show();
        }
    }

    public void stopplay() {

        if (null != player) {
            if (player.isPlaying()) {
                player.stop();
            }
        }
        player.release();
        player = null;

    }

    protected void onPause() {
        super.onPause();
        if (null != recorder) {
            recorder = null;
        }
        if (null != player) {
            player = null;
        }
    }
}



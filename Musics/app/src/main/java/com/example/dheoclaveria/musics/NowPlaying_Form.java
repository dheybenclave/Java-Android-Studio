package com.example.dheoclaveria.musics;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Random;

import static com.example.dheoclaveria.musics.Main_Form.adapter_messages;
import static com.example.dheoclaveria.musics.Main_Form.currentid;
import static com.example.dheoclaveria.musics.Main_Form.getartistname;
import static com.example.dheoclaveria.musics.Main_Form.getcurrentduration;
import static com.example.dheoclaveria.musics.Main_Form.getsongname;
import static com.example.dheoclaveria.musics.Main_Form.gettotalduration;
import static com.example.dheoclaveria.musics.Main_Form.mediaplayer;
import static com.example.dheoclaveria.musics.Main_Form.music_datas;
import static com.example.dheoclaveria.musics.Main_Form.musicidlist;
import static com.example.dheoclaveria.musics.Main_Form.musicselected;
import static com.example.dheoclaveria.musics.Main_Form.playingid;
import static com.example.dheoclaveria.musics.Main_Form.songcoloring;
import static com.example.dheoclaveria.musics.Main_Form.statusplay;

public class NowPlaying_Form extends AppCompatActivity {

    Button btnprevsong, btnnextsong;
    ToggleButton btnplaypause, btnshuffle,  btnrepeat;
    SeekBar skduration1;
    TextView lblsongname, lblartistname, lblcurrentdration, lblbotcurrentduration, lblbottotalduration, lbltotaldration;

    List<Music_Data> music_datas1 = music_datas;
    Cursor cursor;

    Music_Data musicselected1 = musicselected;

    Handler seekhandler = new Handler();

    MediaPlayer mediaPlayer = mediaplayer;
    AudioManager audioManager;

    Boolean ifprev, ifnext, ifallsongdone = false;
    public static Integer currentid1, songcoloring1;
    String currentduration, totalduration = "";
    int currentpassduration = 0;

    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdate();
            currentsong();
        }
    };
    private Intent intent;
    private Random r = new Random();
    private boolean ifshuffle, ifrepeat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing__form);
        currentid1 = currentid;
        songcoloring1 = songcoloring;

        lblsongname = (TextView) findViewById(R.id.lblsongname);
        lblsongname.setText(getsongname);

        lblartistname = (TextView) findViewById(R.id.lblartistname);
        lblartistname.setText(getartistname);

        lbltotaldration = (TextView) findViewById(R.id.lblduration);
        lbltotaldration.setText(gettotalduration);

        lblbottotalduration = (TextView) findViewById(R.id.lbltotalduration);
        lblbottotalduration.setText(gettotalduration);

        lblbotcurrentduration = (TextView) findViewById(R.id.lblcurrentduration);
        lblbotcurrentduration.setText(getcurrentduration);

        skduration1 = (SeekBar) findViewById(R.id.skduration1);

        btnshuffle = (ToggleButton) findViewById(R.id.btnsguffle);
        btnshuffle.setOnCheckedChangeListener(new Checklisten());

        btnprevsong = (Button) findViewById(R.id.btnprevsong);
        btnprevsong.setOnClickListener(new ButtonEvent());


        btnplaypause = (ToggleButton) findViewById(R.id.btnplaypuase);
        btnplaypause.setOnCheckedChangeListener(new Checklisten());

        btnrepeat = (ToggleButton) findViewById(R.id.btnrepeat);
        btnrepeat.setOnCheckedChangeListener(new Checklisten());


        if (statusplay) {
            btnplaypause.setChecked(true);
            btnplaypause.setBackgroundResource(R.drawable.play);
        } else {
            btnplaypause.setChecked(false);
            btnplaypause.setBackgroundResource(R.drawable.pause);
        }


        btnnextsong = (Button) findViewById(R.id.btnnextsong);
        btnnextsong.setOnClickListener(new ButtonEvent());

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        if (mediaPlayer != null) {
            seekUpdate();
        }
        skduration1.setOnTouchListener(new Ontouch());

    }

    @Override
    public void onBackPressed() {

        playingid = songcoloring;
        adapter_messages.notifyDataSetChanged();
        setResult(1, getIntent());
        finish();
        super.onBackPressed();

    }

    private class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (btnprevsong == v) {
                if (mediaPlayer != null) {
                    currentduration = totalduration;
                    currentid -= 2;
                    currentsong();
                    playingid = songcoloring;

                }
            } else if (btnnextsong == v) {

                if (mediaPlayer != null) {

                    currentduration = totalduration;
                    currentsong();
                    playingid = songcoloring;
                }
            } else if (btnshuffle == v) {

                if (mediaPlayer != null) {
                    currentduration = totalduration;
                    ifshuffle = true;
                    currentsong();
                    playingid = songcoloring;
                }
            }
        }
    }

    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();

        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buf
                .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }

    public void seekChange(View v) {

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            SeekBar skduration1 = (SeekBar) v;
            mediaplayer.seekTo(skduration1.getProgress());
        }
    }

    private void seekUpdate() {
        skduration1.setProgress(mediaPlayer.getCurrentPosition());
        currentduration = getTimeString(mediaPlayer.getCurrentPosition());
        totalduration = getTimeString(mediaPlayer.getDuration());
        seekhandler.postDelayed(run, 1000);
    }

    public void currentsong() {


        if (ifshuffle == true) {
            currentid = r.nextInt(musicidlist.size());
        }


        if (currentduration.equals(totalduration)) {
            currentid++;

            if(ifrepeat){
                currentid--;
            }

            try {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer = new MediaPlayer();
                try {

                    for (int i = 0; i < musicidlist.size(); i++) {


                    }
                    musicselected1 = music_datas1.get(currentid);
                    songcoloring = musicselected1.getMusicid();
                    lblartistname.setText(musicselected1.getArtist());
                    lblsongname.setText(musicselected1.getMusicname());
                    mediaPlayer.setDataSource(musicselected1.getFullpath());
                    mediaPlayer.prepare();
                    skduration1.setMax(musicselected1.getDuration());
                    mediaPlayer.start();
                    btnplaypause.setChecked(true);
                    mediaplayer = mediaPlayer;
                    seekUpdate();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    ifallsongdone = true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } else {
        }

        playingid = songcoloring;
        if (playingid != null && !(mediaPlayer.isPlaying())) {
        }
    }


    private class Ontouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            seekChange(view);
            return false;
        }
    }


    private class Checklisten implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton v, boolean ischecked) {

            if (btnplaypause == v) {
                try {
                    if (mediaPlayer != null) {
                        if (ischecked) {
                            mediaPlayer.start();
                            btnplaypause.setBackgroundResource(R.drawable.play);
                            statusplay = true;
                        } else {
                            mediaPlayer.pause();
                            btnplaypause.setBackgroundResource(R.drawable.pause);
                            statusplay = false;

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (btnshuffle == v) {
                if (ischecked) {
                    ifshuffle = true;
                    Toast.makeText(getApplicationContext(),"shuffle on" , Toast.LENGTH_SHORT).show();

                } else {
                    ifshuffle = false;
                    Toast.makeText(getApplicationContext(),"shuffle off" , Toast.LENGTH_SHORT).show();
                }
            }
            else if (btnrepeat == v) {
                if (ischecked) {
                    ifrepeat = true;
                    Toast.makeText(getApplicationContext(),"repeat on" , Toast.LENGTH_SHORT).show();

                } else {
                    ifrepeat = false;
                    Toast.makeText(getApplicationContext(),"repeat off" , Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}

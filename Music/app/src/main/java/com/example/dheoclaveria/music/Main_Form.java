package com.example.dheoclaveria.music;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main_Form extends AppCompatActivity {

    ListView lstsong;
    Button btnup, btndown, btnrepeat,  btnshuffle;
    SeekBar skduration;
    ToggleButton btnplaypause;
    TextView lblcurrentduration, lbltotalduration;

    ArrayAdapter<String> adapter;
    List<Music> musicinfolist;
    Cursor cursor;
    AudioManager audiomanager;
    Music musicselected;
    MediaPlayer mediaplayer;
    Handler seekhandler = new Handler();
    Integer currentid = null;
    String currentduration, totalduration = "";
    Random r = new Random();
    Integer counter, currentidrepeat  = 0 ;

    Boolean ifrepeat, ifshuffle , ifallsongsdone= false;

    List<Integer> playlistid = new ArrayList<Integer>();
    List<String> playlistpath = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        lstsong = (ListView) findViewById(R.id.lstsong);
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listitem);
        lstsong.setAdapter(adapter);
        lstsong.setOnItemClickListener(new Listviewonclick());

        btnplaypause = (ToggleButton) findViewById(R.id.btnplaypause);
        btnplaypause.setOnCheckedChangeListener(new Checklistner());

        skduration = (SeekBar) findViewById(R.id.skduration);

        btnup = (Button) findViewById(R.id.btnvolup);
        btnup.setOnClickListener(new ButtonEvent());

        btndown = (Button) findViewById(R.id.btnvoldown);
        btndown.setOnClickListener(new ButtonEvent());

        btnrepeat = (Button) findViewById(R.id.btnrepeat);
        btnrepeat.setOnClickListener(new ButtonEvent());

       btnshuffle = (Button) findViewById(R.id.btnshuffle);
       btnshuffle.setOnClickListener(new ButtonEvent());

        musicinfolist = new ArrayList<Music>();
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        lbltotalduration = (TextView) findViewById(R.id.lbltotalduration);
        lblcurrentduration = (TextView) findViewById(R.id.lblcurrentduration);

        Loadmusic();
        if (mediaplayer != null) {seekUpdate();}
        skduration.setOnTouchListener(new Ontouch());
    }


    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnup == v) {
                audiomanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            } else if (btndown == v) {
                audiomanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);

            }
            else if (btnrepeat == v) {
                ifrepeat = true;
            }
            else if (btnshuffle == v) {
                ifshuffle = true;
            }
        }
    }


    public void Loadmusic() {


        Uri songuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String songlist = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        cursor = managedQuery(songuri, new String[]{"*"}, songlist, null, null);
        Music music = null;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                music = new Music();
                music.setMusicid(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                music.setMusicname(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                music.setFullpath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                music.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                music.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                music.setDuration(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                music.setFlesize(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)));
                String photo = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
                adapter.add(music.getMusicname() + " by " + music.getArtist());
                musicinfolist.add(music);
                playlistid.add(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                playlistpath.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            }
        }
    }


    private class Listviewonclick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if (mediaplayer != null && mediaplayer.isPlaying()) {
                mediaplayer.stop();
            }
            mediaplayer = new MediaPlayer();
            try {
                musicselected = musicinfolist.get(i);
                currentid = i;
                mediaplayer.setDataSource(musicselected.getFullpath());
                mediaplayer.prepare();
                skduration.setMax(musicselected.getDuration());
                mediaplayer.start();
                btnplaypause.setChecked(true);
                seekUpdate();

            } catch (Exception ex) {
                ex.printStackTrace();
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

    public void seekUpdate() {

        skduration.setProgress(mediaplayer.getCurrentPosition());
        currentduration = getTimeString(mediaplayer.getCurrentPosition());
        totalduration = getTimeString(mediaplayer.getDuration());


        lbltotalduration.setText(String.valueOf(totalduration));
        lblcurrentduration.setText(String.valueOf(currentduration));
        seekhandler.postDelayed(run, 1000);
    }

    public void currentsong() {

        if(currentduration.equals(totalduration)) {
            currentidrepeat = currentid;

            if(ifshuffle == true){currentid = r.nextInt(lstsong.getCount());}
//
//           if (ifrepeat == true){
//                        counter++;
//                if(counter == 2){counter = 0 ;currentid++;}
//                else{currentid = currentidrepeat;}
//            }
//            else{currentid =  currentid++;}
           currentid++;
            try {
                if (mediaplayer != null && mediaplayer.isPlaying()) {
                    mediaplayer.stop();
                }
                mediaplayer = new MediaPlayer();
                try {
                    musicselected = musicinfolist.get(currentid);

                    mediaplayer.setDataSource(musicselected.getFullpath());
                    mediaplayer.prepare();
                    skduration.setMax(musicselected.getDuration());
                    mediaplayer.start();
                    btnplaypause.setChecked(true);
                    seekUpdate();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{}

    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdate();
            currentsong();

        }
    };


    private class Ontouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            seekChange(view);
            return false;
        }
    }

    public void seekChange(View v) {

        if (mediaplayer != null && mediaplayer.isPlaying()) {
           SeekBar skduration = (SeekBar) v;
            mediaplayer.seekTo(skduration.getProgress());
        }
    }

    private class Checklistner implements CompoundButton.OnCheckedChangeListener {
        @Override

        public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {

            if (mediaplayer != null) {
                if (ischecked ) {
                    mediaplayer.start();
                } else {
                    mediaplayer.pause();
                }
            }
        }
    }

}

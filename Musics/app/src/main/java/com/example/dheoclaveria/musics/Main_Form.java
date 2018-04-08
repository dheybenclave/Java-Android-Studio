package com.example.dheoclaveria.musics;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


public class Main_Form extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Integer playingid,currentdurationpass =0;
    public static  String getcurrentduration,getartistname,getsongname,gettotalduration;
    public static Boolean statusplay;
    ListView listviewsongs;
    ToggleButton btnplaypause;
    Button btnnextsong, btnprevsong;
    MultiAutoCompleteTextView txtsearch;
    TextView lblbotsongname, lblbotartistname;
    SeekBar skduration;

    public static List<String> fullpathlist = new ArrayList<String>();
    public static List<String> musicidlist = new ArrayList<String>();

    ImageView imgbotphto;

     public static   Custom_ListAdapter_Music adapter_messages;
    public static List<Music_Data> music_datas = new ArrayList<Music_Data>();
    Cursor cursor;

   public static AudioManager audiomanager;
    public static  Music_Data musicselected;
    public static  MediaPlayer mediaplayer;

    Handler seekhandler = new Handler();

    Boolean ifprev,ifnext, ifallsongdone = false;
  public static   Integer currentid, songcoloring ;
    String currentduration, totalduration = "";


    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdate();
            currentsong();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtsearch = (MultiAutoCompleteTextView) findViewById(R.id.txtsearch);


        listviewsongs = (ListView) findViewById(R.id.lstsong);
        adapter_messages = new Custom_ListAdapter_Music(this, music_datas);
        listviewsongs.setAdapter(adapter_messages);
        listviewsongs.setOnItemClickListener(new ListViewItemClick());

        btnprevsong = (Button) findViewById(R.id.btnprevsong);
        btnprevsong.setOnClickListener(new ButtonEvent());

        btnplaypause = (ToggleButton) findViewById(R.id.btnplaypuase);
        btnplaypause.setOnCheckedChangeListener(new Checklisten());

        btnnextsong = (Button) findViewById(R.id.btnnextsong);
        btnnextsong.setOnClickListener(new ButtonEvent());

        skduration = (SeekBar) findViewById(R.id.skduration);


        imgbotphto = (ImageView) findViewById(R.id.imgbot);
        imgbotphto.setOnClickListener(new ImgCilckListener());

        lblbotartistname = (TextView) findViewById(R.id.lblbotartistname);
        lblbotartistname.setOnClickListener(new LblClickListner());

        lblbotsongname = (TextView) findViewById(R.id.lblbotsongname);
        lblbotsongname.setOnClickListener(new LblClickListner());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        Loadmusic();

        if (mediaplayer != null) {
            seekUpdate();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_allsong) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_artists) {
            this.setTitle(item.getTitle().toString());
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_albums) {
            this.setTitle(item.getTitle().toString());
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tracks) {
            this.setTitle(item.getTitle().toString());
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_playlisr) {
            this.setTitle(item.getTitle().toString());
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            if (requestCode == 1) {
                if(statusplay){btnplaypause.setChecked(true);}
                else{btnplaypause.setChecked(false);}
                playingid = songcoloring;
                adapter_messages.notifyDataSetChanged();

            }
        }
    }



    public void Loadmusic() {

        listviewsongs.setAdapter(null);
        adapter_messages = new Custom_ListAdapter_Music(this, music_datas);
        music_datas.clear();


        Uri songuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String songlist = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        cursor = managedQuery(songuri, new String[]{"*"}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    int musicid = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    String musicname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String fullpath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                    int filezsize = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                    String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));


                    // id,musicname,fullpath,artist,duration,filesize,album
                    music_datas.add(new Music_Data(musicid, musicname, fullpath, artist, duration, filezsize, album));
                    musicidlist.add(String.valueOf(musicid));
                    fullpathlist.add(fullpath);
                }
                while (cursor.moveToNext());
                listviewsongs.setAdapter(adapter_messages);
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

    private void seekUpdate() {
        skduration.setProgress(mediaplayer.getCurrentPosition());
        currentduration = getTimeString(mediaplayer.getCurrentPosition());
        totalduration = getTimeString(mediaplayer.getDuration());
        seekhandler.postDelayed(run, 1000);
    }

    public void currentsong() {


        if (currentduration.equals(totalduration)) {

            currentid++;
            try {
                if (mediaplayer != null && mediaplayer.isPlaying()) {
                    mediaplayer.stop();
                }
                mediaplayer = new MediaPlayer();
                try {
                    musicselected = music_datas.get(currentid);
                    songcoloring = musicselected.getMusicid();
                    lblbotartistname.setText(musicselected.getArtist());
                    lblbotsongname.setText(musicselected.getMusicname());
                    mediaplayer.setDataSource(musicselected.getFullpath());
                    getartistname =musicselected.getArtist();
                    getsongname = musicselected.getMusicname();

                    mediaplayer.prepare();
                    skduration.setMax(musicselected.getDuration());
                    mediaplayer.start();
                    btnplaypause.setChecked(true);
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
        if(playingid != null && !(mediaplayer.isPlaying())){  adapter_messages.notifyDataSetChanged();}
    }


    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnprevsong == v) {
                if (mediaplayer != null) {
                    currentduration = totalduration;
                    currentid -= 2;
                    currentsong();
                    playingid = songcoloring;
                    adapter_messages.notifyDataSetChanged();

                }
            } else if (btnnextsong == v) {

                if (mediaplayer != null) {
                    currentduration = totalduration;
                    currentsong();
                    playingid = songcoloring;
                    adapter_messages.notifyDataSetChanged();

                }
            }
        }
    }



    private class Checklisten implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton v, boolean ischecked) {

            try {
                if (mediaplayer != null) {
                    if (ischecked) {
                        mediaplayer.start();
                        btnplaypause.setBackgroundResource(R.drawable.play);
                        statusplay = true;

                    } else {
                        mediaplayer.pause();
                        btnplaypause.setBackgroundResource(R.drawable.pause);
                        statusplay = false ;

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }




    private class ListViewItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            playingid = music_datas.get(i).getMusicid();
            adapter_messages.notifyDataSetChanged();
            btnplaypause.setBackgroundResource(R.drawable.play);
             statusplay = true;

            getartistname = music_datas.get(i).getArtist();
            getsongname = music_datas.get(i).getMusicname();


            lblbotartistname.setText(music_datas.get(i).getArtist());
            lblbotsongname.setText(music_datas.get(i).getMusicname());

            if (mediaplayer != null && mediaplayer.isPlaying()) {
                mediaplayer.stop();
            }
            mediaplayer = new MediaPlayer();
            try {
                musicselected = music_datas.get(i);
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

    public  void forpassval() {
        try {
            getcurrentduration = getTimeString(mediaplayer.getCurrentPosition());
            gettotalduration = getTimeString(mediaplayer.getDuration());

        } catch (Exception ex){}

        Intent intent1 = new Intent(Main_Form.this, NowPlaying_Form.class);
        startActivityForResult(intent1, 1);
    }
    private class ImgCilckListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if(imgbotphto == view){
             forpassval();
            }
        }
    }

    private class LblClickListner implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (lblbotsongname == view || lblbotartistname == view) {
                forpassval();
            }
        }
    }
}
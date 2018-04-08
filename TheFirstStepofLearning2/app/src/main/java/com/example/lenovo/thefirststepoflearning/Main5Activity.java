package com.example.lenovo.thefirststepoflearning;

import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main5Activity extends FragmentActivity{
    ViewPager viewPager;
    SwipeAdapter adapter;
    MediaPlayer mediaPlayer;
    private int[] musicResources = {R.raw.rowrow, R.raw.maryhad, R.raw.littleteapot, R.raw.fivelittle, R.raw.littleteapot};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        adapter = new SwipeAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), musicResources[0]);
        mediaPlayer.start();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrollStateChanged(int state) {
                // Called when the scroll state changes (scroll started - ended)
            }

            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {
                // This is called a lot of times when the user is scrolling
            }

            @Override
            public void onPageSelected(int position) {
                try{
                    mediaPlayer.stop();
                }catch (Exception ex){}
                viewPager.setCurrentItem(position);
                mediaPlayer = MediaPlayer.create(getApplicationContext(), musicResources[position]);
                mediaPlayer.start();
            }
        });
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

package com.example.lenovo.thefirststepoflearning;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SwipeAdapter extends PagerAdapter {

    private int[] imageResources = {R.drawable.rowrow, R.drawable.mary, R.drawable.teapot,R.drawable.fivelittle, R.drawable.babyshark};
    private int[] musicResources = {R.raw.rowrow, R.raw.maryhad, R.raw.littleteapot, R.raw.fivelittle, R.raw.littleteapot};
    private Context ctx;
    public static int position1;
    private LayoutInflater layoutInflater;
    public static MediaPlayer mediaPlayer;
    public  SwipeAdapter(Context ctx) {
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout)object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater =(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = layoutInflater.inflate(R.layout.fragment_page,container,false);
        ImageView imageView = (ImageView) itemview.findViewById(R.id.imgPages);
        // imageView.setImageResource(imageResources[position]);
        Toast.makeText(ctx,String.valueOf(position),Toast.LENGTH_SHORT).show();
        int newposition =0;
        try {
            mediaPlayer.stop();
            mediaPlayer = null;
        }catch (Exception e){}
        position1 = position;
        if(position == 0) {newposition = 0; imageView.setImageResource(imageResources[0]);}
        else if(position == 1) {newposition = 1; imageView.setImageResource(imageResources[1]);}
        else if(position == 2) {newposition = 2; imageView.setImageResource(imageResources[2]);}
        else if(position == 3) {newposition = 3; imageView.setImageResource(imageResources[3]);}
        else if(position == 4) {newposition = 4; imageView.setImageResource(imageResources[4]);}

        mediaPlayer = null;
        if(position == 0) {
            mediaPlayer = MediaPlayer.create(ctx, musicResources[0]);
        }
        else if(position == 1) {
            mediaPlayer = MediaPlayer.create(ctx, musicResources[0]);
        }
        else if(position == 2) {
            mediaPlayer = MediaPlayer.create(ctx, musicResources[1]);
        }
        else if(position == 3) {
            mediaPlayer = MediaPlayer.create(ctx, musicResources[2]);
        }
        else if(position == 4) {
            mediaPlayer = MediaPlayer.create(ctx, musicResources[3]);
        }
        // mediaPlayer.start();
        container.addView(itemview);
        return itemview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView((RelativeLayout) object);
            super.destroyItem(container, position, object);
        }catch (Exception e){}
    }
}

package com.example.dheoclaveria.music;

/**
 * Created by Dheo Claveria on 8/19/2017.
 */

public class Music {

    public int musicid,duration,flesize;
    public String musicname, fullpath, album,artist;
//
//    public Music(int musicid,String musicname, String fullpath, String album, String artist, int duration, int flesize){
//
//        this.musicid = musicid;
//        this.musicname = musicname;
//        this.fullpath = fullpath;
//        this.album = album;
//        this.artist =artist;
//        this.duration = duration;
//        this.flesize = flesize;
//    }

    public  Music(){

    }

    public int getMusicid() {
        return musicid;
    }

    public void setMusicid(int musicid) {
        this.musicid = musicid;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFlesize() {
        return flesize;
    }

    public void setFlesize(int flesize) {
        this.flesize = flesize;
    }
}

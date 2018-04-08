package com.example.dheoclaveria.musics;

/**
 * Created by Dheo Claveria on 8/19/2017.
 */

public class Music_Data {

    public int musicid, duration, filesize;
    public String musicname, fullpath, album, artist;

    public Music_Data(int musicid, String musicname, String fullpath, String artist, int duration, int filesize, String album) {

        this.musicid = musicid;
        this.musicname = musicname;
        this.fullpath = fullpath;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.filesize = filesize;
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

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }
}

package com.company.model;

public class Song {

    private int id;
    private int track;
    private String title;
    private int album_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbum() {
        return album_id;
    }

    public void setAlbum(int album) {
        this.album_id = album;
    }
}
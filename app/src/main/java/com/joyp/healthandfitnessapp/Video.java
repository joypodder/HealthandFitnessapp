package com.joyp.healthandfitnessapp;

public class Video {
    private String title;
    private String url;

    public Video() {
        // Default constructor required for calls to DataSnapshot.getValue(Video.class)
    }

    public Video(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

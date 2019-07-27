package com.example.twinkle;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Post implements Serializable {
    private String title;
    private String subtitle;
    private String date;
    private double x;
    private double y;

    public Post(String title, String subtitle, String date, double x, double y) {
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.x = x;
        this.y = y;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("subtitle", subtitle);
        result.put("date", date);
        result.put("x", x);
        result.put("y", y);
        return result;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

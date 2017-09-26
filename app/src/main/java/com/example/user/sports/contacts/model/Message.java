package com.example.user.sports.contacts.model;

import android.graphics.Bitmap;

/**
 * Author : user
 * Date : 17-9-20
 * Description :
 */
public class Message {
    private String name;
    private String time;
    private Bitmap headview;
    private String message;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Bitmap getHeadview() {
        return headview;
    }

    public void setHeadview(Bitmap headview) {
        this.headview = headview;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}

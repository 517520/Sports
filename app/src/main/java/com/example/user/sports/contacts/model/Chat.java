package com.example.user.sports.contacts.model;

import android.graphics.Bitmap;

/**
 * Author : user
 * Date : 17-9-22
 * Description :
 */
public class Chat {
    private String name;
    private Bitmap bitmap;
    private String message;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

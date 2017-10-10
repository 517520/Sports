package com.example.user.sports.view.contacts.model;

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
    private int type;
    public static final int RECEIVED = 1;
    public static final int Send = 0;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

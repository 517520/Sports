package com.example.user.sports.view.contacts.model;

import android.graphics.Bitmap;

/**
 * Author : user
 * Date : 17-9-21
 * Description :
 */
public class Team {
    private String name;
    private String detail;
    private Bitmap bitmap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


}

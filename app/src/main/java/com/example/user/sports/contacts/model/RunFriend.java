package com.example.user.sports.contacts.model;

import android.graphics.Bitmap;

/**
 * Author : user
 * Date : 17-9-21
 * Description :
 */
public class RunFriend {
    private String name;
    private String distance;
    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}

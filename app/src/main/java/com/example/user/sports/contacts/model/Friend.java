package com.example.user.sports.contacts.model;

import android.graphics.Bitmap;

import com.example.user.sports.utils.bean.BaseIndexPinyinBean;

/**
 * Author : user
 * Date : 17-9-20
 * Description :
 */
public class Friend extends BaseIndexPinyinBean {
    private String name;//城市名字
    private Bitmap photo;

    public Friend() {

    }
    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTarget() {
        return name;
    }
}

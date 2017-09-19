package com.example.user.sports.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


/**
 * Created by yufeng.cao
 * Date：2017/9/18.
 * Time：17:32
 * 保存登录信息到本地
 */
public class SharePreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context) {
        sp = context.getSharedPreferences("Sports", context.MODE_PRIVATE);
        editor = sp.edit();
    }

    //电话
    public void setPhone(String phone){
        editor.putString("phone", phone);
        editor.commit();
    }
    public String getPhone(){
        return sp.getString("phone", "");
    }

    // 用户的密码
    public void setPassword(String passwd) {
        editor.putString("password", passwd);
        editor.commit();
    }
    public String getPasswd() {
        return sp.getString("password", "");
    }

    // 昵称
    public void setUsername(String name) {
        editor.putString("username", name);
        editor.commit();
    }
    public String getUsername() {
        return sp.getString("username", "");
    }

    /**
     * 当前状态
     * 0：未运动
     * 1：走路
     * 2：跑步
     * 3：骑行
     */
    public void setState(int state) {
        editor.putInt("state", state);
        editor.commit();
    }
    public int getState() {
        return sp.getInt("state", 0);
    }

    // 用户自己的头像
    public void setImg(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        editor.putString("image", imageBase64);
        editor.commit();
    }
    public Bitmap getImg(Bitmap defaultValue) {
        String imageBase64 = sp.getString("image", "");
        if (TextUtils.isEmpty(imageBase64)) {
            return defaultValue;
        }

        byte[] base64Bytes = Base64.decode(imageBase64.getBytes(),Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        Bitmap ret = BitmapFactory.decodeStream(bais);
        if (ret != null) {
            return ret;
        } else {
            return defaultValue;
        }
    }

    // 是否已经登陆
    public void setIsLogin(boolean isLogin) {
        editor.putBoolean("isLogin", isLogin);
        editor.commit();
    }
    public boolean getIsLogin() {
        return sp.getBoolean("isLogin", false);
    }

    //清空数据
    public void exit(){
        editor.clear().commit();
    }

}

package com.example.user.sports.jsonModel;

/**
 * 登陆时注册完，下一步初步设置个人信息
 */

public class Json_0_set {

    private String phoneNumber;
    private String nickname;
    private String icon;
    private String result;

    public Json_0_set(){ }

    public Json_0_set(String phoneNumber,String nickname,String icon){
        this.phoneNumber=phoneNumber;
        this.nickname=nickname;
        this.icon=icon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}

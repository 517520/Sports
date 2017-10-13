package com.example.user.sports.model.jsonModel;

/**
 * 登陆时注册完，下一步初步设置个人信息
 */

public class Json_0_set {

    private String phoneNumber;
    private String nickname;
    private String iconString;
    private String iconURL;
    private String result;

    public Json_0_set(){ }

    public Json_0_set(String phoneNumber,String nickname,String iconString){
        this.phoneNumber=phoneNumber;
        this.nickname=nickname;
        this.iconString=iconString;
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

    public String getIconString() {
        return iconString;
    }

    public void setIconString(String iconString) {
        this.iconString = iconString;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}

package com.example.user.sports.bean;

/**
 * Created by Allen on 2017/9/27.
 */
public class JsonUserImg {

    private String phoneNumber;
    private String nickname;
    private String icon;
    private String set_result;

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

    public String getSet_result() {
        return set_result;
    }

    public void setSet_result(String set_result) {
        this.set_result = set_result;
    }

    @Override
    public String toString() {
        return "JsonUserImg{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", icon='" + icon + '\'' +
                ", set_result='" + set_result + '\'' +
                '}';
    }
}

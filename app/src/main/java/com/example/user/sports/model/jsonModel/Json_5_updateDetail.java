package com.example.user.sports.model.jsonModel;

/**
 * 我的界面中,更新我的信息
 */

public class Json_5_updateDetail {

    private String phoneNumber;
    private String icon;
    private String nickname;
    private int sex;
    private String birthday;
    private Float weight;
    private int height;
    private int walkNumGoal;
    private String result;

    public Json_5_updateDetail(){ }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWalkNumGoal() {
        return walkNumGoal;
    }

    public void setWalkNumGoal(int walkNumGoal) {
        this.walkNumGoal = walkNumGoal;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Json_5_updateDetail(String phoneNumber, String icon, String nickname
            , int sex, String birthday, Float weight, int height, int walkNumGoal){

        this.phoneNumber=phoneNumber;
        this.icon=icon;
        this.nickname=nickname;
        this.sex=sex;
        this.birthday=birthday;
        this.weight=weight;
        this.height=height;
        this.walkNumGoal=walkNumGoal;
    }


}

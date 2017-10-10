package com.example.user.sports.model.jsonModel;

/**
 * 我的界面中,进行签到
 */
public class Json_5_sign {

    private String phoneNumber;
    private String result;

    public Json_5_sign(){ }

    public Json_5_sign(String phoneNumber){

        this.phoneNumber=phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

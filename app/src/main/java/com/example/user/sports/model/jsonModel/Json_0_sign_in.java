package com.example.user.sports.model.jsonModel;

/**
 * 登陆时手机号码，密码登陆
 */

public class Json_0_sign_in {

    private String password;
    private String phoneNumber;
    private String result;

    public Json_0_sign_in(){ }


    public Json_0_sign_in(String phoneNumber,String password){
        this.phoneNumber=phoneNumber;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

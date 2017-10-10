package com.example.user.sports.jsonModel;

/**
 * 登陆时注册
 */

public class Json_0_sign_up {

    private String phoneNumber;
    private String password;
    private String result;


    public Json_0_sign_up(){ }

    public Json_0_sign_up(String phoneNumber,String password){
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

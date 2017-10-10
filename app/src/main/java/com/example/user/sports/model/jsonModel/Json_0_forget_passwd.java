package com.example.user.sports.model.jsonModel;

/**
 * 登陆时忘记密码
 */

public class Json_0_forget_passwd {

     private String phoneNumber;
     private String newPassword;
     private String result;

    public Json_0_forget_passwd(){ }

     public Json_0_forget_passwd(String phoneNumber,String newPassword){
         this.phoneNumber=phoneNumber;
         this.newPassword=newPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}

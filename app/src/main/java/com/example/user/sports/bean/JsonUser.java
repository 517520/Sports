package com.example.user.sports.bean;

/**
 * Created by Allen on 2017/9/11.
 */
public class JsonUser {

    private String phoneNumber;
    private String password;

    /*
    *  用数字1,2,3,4确定状态，还是字符串直接返回；暂时定义字符串直接返回结果
    *
    *  登录：
    *  (1)登录成功
    *  (2)密码不对
    *  (3)其余错误
    *
    *  注册：
    *  (1)注册成功
    *  (2)用户名重复
    *  (3)密码不一致
    *  (4)其余错误
    *
    * */

    private String result;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonUser{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

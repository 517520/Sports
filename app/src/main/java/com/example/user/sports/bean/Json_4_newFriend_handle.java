package com.example.user.sports.bean;

/**
 * 联系人表，点击新的朋友，进入新的朋友请求的页面
 *
 * 可以处理请求信息，接收或者拒绝他人的请求
 */

public class Json_4_newFriend_handle {

    private String phoneNumber;
    private String linkPhoneNumber;
    private int acceptOrNot;
    private String result;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLinkPhoneNumber() {
        return linkPhoneNumber;
    }

    public void setLinkPhoneNumber(String linkPhoneNumber) {
        this.linkPhoneNumber = linkPhoneNumber;
    }

    public int getAcceptOrNot() {
        return acceptOrNot;
    }

    public void setAcceptOrNot(int acceptOrNot) {
        this.acceptOrNot = acceptOrNot;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

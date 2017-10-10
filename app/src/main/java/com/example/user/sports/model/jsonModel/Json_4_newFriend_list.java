package com.example.user.sports.model.jsonModel;

/**
 * 联系人表，点击新的朋友，进入新的朋友请求的页面
 *
 * 在这里可以看到其他人的请求信息
 */


public class Json_4_newFriend_list {

    private String phoneNumber;
    private String linkPhoneNumber;
    private String linkIcon;
    private String linkNickname;
    private String linkBrief;
    private String result;

    public Json_4_newFriend_list(){ }

    public Json_4_newFriend_list(String phoneNumber){

        this.phoneNumber=phoneNumber;
    }

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

    public String getLinkIcon() {
        return linkIcon;
    }

    public void setLinkIcon(String linkIcon) {
        this.linkIcon = linkIcon;
    }

    public String getLinkNickname() {
        return linkNickname;
    }

    public void setLinkNickname(String linkNickname) {
        this.linkNickname = linkNickname;
    }

    public String getLinkBrief() {
        return linkBrief;
    }

    public void setLinkBrief(String linkBrief) {
        this.linkBrief = linkBrief;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

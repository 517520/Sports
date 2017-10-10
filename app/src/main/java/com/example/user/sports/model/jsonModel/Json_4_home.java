package com.example.user.sports.model.jsonModel;

/**
 * 联系人表，一进去就要展示我的联系人列表，以及未读消息数量
 */

public class Json_4_home {

    private String phoneNumber;
    private String linkPhoneNumber;
    private String linkIcon;
    private String linkNickname;
    private int unReadMsg;
    private String result;

    public Json_4_home(){ }

    public Json_4_home(String phoneNumber){

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

    public int getUnReadMsg() {
        return unReadMsg;
    }

    public void setUnReadMsg(int unReadMsg) {
        this.unReadMsg = unReadMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

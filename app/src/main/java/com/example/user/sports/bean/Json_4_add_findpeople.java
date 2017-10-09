package com.example.user.sports.bean;

/**
 * 联系人表，在搜索栏通过用户电话号码搜索陌生人
 *
 * 可以找到对应的陌生人
 */

public class Json_4_add_findpeople {

    private String phoneNumber;
    private String findPhoneNumber;
    private String findIcon;
    private String findNickname;
    private String findBrief;
    private String result;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFindPhoneNumber() {
        return findPhoneNumber;
    }

    public void setFindPhoneNumber(String findPhoneNumber) {
        this.findPhoneNumber = findPhoneNumber;
    }

    public String getFindIcon() {
        return findIcon;
    }

    public void setFindIcon(String findIcon) {
        this.findIcon = findIcon;
    }

    public String getFindNickname() {
        return findNickname;
    }

    public void setFindNickname(String findNickname) {
        this.findNickname = findNickname;
    }

    public String getFindBrief() {
        return findBrief;
    }

    public void setFindBrief(String findBrief) {
        this.findBrief = findBrief;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

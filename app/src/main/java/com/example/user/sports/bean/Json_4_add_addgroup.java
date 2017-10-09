package com.example.user.sports.bean;

/**
 * 联系人表，在搜索栏通过群组名字找到群后，点击添加，发送添加请求
 */

public class Json_4_add_addgroup {

    private String phoneNumber;
    private int addGroupId;
    private String result;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAddGroupId() {
        return addGroupId;
    }

    public void setAddGroupId(int addGroupId) {
        this.addGroupId = addGroupId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

package com.example.user.sports.model.jsonModel;

/**
 * 联系人表，在搜索栏通过群组名字找讨论群
 *
 * 可以找到几个同样名字的群
 */

public class Json_4_add_findgroup {

    private String phoneNumber;
    private String groupName;
    private int groupId;
    private String groupIcon;
    private String groupIntroduce;
    private String result;

    public Json_4_add_findgroup(){ }

    public Json_4_add_findgroup(String phoneNumber,String groupName){

        this.phoneNumber=phoneNumber;
        this.groupName=groupName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public String getGroupIntroduce() {
        return groupIntroduce;
    }

    public void setGroupIntroduce(String groupIntroduce) {
        this.groupIntroduce = groupIntroduce;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

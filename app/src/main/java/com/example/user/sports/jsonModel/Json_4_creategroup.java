package com.example.user.sports.jsonModel;

/**
 * 联系人表，点击新增群组按钮，添加新的群组
 */

public class Json_4_creategroup {

    private String phoneNumber;
    private String groupIcon;
    private String groupName;
    private String location;
    private String groupIntroduce;
    private String result;

    public Json_4_creategroup(){ }

    public Json_4_creategroup(String phoneNumber,String groupIcon,String groupName
            ,String location,String groupIntroduce){

        this.phoneNumber=phoneNumber;
        this.groupIcon=groupIcon;
        this.groupName=groupName;
        this.location=location;
        this.groupIntroduce=groupIntroduce;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

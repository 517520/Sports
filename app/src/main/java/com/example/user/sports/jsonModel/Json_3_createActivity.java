package com.example.user.sports.jsonModel;

public class Json_3_createActivity {

    private String phoneNumber;
    private String icon;
    private String theam;
    private String beginTime;
    private String endTime;
    private String location;
    private String brief;
    private String result;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTheam() {
        return theam;
    }

    public void setTheam(String theam) {
        this.theam = theam;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Json_3_createActivity(){ }

    public Json_3_createActivity(String phoneNumber,String icon,String theam,String beginTime
            ,String endTime,String location,String brief){
        this.phoneNumber=phoneNumber;
        this.icon=icon;
        this.theam=theam;
        this.beginTime=beginTime;
        this.endTime=endTime;
        this.location=location;
        this.brief=brief;
    }


}

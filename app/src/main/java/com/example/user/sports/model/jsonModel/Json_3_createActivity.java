package com.example.user.sports.model.jsonModel;

public class Json_3_createActivity {

    private String phoneNumber;
    private String iconString;
    private String theam;
    private String beginTime;
    private String endTime;
    private String location;
    private String brief;
    private String iconURL;
    private String result;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIconString() {
        return iconString;
    }

    public void setIconString(String iconString) {
        this.iconString = iconString;
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

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Json_3_createActivity(){ }

    public Json_3_createActivity(String phoneNumber,String iconString,String theam,String beginTime
            ,String endTime,String location,String brief){
        this.phoneNumber=phoneNumber;
        this.iconString=iconString;
        this.theam=theam;
        this.beginTime=beginTime;
        this.endTime=endTime;
        this.location=location;
        this.brief=brief;
    }


}

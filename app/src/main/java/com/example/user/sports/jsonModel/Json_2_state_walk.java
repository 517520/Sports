package com.example.user.sports.jsonModel;

/**
 * 运动时，保存本次步行数据
 */

public class Json_2_state_walk {

    private String phoneNumber;
    private String startTiem;
    private String endTime;
    private String spoType;
    private Float spoDis;
    private Float aveSpeed;
    private Float calorie;
    private int steps_count;
    private Float totTime;
    private String result;

    public Json_2_state_walk(){ }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStartTiem() {
        return startTiem;
    }

    public void setStartTiem(String startTiem) {
        this.startTiem = startTiem;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSpoType() {
        return spoType;
    }

    public void setSpoType(String spoType) {
        this.spoType = spoType;
    }

    public Float getSpoDis() {
        return spoDis;
    }

    public void setSpoDis(Float spoDis) {
        this.spoDis = spoDis;
    }

    public Float getAveSpeed() {
        return aveSpeed;
    }

    public void setAveSpeed(Float aveSpeed) {
        this.aveSpeed = aveSpeed;
    }

    public Float getCalorie() {
        return calorie;
    }

    public void setCalorie(Float calorie) {
        this.calorie = calorie;
    }

    public int getSteps_count() {
        return steps_count;
    }

    public void setSteps_count(int steps_count) {
        this.steps_count = steps_count;
    }

    public Float getTotTime() {
        return totTime;
    }

    public void setTotTime(Float totTime) {
        this.totTime = totTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Json_2_state_walk(String phoneNumber, String startTiem, String endTime, String spoType
            , Float spoDis, Float aveSpeed, Float calorie, int steps_count, Float totTime){
        this.phoneNumber=phoneNumber;
        this.startTiem=startTiem;
        this.endTime=endTime;
        this.spoType=spoType;
        this.spoDis=spoDis;
        this.aveSpeed=aveSpeed;
        this.calorie=calorie;
        this.steps_count=steps_count;
        this.totTime=totTime;
    }
}

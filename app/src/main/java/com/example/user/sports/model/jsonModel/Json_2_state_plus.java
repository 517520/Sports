package com.example.user.sports.model.jsonModel;

import java.util.List;

/**
 * 运动时，保存本次跑步数据
 */

public class Json_2_state_plus {

    private String phoneNumber;
    private String startTiem;
    private String endTime;
    private String spoType;
    private Float spoDis;
    private Float aveSpeed;
    private Float calorie;
    private int steps_count;
    private String  track;
    private String result;

    public Json_2_state_plus(String phoneNumber, String startTiem, String endTime, String spoType, Float spoDis, Float aveSpeed, Float calorie, int steps_count, String track, String result) {
        this.phoneNumber = phoneNumber;
        this.startTiem = startTiem;
        this.endTime = endTime;
        this.spoType = spoType;
        this.spoDis = spoDis;
        this.aveSpeed = aveSpeed;
        this.calorie = calorie;
        this.steps_count = steps_count;
        this.track = track;
        this.result = result;
    }

    public Json_2_state_plus(){ }

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

/*    public Float getTotTime() {
        return totTime;
    }

    public void setTotTime(Float totTime) {
        this.totTime = totTime;
    }*/

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "Json_2_state_plus{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", startTiem='" + startTiem + '\'' +
                ", endTime='" + endTime + '\'' +
                ", spoType='" + spoType + '\'' +
                ", spoDis=" + spoDis +
                ", aveSpeed=" + aveSpeed +
                ", calorie=" + calorie +
                ", steps_count=" + steps_count +
                ", track=" + track +
/*                ", totTime=" + totTime +*/
                ", result='" + result + '\'' +
                '}';
    }
}

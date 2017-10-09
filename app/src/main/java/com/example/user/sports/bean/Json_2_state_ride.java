package com.example.user.sports.bean;

/**
 * 运动时，保存本次骑行数据
 */

public class Json_2_state_ride {

    private String phoneNumber;
    private String startTiem;
    private String endTime;
    private String spoType;
    private Double spoDis;
    private Double aveSpeed;
    private Double calorie;
    private Integer steps_count;
    private Double totTime;
    private String result;

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

    public Double getSpoDis() {
        return spoDis;
    }

    public void setSpoDis(Double spoDis) {
        this.spoDis = spoDis;
    }

    public Double getAveSpeed() {
        return aveSpeed;
    }

    public void setAveSpeed(Double aveSpeed) {
        this.aveSpeed = aveSpeed;
    }

    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    public Integer getSteps_count() {
        return steps_count;
    }

    public void setSteps_count(Integer steps_count) {
        this.steps_count = steps_count;
    }

    public Double getTotTime() {
        return totTime;
    }

    public void setTotTime(Double totTime) {
        this.totTime = totTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}

package com.example.user.sports.jsonModel;

/**
 * 运动圈信息，查看单个活动详情
 */
public class Json_3_activity_single {

    private int activity_id;
    private String icon;
    private String title;
    private String brief;
    private String beginTime;
    private String endTime;
    private String location;
    private String state;
    private String totPeo;
    private String result;

    public Json_3_activity_single(){ }

    public Json_3_activity_single(int activity_id){

        this.activity_id=activity_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTotPeo() {
        return totPeo;
    }

    public void setTotPeo(String totPeo) {
        this.totPeo = totPeo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

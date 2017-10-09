package com.example.user.sports.bean;

/**
 * 运动圈信息，取出所有活动
 */
public class Json_3_activity_all {

    private int activity_id;
    private String icon;
    private String title;
    private String brief;
    private String beginTime;
    private String endTime;
    private String totPeo;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getTotPeo() {
        return totPeo;
    }

    public void setTotPeo(String totPeo) {
        this.totPeo = totPeo;
    }

}

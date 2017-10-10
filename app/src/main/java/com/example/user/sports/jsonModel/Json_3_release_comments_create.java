package com.example.user.sports.jsonModel;

public class Json_3_release_comments_create {

    private String phoneNumber;
    private int activity_id;
    private int comment_otherpeo_id;
    private String comment_content;
    private String result;

    public Json_3_release_comments_create(){ }

    public Json_3_release_comments_create(String phoneNumber,int activity_id,int comment_otherpeo_id,String comment_content){

        this.phoneNumber=phoneNumber;
        this.activity_id=activity_id;
        this.comment_otherpeo_id=comment_otherpeo_id;
        this.comment_content=comment_content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getComment_otherpeo_id() {
        return comment_otherpeo_id;
    }

    public void setComment_otherpeo_id(int comment_otherpeo_id) {
        this.comment_otherpeo_id = comment_otherpeo_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

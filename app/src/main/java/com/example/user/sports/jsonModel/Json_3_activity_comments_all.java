package com.example.user.sports.jsonModel;

/**
 * 运动圈信息，获取某个活动下的所有评论
 */
public class Json_3_activity_comments_all {

    private int activity_id;
    private int comment_otherpeo_id;
    private int comment_id;
    private String comment_nickname;
    private String comment_usericon;
    private String comment_content;
    private String result;

    public Json_3_activity_comments_all(){ }

    public Json_3_activity_comments_all(int activity_id,int comment_otherpeo_id){

        this.activity_id=activity_id;
        this.comment_otherpeo_id=comment_otherpeo_id;
    }

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

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_otherpeo_id() {
        return comment_otherpeo_id;
    }

    public void setComment_otherpeo_id(int comment_otherpeo_id) {
        this.comment_otherpeo_id = comment_otherpeo_id;
    }

    public String getComment_nickname() {
        return comment_nickname;
    }

    public void setComment_nickname(String comment_nickname) {
        this.comment_nickname = comment_nickname;
    }

    public String getComment_usericon() {
        return comment_usericon;
    }

    public void setComment_usericon(String comment_usericon) {
        this.comment_usericon = comment_usericon;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

}

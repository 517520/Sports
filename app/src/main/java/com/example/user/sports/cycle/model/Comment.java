package com.example.user.sports.cycle.model;

import java.util.ArrayList;

/**
 * Created by user on 9/13/17.
 */

public class Comment {
    //头像
    private int mHeadPortraitResourceID;

    //用户名
    private String mUserName;

    public int getHeadPortraitResourceID() {
        return mHeadPortraitResourceID;
    }


    public Comment(int headPortraitResourceID, String userName, ArrayList<Integer> commentPictureResourceIDs) {
        mHeadPortraitResourceID = headPortraitResourceID;
        mUserName = userName;
        mCommentPictureResourceIDs = commentPictureResourceIDs;
    }

    public void setHeadPortraitResourceID(int headPortraitResourceID) {
        mHeadPortraitResourceID = headPortraitResourceID;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public ArrayList<Integer> getCommentPictureResourceIDs() {
        return mCommentPictureResourceIDs;
    }

    public void setCommentPictureResourceIDs(ArrayList<Integer> commentPictureResourceIDs) {
        mCommentPictureResourceIDs = commentPictureResourceIDs;
    }

    //评论图片
    private ArrayList<Integer> mCommentPictureResourceIDs;

}

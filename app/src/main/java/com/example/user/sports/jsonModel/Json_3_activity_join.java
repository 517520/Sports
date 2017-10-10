package com.example.user.sports.jsonModel;

/**
 * 运动圈信息，申请参加某个活动
 */
public class Json_3_activity_join {

    private String phoneNumber;
    private int activity_id;
    private String result;

    public Json_3_activity_join(){ }

    public Json_3_activity_join(String phoneNumber,int activity_id){

        this.phoneNumber=phoneNumber;
        this.activity_id=activity_id;
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


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}

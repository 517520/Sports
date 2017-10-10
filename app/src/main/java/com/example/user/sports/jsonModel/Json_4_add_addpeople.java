package com.example.user.sports.jsonModel;

/**
 * 联系人表，在搜索栏通过电话找到人之后，点击添加，发送添加请求
 */

public class Json_4_add_addpeople {

    private String phoneNumber;
    private String addPhoneNumber;
    private String result;

    public Json_4_add_addpeople(){ }

    public Json_4_add_addpeople(String phoneNumber,String addPhoneNumber){

        this.phoneNumber=phoneNumber;
        this.addPhoneNumber=addPhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddPhoneNumber() {
        return addPhoneNumber;
    }

    public void setAddPhoneNumber(String addPhoneNumber) {
        this.addPhoneNumber = addPhoneNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

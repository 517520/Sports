package com.example.user.sports.utils.bean;

/**
 * 介绍：索引类的汉语拼音的接口
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * CSDN：http://blog.csdn.net/zxt0601
 * 时间： 16/09/04.
 */

public abstract class BaseIndexPinyinBean extends BaseIndexTagBean implements IIndexTargetInterface {
    private String pyName;

    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }
}

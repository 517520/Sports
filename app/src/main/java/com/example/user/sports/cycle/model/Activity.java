package com.example.user.sports.cycle.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by user on 9/7/17.
 */

public class Activity {
    //ID
    public UUID id;

    //活动创建者
    private String activity_owner;

    //活动名称
    private String activity_name;

    //活动类型 1.跑步 2.步行 3.骑行
    private int activity_type;

    //活动创建时间
    private Date activity_time_create;

    //活动开始时间
    private Date activity_time_start;

    //活动结束时间
    private Date activity_time_end;

    //活动地点


    private String activity_address;

    //活动状态 1.未开始 2.正在进行 3.已结束
    private  int activity_state;

    //活动详细描述
    private String activity_description;

    //参与用户
    private ArrayList<User> mUsers;

    //已参加人数
    private int user_joined;

    //限制人数
    private int user_max;


    public UUID getId() {
        return id;
    }

    public Activity(
                    String activity_owner,
                    String activity_name,
                    int activity_type,
                    Date activity_time_start,
                    Date activity_time_end,
                    String activity_address,
                    String activity_description,
                    int user_max) {
        this.id = UUID.randomUUID();

        this.activity_owner = activity_owner;
        this.activity_name = activity_name;
        this.activity_type = activity_type;
        this.activity_time_create = new Date();
        this.activity_time_start = activity_time_start;
        this.activity_time_end = activity_time_end;
        this.activity_address = activity_address;
        this.activity_state = 1;
        this.activity_description = activity_description;
        mUsers = new ArrayList<User>();
        this.user_joined = 0;
        this.user_max = user_max;
    }


    public int getUser_joined() {
        return getUsers().size();
    }


    public int getUser_max() {
        return user_max;
    }

    public void setUser_max(int user_max) {
        this.user_max = user_max;
    }

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public void setUsers(ArrayList<User> users) {
        mUsers = users;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }

    public int getActivity_state() {
        return activity_state;
    }

    public void setActivity_state(int activity_state) {
        this.activity_state = activity_state;
    }

    public String getActivity_address() {
        return activity_address;
    }

    public void setActivity_address(String activity_address) {
        this.activity_address = activity_address;
    }

    public Date getActivity_time_end() {
        return activity_time_end;
    }

    public void setActivity_time_end(Date activity_time_end) {
        this.activity_time_end = activity_time_end;
    }

    public Date getActivity_time_start() {
        return activity_time_start;
    }

    public void setActivity_time_start(Date activity_time_start) {
        this.activity_time_start = activity_time_start;
    }

    public Date getActivity_time_create() {
        return activity_time_create;
    }

    public void setActivity_time_create(Date activity_time_create) {
        this.activity_time_create = activity_time_create;
    }

    public int getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(int activity_type) {
        this.activity_type = activity_type;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_owner() {
        return activity_owner;
    }

    public void setActivity_owner(String activity_owner) {
        this.activity_owner = activity_owner;
    }
}

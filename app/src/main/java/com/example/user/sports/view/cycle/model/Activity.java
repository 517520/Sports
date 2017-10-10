package com.example.user.sports.view.cycle.model;

import java.util.Calendar;
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

    //活动开始时间
    private Calendar activity_time_start;

    //活动结束时间
    private Calendar activity_time_end;

    //活动地点
    private String activity_address;

    //活动状态 1.未开始 2.正在进行 3.已结束
    private  int activity_state;

    //活动详细描述
    private String activity_description;

    //参与用户
    private ArrayList<User> mUsers;


    public Activity(String activity_owner,
                    String activity_name,
                    Calendar activity_time_start,
                    Calendar activity_time_end,
                    String activity_address,
                    int activity_state,
                    String activity_description,
                    ArrayList<User> users) {
        this.id = UUID.randomUUID();
        this.activity_description = activity_description;
        this.activity_time_start = activity_time_start;
        this.activity_time_end = activity_time_end;
        this.activity_address = activity_address;
        this.activity_state = activity_state;
        this.activity_owner = activity_owner;
        this.activity_name = activity_name;
        mUsers = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getActivity_owner() {
        return activity_owner;
    }

    public void setActivity_owner(String activity_owner) {
        this.activity_owner = activity_owner;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public Calendar getActivity_time_start() {
        return activity_time_start;
    }

    public void setActivity_time_start(Calendar activity_time_start) {
        this.activity_time_start = activity_time_start;
    }

    public Calendar getActivity_time_end() {
        return activity_time_end;
    }

    public void setActivity_time_end(Calendar activity_time_end) {
        this.activity_time_end = activity_time_end;
    }

    public String getActivity_address() {
        return activity_address;
    }

    public void setActivity_address(String activity_address) {
        this.activity_address = activity_address;
    }

    public int getActivity_state() {
        return activity_state;
    }

    public void setActivity_state(int activity_state) {
        this.activity_state = activity_state;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public void setUsers(ArrayList<User> users) {
        mUsers = users;
    }
}

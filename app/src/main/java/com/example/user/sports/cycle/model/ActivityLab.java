package com.example.user.sports.cycle.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 9/11/17.
 */

public class ActivityLab {
    private static ActivityLab sActivities;
    private ArrayList<Activity> mActivities;

    public static ActivityLab get(Context context){
        if (sActivities==null)
            sActivities = new ActivityLab(context);
        return sActivities;
    }

    private ActivityLab(Context context){
        mActivities = new ArrayList<>();

        for (int i=0;i<30;i++){
            Activity activity = new Activity("owner"+i,"activity"+i,i%3,new Date(),new Date(),"address"+i,"hello world"+i,2*i);
            mActivities.add(activity);
        }
    }

    public ArrayList<Activity> getActivitiesByLab(){
        return mActivities;
    }

    public Activity getActivityByLab(UUID id){
        for (Activity activity:mActivities){
            if (activity.getId().equals(id)){
                return activity;
            }
        }
        return null;
    }

}

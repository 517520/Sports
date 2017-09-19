package com.example.user.sports.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.user.sports.R;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/12.
 */
public class IntentUtils {

    public static void turnTo(Activity activity, Class cls, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent);
        if (isFinish) {
            //是否finish 掉activity
            activity.finish();
            activity.overridePendingTransition(R.anim.no_animotion, R.anim.no_animotion);
        }
    }

    public static void turnTo(Activity activity, Class cls, boolean isFinish, Map<String, Object> map) {
        Intent intent = new Intent(activity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        for (Map.Entry<String, Object> maps : map.entrySet()) {
            intent.putExtra(maps.getKey(), ((Serializable) maps.getValue()));
        }
        activity.startActivity(intent);
        if (isFinish) {
            //是否finish 掉activity
            activity.finish();
        }
    }

    public static void turnToInstance(Activity activity, Class cls, boolean isFinish, Map<String, Object> map) {
        Intent intent = new Intent(activity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        for (Map.Entry<String, Object> maps : map.entrySet()) {
            intent.putExtra(maps.getKey(), ((Serializable) maps.getValue()));
        }
        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        activity.startActivity(intent);
        if (isFinish) {
            //是否finish 掉activity
            activity.finish();
        }
    }


    public static void turnToHaveResult(Activity activity, Class cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, 0);

    }

}

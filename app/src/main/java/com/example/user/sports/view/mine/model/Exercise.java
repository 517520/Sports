package com.example.user.sports.view.mine.model;

/**
 * Created by user on 10/17/17.
 */

public class Exercise {
    private int mExerciseStyle;  //运动类型
    private float mExerciseDistance;  //运动距离
    private int mExerciseTime;     //运动时间


    public Exercise(int exerciseStyle, float exerciseDistance, int exerciseTime) {
        mExerciseStyle = exerciseStyle;
        mExerciseDistance = exerciseDistance;
        mExerciseTime = exerciseTime;
    }

    public int getExerciseStyle() {
        return mExerciseStyle;
    }

    public void setExerciseStyle(int exerciseStyle) {
        mExerciseStyle = exerciseStyle;
    }

    public float getExerciseDistance() {
        return mExerciseDistance;
    }

    public void setExerciseDistance(int exerciseDistance) {
        mExerciseDistance = exerciseDistance;
    }

    public int getExerciseTime() {
        return mExerciseTime;
    }

    public void setExerciseTime(int exerciseTime) {
        mExerciseTime = exerciseTime;
    }
}

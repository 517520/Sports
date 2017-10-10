package com.example.user.sports.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 9/20/17.
 */

public class CheckInView extends View {

    private Paint mPaint = new Paint();

    private int mNOColor = 0xFF757575;
    private int mYESColor  = 0xFF15A9E6;
    private int checkInDayNumber=0;
    private int widthsize;
    private int heightsize;


    public CheckInView(Context context) {
        super(context);
        initPaint();
    }

    public CheckInView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CheckInView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public CheckInView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);

        heightsize = MeasureSpec.getSize(heightMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mNOColor);
        canvas.drawLine(5,40,widthsize-5,40,mPaint);
        int distance = (widthsize-40)/6;
        int k = 20;
        for(int i=0;i<7;i++){
            canvas.drawCircle(k,40,20,mPaint);
            k=k+distance;

        }
        if (checkInDayNumber>0){
            mPaint.setColor(mYESColor);
            canvas.drawLine(5,40,checkInDayNumber*distance,40,mPaint);
            k = 20;
            for(int j=0;j<checkInDayNumber;j++){
                canvas.drawCircle(k,40,20,mPaint);
                k=k+distance;
            }
        }


    }

    private void initPaint(){
        mPaint.setStrokeWidth(15f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setCheckInDayNumber(int dayNumber){
        checkInDayNumber = dayNumber;
        invalidate();
    }
}

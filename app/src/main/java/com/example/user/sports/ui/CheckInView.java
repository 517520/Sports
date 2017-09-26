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
        canvas.drawLine(5,40,widthsize,40,mPaint);
        mPaint.setColor(mYESColor);
        canvas.drawLine(5,40,200,40,mPaint);
        canvas.drawCircle(200,40,20,mPaint);
        canvas.drawCircle(20,40,20,mPaint);
    }

    private void initPaint(){
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(15f);
        mPaint.setStyle(Paint.Style.FILL);
    }
}

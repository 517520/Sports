package com.example.user.sports.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.example.user.sports.R;

/**
 * Created by DylanAndroid on 2016/5/26.
 * 显示步数的圆弧
 */
public class StepArcView extends View {

    /**
     * 圆弧的宽度
     */
    private float borderWidth = dipToPx(10);
    /**
     * 画步数的数值的字体大小
     */
    private float numberTextSize = 0;
    /**
     * 步数
     */
    private String stepNumber = "0";
    /**
     * 开始绘制圆弧的角度
     */
    private float startAngle = 125;
    /**
     * 终点对应的角度和起始点对应的角度的夹角
     */
    private float angleLength = 290;
    /**
     * 所要绘制的当前步数的蓝色圆弧终点到起点的夹角
     */
    private float currentAngleLength = 0;
    /**
     * 动画时长
     */
    private int animationLength = 3000;

    /**
     * 当前运动类型
     */
    private String type = "Riding";

    /**
     * 当前活跃等级
     */
    private String level = "等级：轻度活跃";

    /**
     * 步数上方文字
     */
    private String today = "今日步数";

    /**
     * 单位km是否显示
     */
    private String unit = "Km";

    public StepArcView(Context context) {
        super(context);
    }

    public StepArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StepArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**中心点的x坐标*/
        float centerX = (getWidth()) / 2;
        /**指定圆弧的外轮廓矩形区域*/
        RectF rectF = new RectF(0 + borderWidth, borderWidth, 2 * centerX - borderWidth, 2 * centerX - borderWidth);

        /**【第一步】绘制整体的灰色圆弧*/
        drawArcYellow(canvas, rectF);
        /**【第二步】绘制当前进度的红蓝色圆弧*/
        drawArcRed(canvas, rectF);
        /**小图标*/
        drawIcon(canvas, rectF);

        /**【第三步】绘制当前进度的红色数字*/
        drawTextNumber(canvas, centerX);
        /**【第四步】绘制"步数"的红色数字*/
        drawTextStepString(canvas, centerX);
        /**【第五步】绘制当前记步类型*/
        drawTextType(canvas, centerX);
        /**【第六步】绘制当前等级类型*/
        drawTextLevel(canvas, centerX);
        /**【第七步】绘制骑行距离单位*/
        drawTextUnit(canvas, centerX);
    }



    /**
     * 1.绘制总步数的灰色圆弧
     *
     * @param canvas 画笔
     * @param rectF  参考的矩形
     */
    private void drawArcYellow(Canvas canvas, RectF rectF) {
        Paint paint = new Paint();
        /** 默认画笔颜色，灰色 */
        paint.setColor(getResources().getColor(R.color.near_black));
        /** 结合处为圆弧*/
        paint.setStrokeJoin(Paint.Join.MITER);
        /** 设置画笔的样式 Paint.Cap.Round ,Cap.SQUARE等分别为圆形、方形*/
        paint.setStrokeCap(Paint.Cap.BUTT);
        /** 设置画笔的填充样式 Paint.Style.FILL  :填充内部;Paint.Style.FILL_AND_STROKE  ：填充内部和描边;  Paint.Style.STROKE  ：仅描边*/
        paint.setStyle(Paint.Style.STROKE);

        float[] floats = {4,16,4,16};
        paint.setPathEffect(new DashPathEffect(floats, 0));

        /**抗锯齿功能*/
        paint.setAntiAlias(true);
        /**设置画笔宽度*/
        paint.setStrokeWidth(borderWidth);

        /**绘制圆弧的方法
         * drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)//画弧，
         参数一是RectF对象，一个矩形区域椭圆形的界限用于定义在形状、大小、电弧，
         参数二是起始角(度)在电弧的开始，圆弧起始角度，单位为度。
         参数三圆弧扫过的角度，顺时针方向，单位为度,从右中间开始为零度。
         参数四是如果这是true(真)的话,在绘制圆弧时将圆心包括在内，通常用来绘制扇形；如果它是false(假)这将是一个弧线,
         参数五是Paint对象；
         */
        canvas.drawArc(rectF, startAngle, angleLength, false, paint);

    }

    /**
     * 2.绘制当前步数的蓝色圆弧
     */
    private void drawArcRed(Canvas canvas, RectF rectF) {
        Paint paintCurrent = new Paint();
        paintCurrent.setStrokeJoin(Paint.Join.MITER);
        paintCurrent.setStrokeCap(Paint.Cap.BUTT);//圆角弧度
        paintCurrent.setStyle(Paint.Style.STROKE);//设置填充样式
        paintCurrent.setAntiAlias(true);//抗锯齿功能
        paintCurrent.setStrokeWidth(borderWidth);//设置画笔宽度
        paintCurrent.setColor(getResources().getColor(R.color.colorPrimary));//设置画笔颜色
        float[] floats = {4,16,4,16};
        paintCurrent.setPathEffect(new DashPathEffect(floats, 0));
        canvas.drawArc(rectF, startAngle, currentAngleLength, false, paintCurrent);
    }

    /**
     * 画小图标
     */
    private void drawIcon(Canvas canvas, RectF rectF) {
        Paint iconPaint = new Paint();
        
    }


    /**
     * 3.圆环中心的步数
     */
    private void drawTextNumber(Canvas canvas, float centerX) {
        Paint vTextPaint = new Paint();
        vTextPaint.setTextAlign(Paint.Align.CENTER);
        vTextPaint.setAntiAlias(true);//抗锯齿功能
        vTextPaint.setTextSize(numberTextSize);
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        vTextPaint.setTypeface(font);//字体风格
        vTextPaint.setColor(getResources().getColor(R.color.white));
        Rect bounds_Number = new Rect();
        vTextPaint.getTextBounds(stepNumber, 0, stepNumber.length(), bounds_Number);
        canvas.drawText(stepNumber, centerX, getHeight() / 2 + bounds_Number.height() / 2, vTextPaint);
    }

    /**
     * 4.圆环中心[本次步数]的文字
     */
    private void drawTextStepString(Canvas canvas, float centerX) {
        Paint vTextPaint = new Paint();
        vTextPaint.setTextSize(dipToPx(16));
        vTextPaint.setTextAlign(Paint.Align.CENTER);
        vTextPaint.setAntiAlias(true);//抗锯齿功能
        vTextPaint.setColor(getResources().getColor(R.color.gray));
        Rect bounds = new Rect();
        vTextPaint.getTextBounds(today, 0, today.length(), bounds);
        canvas.drawText(today, centerX, getHeight() / 2 + bounds.height() - 2 * getFontHeight(numberTextSize), vTextPaint);
    }

    /**
     *  5.圆环中下[Walking]等文字
     */
    private void drawTextType(Canvas canvas, float centerX) {
        Paint mTypePaint = new Paint();
        mTypePaint.setTextSize(dipToPx(22));
        mTypePaint.setTextAlign(Paint.Align.CENTER);
        mTypePaint.setAntiAlias(true);
        mTypePaint.setColor(getResources().getColor(R.color.text_blue));
        Rect bounds = new Rect();
        mTypePaint.getTextBounds(type, 0, type.length(), bounds);
        canvas.drawText(type, centerX, getHeight() / 2 + 2 * bounds.height()  + getFontHeight(numberTextSize), mTypePaint);
    }

    /**
     *  6.绘制圆环下方等级
     */
    private void drawTextLevel(Canvas canvas, float centerX) {
        Paint mLevelPaint = new Paint();
        mLevelPaint.setTextSize(dipToPx(12));
        mLevelPaint.setTextAlign(Paint.Align.CENTER);
        mLevelPaint.setAntiAlias(true);
        mLevelPaint.setColor(getResources().getColor(R.color.input_hint_gray));
        Rect bounds = new Rect();
        mLevelPaint.getTextBounds(level, 0, level.length(), bounds);
        canvas.drawText(level, centerX, getHeight() / 2 + 2 * bounds.height() + 2 * getFontHeight(numberTextSize), mLevelPaint);
    }

    /**
     *  7.绘制骑行单位km
     */
    private void drawTextUnit(Canvas canvas, float centerX) {
        Paint mUnitPaint = new Paint();
        mUnitPaint.setTextSize(dipToPx(16));
        mUnitPaint.setTextAlign(Paint.Align.CENTER);
        mUnitPaint.setAntiAlias(true);
        mUnitPaint.setColor(getResources().getColor(R.color.input_hint_gray));
        Rect bounds = new Rect();
        mUnitPaint.getTextBounds(unit, 0, unit.length(), bounds);
        canvas.drawText(unit, centerX+ stepNumber.length()*80, getHeight() / 2 + bounds.height() * 3 / 2, mUnitPaint);
    }


    /**
     * 获取当前步数的数字的高度
     *
     * @param fontSize 字体大小
     * @return 字体高度
     */
    public int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Rect bounds_Number = new Rect();
        paint.getTextBounds(stepNumber, 0, stepNumber.length(), bounds_Number);
        return bounds_Number.height();
    }

    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * 所走的步数进度
     * @param totalStepNum  设置的步数
     * @param currentCounts 所走步数
     */
    public void setCurrentCount(int totalStepNum, int currentCounts) {
        /**如果当前走的步数超过总步数则圆弧还是270度，不能成为园*/
        if (currentCounts > totalStepNum) {
            currentCounts = totalStepNum;
        }

        /**上次所走步数占用总共步数的百分比*/
        float scalePrevious = (float) Integer.valueOf(stepNumber) / totalStepNum;
        /**换算成弧度最后要到达的角度的长度-->弧长*/
        float previousAngleLength = scalePrevious * angleLength;

        /**所走步数占用总共步数的百分比*/
        float scale = (float) currentCounts / totalStepNum;
        /**换算成弧度最后要到达的角度的长度-->弧长*/
        float currentAngleLength = scale * angleLength;
        /**开始执行动画*/
        setAnimation(previousAngleLength, currentAngleLength, animationLength);

        stepNumber = String.valueOf(currentCounts);
        setTextSize(currentCounts);
    }

    /**
     * 设置各个参数
     */
    public void setParams(String today, String unit, String type, String level) {
        this.today = today;
        this.unit = unit;
        this.type = type;
        this.level = level;
    }

    /**
     * 为进度设置动画
     * ValueAnimator是整个属性动画机制当中最核心的一个类，属性动画的运行机制是通过不断地对值进行操作来实现的，
     * 而初始值和结束值之间的动画过渡就是由ValueAnimator这个类来负责计算的。
     * 它的内部使用一种时间循环的机制来计算值与值之间的动画过渡，
     * 我们只需要将初始值和结束值提供给ValueAnimator，并且告诉它动画所需运行的时长，
     * 那么ValueAnimator就会自动帮我们完成从初始值平滑地过渡到结束值这样的效果。
     *
     * @param start   初始值
     * @param current 结束值
     * @param length  动画时长
     */
    private void setAnimation(float start, float current, int length) {
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(start, current);
        progressAnimator.setDuration(length);
        progressAnimator.setTarget(currentAngleLength);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /**每次在初始值和结束值之间产生的一个平滑过渡的值，逐步去更新进度*/
                currentAngleLength = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator.start();
    }

    /**
     * 设置文本大小,防止步数特别大之后放不下，将字体大小动态设置
     *
     * @param num
     */
    public void setTextSize(int num) {
        String s = String.valueOf(num);
        int length = s.length();
        if (length <= 4) {
            numberTextSize = dipToPx(50);
        } else if (length > 4 && length <= 6) {
            numberTextSize = dipToPx(40);
        } else if (length > 6 && length <= 8) {
            numberTextSize = dipToPx(30);
        } else if (length > 8) {
            numberTextSize = dipToPx(25);
        }
    }

}


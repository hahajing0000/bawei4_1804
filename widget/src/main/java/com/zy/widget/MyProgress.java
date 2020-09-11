package com.zy.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public class MyProgress extends View {
    /**
     * 画笔  mPaint1  底圆画笔  mPaint2  上层弧形画笔  mPaint3 文本画笔
     */
    private Paint mPaint1,mPaint2,mPaint3;
    /**
     * 文本显示内容
     */
    private String content="0%";
    /**
     * 弧形角度
     */
    private float angle=0;

    /**
     * 默认宽高 对应 Warp_Content
     */
    private int defaultWidth=400;
    private int defaultHeight=400;

    /**
     * 默认半径大小
     */
    private int radius=100;

    /**
     * 线宽
     */
    private Float strokeWidth=15.0F;

    public MyProgress(Context context) {
        super(context);
    }

    public MyProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    public MyProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 处理Warp_Content的情况
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultHeight);
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 绘制时需要考虑Padding
         */


        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,radius,mPaint1);
        RectF rectF=new RectF(getMeasuredWidth()/2-radius,getMeasuredHeight()/2-radius,getMeasuredWidth()/2+radius,getMeasuredHeight()/2+radius);
        canvas.drawArc(rectF,0,angle,false,mPaint2);

        Rect bounds=new Rect();
        String text=content;
        mPaint3.getTextBounds(text,0,text.length(),bounds);
        float offSet=(bounds.top+bounds.bottom)/2;
        canvas.drawText(text,getMeasuredWidth()/2,getMeasuredHeight()/2-offSet,mPaint3);
    }

    @SuppressLint("WrongConstant")
    public void startAnimator(){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatMode(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float animatedValue = (Float) valueAnimator.getAnimatedValue();
                angle= 360*animatedValue;
                DecimalFormat format=new DecimalFormat("#.00");
                content= format.format(angle/360*100)+"%";
                //判断是否主线程
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint1=new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(strokeWidth);
        mPaint1.setDither(true);
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.GRAY);

        mPaint2=new Paint();
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(strokeWidth);
        mPaint2.setAntiAlias(true);
        mPaint2.setDither(true);
        mPaint2.setColor(Color.BLUE);
        mPaint2.setStrokeCap(Paint.Cap.ROUND);
        mPaint2.setStrokeJoin(Paint.Join.ROUND);

        mPaint3=new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setDither(true);
        mPaint3.setColor(Color.RED);
        mPaint3.setTextAlign(Paint.Align.CENTER);
        mPaint3.setTextSize(25.0f);
    }

}

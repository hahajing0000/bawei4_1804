package com.zy.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/9/10
 */
public class CircleView extends View {
    private Paint paintBackground,paintFoceground,paintText;
    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Float strokeWidth=10F;

    private int defaultWidth=200;
    private int defaultHeight=200;

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paintBackground=new Paint();
        paintBackground.setStyle(Paint.Style.STROKE);
        paintBackground.setColor(Color.GRAY);
        paintBackground.setStrokeWidth(strokeWidth);
        paintBackground.setDither(true);
        paintBackground.setAntiAlias(true);

        paintFoceground=new Paint();
        paintFoceground.setStyle(Paint.Style.STROKE);
        paintFoceground.setColor(Color.BLUE);
        paintFoceground.setStrokeWidth(strokeWidth);
        paintFoceground.setDither(true);
        paintFoceground.setAntiAlias(true);

        paintText=new Paint();
        paintText.setColor(Color.RED);
        paintText.setTextSize(20F);
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultHeight);
        }

    }


    float angle=0;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @SuppressLint("WrongConstant")
    public void startAnimal(){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatMode(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();
                Log.d("123","avalue=>"+animatedValue);
                angle=animatedValue*360;
                Log.d("123","angle=>"+angle);
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }
                else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,100,paintBackground);
        RectF rectF=new RectF();
        rectF.left=getMeasuredWidth()/2-100;
        rectF.top=getMeasuredHeight()/2-100;
        rectF.right=getMeasuredWidth()/2+100;
        rectF.bottom=getMeasuredHeight()/2+100;
        canvas.drawArc(rectF,0,angle,false,paintFoceground);
        Rect bounds=new Rect();
        String text="20%";
        paintText.getTextBounds(text,0,text.length(),bounds);
        float offSet=(bounds.top+bounds.bottom)/2;
        canvas.drawText(text,getMeasuredWidth()/2,getMeasuredHeight()/2-offSet,paintText);
    }
}

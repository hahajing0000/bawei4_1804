package com.zy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/9/17
 */
public class PointView extends View {
    private Paint mPaint;
    private int color;

    private int defaultWH=50;
    private float defaultRaidus;

    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PointView);

        color = typedArray.getColor(R.styleable.PointView_mColor, Color.GRAY);
        defaultRaidus = typedArray.getDimension(R.styleable.PointView_mRaidus, 20.0F);

        typedArray.recycle();
        initPaint();
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWH,defaultWH);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWH,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultWH);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,defaultRaidus,mPaint);

    }

    public void setColor(int color){
        mPaint.setColor(color);
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            invalidate();
        }
        else{
            postInvalidate();
        }
    }

    public void resetColor(){
        mPaint.setColor(color);
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            invalidate();
        }
        else{
            postInvalidate();
        }
    }
}

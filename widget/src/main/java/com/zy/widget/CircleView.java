package com.zy.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/9/10
 */
public class CircleView extends View {
    private Paint paintBackground,paintFoceground,paintText;
    private float defaultTextSize=18F;

    private CircleViewCompletedListener listener;

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

    public void setListener(CircleViewCompletedListener listener) {
        this.listener = listener;
    }

    private Float strokeWidth=5F;

    private int defaultWidth=80;
    private int defaultHeight=80;

    private int duration=5;

    String text=String.valueOf(duration);

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
        paintText.setTextSize(defaultTextSize);
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

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    long start,end;
    int temp_num=0;
    @SuppressLint("WrongConstant")
    public void startAnimal(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (temp_num>=duration){
                    if (listener!=null){
                        listener.completed();
                    }
                    return;
                }
                handler.postDelayed(this,1000);
                text=String.valueOf(duration-(++temp_num));
                invalidate();

            }
        },1000);
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(duration*2*1000);


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();
//                Log.d("123","avalue=>"+animatedValue);
                angle=animatedValue*360;

//                int temp = 360 / duration;
//                int round = Math.round(angle % temp);
//                Log.d("123","round -> "+round);
//                if (round==0){
//                    text=String.valueOf(duration-(++temp_num)+1);
//                    Log.d("123","text -> "+text);
//
//                }
//
//                Log.d("123","angle=>"+angle);
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }
                else{
                    postInvalidate();
                }
            }

        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                end=System.currentTimeMillis()-start;

                Log.d("123",""+end/1000.00);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                start=System.currentTimeMillis();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int r=defaultWidth/2-10;
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,r,paintBackground);
        RectF rectF=new RectF();
        rectF.left=getMeasuredWidth()/2-r;
        rectF.top=getMeasuredHeight()/2-r;
        rectF.right=getMeasuredWidth()/2+r;
        rectF.bottom=getMeasuredHeight()/2+r;
        canvas.drawArc(rectF,0,angle,false,paintFoceground);
        Rect bounds=new Rect();

        paintText.getTextBounds(text,0,text.length(),bounds);
        float offSet=(bounds.top+bounds.bottom)/2;
        canvas.drawText(text,getMeasuredWidth()/2,getMeasuredHeight()/2-offSet,paintText);
    }


    public void destoryView(){
        if (handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    public interface CircleViewCompletedListener{
        void completed();
    }
}

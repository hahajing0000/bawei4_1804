package com.zy.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import androidx.core.content.ContextCompat;

/**
 * @author:zhangyue
 * @date:2020/9/22
 */
public class BesselView extends RelativeLayout {
    private Context mContext;
    private int[] imgs={};

    Random random=new Random();

    /**
     * 当前控件宽
     */
    private int width;
    /**
     * 当前控件高
     */
    private int height;

    private int imgWidth;
    private int imgHeight;

    private AnimatorSet animatorSet;



    public BesselView(Context context) {
        this(context,null);
    }

    public BesselView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BesselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

    }

    public void setImgs(int[] _imgs){
        imgs=_imgs;

        Drawable drawable = ContextCompat.getDrawable(mContext,imgs[0]);
        imgWidth=drawable.getIntrinsicWidth();
        imgHeight=drawable.getIntrinsicHeight();
    }

    private void showBesselImgs(Context mContext) {

        final ImageView imageView=new ImageView(mContext);

        imageView.setBackgroundResource(imgs[random.nextInt(imgs.length-1)]);

        RelativeLayout.LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        imageView.setLayoutParams(params);

        addView(imageView);

        animatorSet = createAnimatorSet(imageView);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(imageView);
            }
        });
        animatorSet.start();
    }

    /**
     * 开始动画
     */
    public void startAnimator(){
        showBesselImgs(mContext);
    }

    private AnimatorSet createAnimatorSet(ImageView imageView) {
        AnimatorSet animatorSet=new AnimatorSet();

        AnimatorSet subAnimatorSet=new AnimatorSet();
//        ObjectAnimator a=ObjectAnimator.ofFloat(imageView,"alpha",0.2f,1f);
////        ObjectAnimator sX=ObjectAnimator.ofFloat(imageView,"scaleX",0.2f,1f);
////        ObjectAnimator sY=ObjectAnimator.ofFloat(imageView,"scaleY",0.2f,1f);
//        subAnimatorSet.setDuration(500);
////        subAnimatorSet.playTogether(a,sX,sY);
//        subAnimatorSet.playTogether(a);



        ValueAnimator besselAnimator=createBesselAnimator(imageView);

        animatorSet.playSequentially(subAnimatorSet,besselAnimator);

        return animatorSet;
    }

    private ValueAnimator createBesselAnimator(final ImageView imageView) {
        //构建三阶贝塞尔曲线的四个点
        ZPoint point0 = new ZPoint((width-imgWidth) / 2, height);
        ZPoint point1 = new ZPoint(random.nextInt(width), random.nextInt(height / 2));
        ZPoint point2 = new ZPoint(random.nextInt(width), random.nextInt(height / 2) + height / 2);
        ZPoint point3 = new ZPoint(random.nextInt(width-imgWidth), 0);

        //创建贝塞尔属性动画
        BesselEvaluator evaluator = new BesselEvaluator(point1, point2);
        final ValueAnimator valueAnimator = ObjectAnimator.ofObject(evaluator, point0, point3);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(4000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取计算后的值
                ZPoint zPoint = (ZPoint) animation.getAnimatedValue();
                imageView.setX(zPoint.getX());
                imageView.setY(zPoint.getY());
                //设置消失动画
                float t = animation.getAnimatedFraction();
                imageView.setAlpha(1 - t + 0.2f);
            }
        });
        return valueAnimator;
    }
}
package com.zy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zy.widget.entity.BulletItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;

/**
 * @author:zhangyue
 * @date:2020/9/25
 */
public class BulletScreenView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    /**
     * 线程退出的标志
     */
    private boolean flag=true;

    SurfaceHolder holder;

    /**
     * 存放的所以弹幕的子项
     */
    private List<BulletItem> bullets=new ArrayList<>();
    private Canvas canvas;

    private Random mRandom=new Random();

    public BulletScreenView(Context context) {
        super(context);
    }

    public BulletScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder=getHolder();
        holder.addCallback(this);

        //背景透明处理
        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSPARENT);
    }

    public BulletScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        /**
         * 开启线程 开始运行
         */
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        flag=false;
    }

    @Override
    public void run() {
        while (flag){
            if(!drawBullets()){continue;}
        }
    }

    int height=0;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height=getMeasuredHeight();
    }

    /**
     * 添加弹幕子项
     * @param _item
     */
    public void addItem(BulletItem _item){
        if (_item==null){
            throw new NullPointerException("_item is null...");
        }

        if (_item.getPosX()==0){
            _item.setPosX(getWidth());
        }
        if (_item.getPosY()==0){
            Log.d("123","height -> "+height+" => "+getMeasuredHeight());
//            if (height<0){
////                Log.d("123","height 000000000000000000");
//            }
            if (height>0){
                int i = mRandom.nextInt(height);
                _item.setPosY(i);
            }


        }

        bullets.add(_item);
    }

    /**
     * 绘制弹幕的所有子项
     */
    private boolean drawBullets() {
        if (bullets==null||bullets.size()==0){
            return false;
        }

        Paint mPaint;

        try{
            /**
             * 通过SuurfaceHolder获取画布
             */
            canvas = holder.lockCanvas();
            //清画布
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            for (int i=0;i<bullets.size();i++){
                BulletItem item=bullets.get(i);
                if (item.getPaint()==null){
                    mPaint=new Paint();
                    mPaint.setTextSize(item.getTextSize());
                    mPaint.setColor(item.getTextColor());

                    item.setPaint(mPaint);
                }else{
                    mPaint=item.getPaint();
                }

                canvas.drawText(item.getContent(),item.getPosX(),item.getPosY(),mPaint);

                /**
                 * 考虑两种情况
                 * 第一种情况：弹幕子项已经移动出了屏幕  从上面的缓存list中将其移除掉
                 * 第二种情况：在屏幕内显示或者是在右侧边缘等待进入屏幕 控制x坐标加速度进行处理
                 */
                if (item.getPosX()<-getWidth()){
                    bullets.remove(item);
                }
                else{
                    item.setPosX(item.getPosX()-item.getMoveSpeed());
                }

            }

        }finally {
            /**
             * 解锁当前画布并将绘制内容提交进行显示
             */
            holder.unlockCanvasAndPost(canvas);
        }

        return true;
    }
}

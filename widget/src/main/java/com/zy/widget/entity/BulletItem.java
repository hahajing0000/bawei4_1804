package com.zy.widget.entity;

import android.graphics.Paint;

/**
 * @author:zhangyue
 * @date:2020/9/25
 */
public class BulletItem {
    /**
     * 文本内容
     */
    private String content;
    /**
     * 文本颜色
     */
    private int textColor;
    /**
     * 文本大小
     */
    private int textSize;
    /**
     * 移动速度
     */
    private int moveSpeed;
    /**
     * 画笔用于缓存 提升性能
     */
    private Paint paint;

    private float posX;
    private float posY;

    /**
     * 构造
     * @param content 文本内容
     * @param textColor 文本颜色
     * @param textSize 文本大小
     * @param moveSpeed 移动速度
     * @param paint 画笔资源
     * @param posX X坐标
     * @param posY Y坐标
     */

    public BulletItem(String content, int textColor, int textSize, int moveSpeed, Paint paint, float posX, float posY) {
        this.content = content;
        this.textColor = textColor;
        this.textSize = textSize;
        this.moveSpeed = moveSpeed;
        this.paint = paint;
        this.posX = posX;
        this.posY = posY;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
}

package com.zy.widget;

/**
 * @author:zhangyue
 * @date:2020/9/22
 */
public class ZPoint {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ZPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "ZPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
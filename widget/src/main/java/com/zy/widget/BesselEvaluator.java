package com.zy.widget;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * @author:zhangyue
 * @date:2020/9/22
 */
public class BesselEvaluator implements TypeEvaluator<ZPoint> {
    private ZPoint point1;
    private ZPoint point2;

    public BesselEvaluator(ZPoint _point1, ZPoint _point2) {
        this.point1 = _point1;
        this.point2 = _point2;
    }

    @Override
    public ZPoint evaluate(float t, ZPoint pStart, ZPoint pEnd) {

        Log.i("123","t -> "+t+" start -> "+pStart.toString()+" end -> "+pEnd.toString());

        ZPoint newPoint = new ZPoint(0,0);
        /**
         * 三阶贝塞尔公式
         *
         * B(t)=(1 - t)^3 P0
         *     + 3 t (1 - t)^2 P1
         *     + 3 t^2 (1 - t) P2
         *     + t^3 P3
         */
        newPoint.setX(pStart.getX() * (1 - t) * (1 - t) * (1 - t)
                + 3 * point1.getX() * t * (1 - t) * (1 - t)
                + 3 * point2.getX() * t * t * (1 - t)
                + pEnd.getX() * t * t * t);

        /**
         * 三阶贝塞尔公式
         *
         * B(t)=(1 - t)^3 P0
         *     + 3 t (1 - t)^2 P1
         *     + 3 t^2 (1 - t) P2
         *     + t^3 P3
         */
        newPoint.setY(pStart.getY() * (1 - t) * (1 - t) * (1 - t)
                + 3 * point1.getY() * t * (1 - t) * (1 - t)
                + 3 * point2.getY() * t * t * (1 - t)
                + pEnd.getY() * t * t * t);
        return newPoint;
    }
}

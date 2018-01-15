package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Demo
 * 使用一个 Path 来绘制虚线，虚线可以是Path绘制的形状，比如圆、三角形...
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class PathDashPathEffectDemo extends View {
    private Paint mPaint;
    private Path mPath;
    private Path shape;

    public PathDashPathEffectDemo(Context context) {
        this(context, null);
    }

    public PathDashPathEffectDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathDashPathEffectDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
        shape = new Path();
        shape.addCircle(10, 10, 10, Path.Direction.CW);
        //使用圆来绘制虚线，shape是圆的path， 1是偏移量
        PathEffect pathEffect = new PathDashPathEffect(shape, 20, 1, PathDashPathEffect.Style.TRANSLATE);
        mPaint.setPathEffect(pathEffect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.lineTo(150, 150);
        mPath.lineTo(180, 5);
        mPath.lineTo(350, 110);
        mPath.lineTo(500, 20);
        mPath.lineTo(750, 520);
        canvas.drawPath(mPath, mPaint);
    }
}

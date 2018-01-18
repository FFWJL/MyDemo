package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆心在坐标a，b，圆弧上任意点坐标
 * x = a + r * cos(angle * π /180) 
 * y = b + r * sin(angle * π /180) 
 *
 * @author Wjl.
 * @date 2018\1\17 0017
 */

public class CircleAnimDemo extends View {
    private Paint mCirclePaint;
    private Paint mDotPaint;
    private RectF mRectF;
    /**
     * 圆点的半径
     */
    private static final int DOT_RADIUS = 20;
    /**
     * 大圆的半径
     */
    private float radius;
    /**
     * 角度
     **/
    private float angle;

    public void setAngle(float angle) {
        this.angle = angle;
        invalidate();
    }

    public CircleAnimDemo(Context context) {
        this(context, null);
    }

    public CircleAnimDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleAnimDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setColor(Color.RED);
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(3);
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radius = getMeasuredWidth() * 0.35f;
        mRectF.set(getMeasuredWidth() * 0.15f, getMeasuredHeight() / 2 - radius, getMeasuredWidth() / 2 + radius, getMeasuredHeight() / 2 + radius);
        drawCircle(canvas);
        drawDot(canvas);
    }

    private void drawDot(Canvas canvas) {
        //当前位置与X轴的夹角
        float dotAngle = angle - 90;
        float centerX = getMeasuredWidth() / 2;
        float centerY = getMeasuredHeight() / 2;
        canvas.drawCircle(centerX + radius * (float) Math.cos(dotAngle * Math.PI / 180), centerY + radius * (float) Math.sin(dotAngle * Math.PI / 180), DOT_RADIUS, mDotPaint);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawArc(mRectF, -90, angle, false, mCirclePaint);
    }
}

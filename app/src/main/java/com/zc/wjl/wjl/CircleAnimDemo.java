package com.zc.wjl.wjl;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆心在坐标位a，b，圆弧上任意点坐标(a+R*cosα,b+R*sinα)
 *
 * @author Wjl.
 * @date 2018\1\17 0017
 */

public class CircleAnimDemo extends View {
    private Paint mPaint;
    private RectF mRectF;
    /**
     * 圆半径
     */
    private float radius;
    /**
     * 角度
     */
    private float angle;
    private Paint mDotPaint;

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
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setColor(Color.RED);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radius = getMeasuredWidth() * 0.35f;
        mRectF.set(getMeasuredWidth() * 0.15f, getMeasuredHeight() / 2 - radius, getMeasuredWidth() / 2 + radius, getMeasuredHeight() / 2 + radius);
        if (0 == angle) {
            drawCircle(canvas);
            startAnim();
        } else {
            drawCircle(canvas);
        }
        drawDot(canvas);
    }

    private void drawDot(Canvas canvas) {
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2 - radius, 30, mDotPaint);
    }

    private void startAnim() {
        ObjectAnimator a = ObjectAnimator.ofFloat(this, "angle", 0, 360);
        a.setDuration(3000);
        a.start();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawArc(mRectF, 0, angle, false, mPaint);
    }
}

package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * @author Wjl.
 * @date 2018\1\18 0018
 */

public class SparkView extends View {
    private Paint mPaint;
    /**
     * 半径
     */
    private float radius;
    /**
     * 圆心
     */
    private PointF mPointF;
    public float value;

    public void setPointF(PointF pointF) {
        mPointF = pointF;
        invalidate();
    }

    public SparkView(Context context) {
        this(context, null);
    }

    public SparkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SparkView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPointF = new PointF();
        radius = ScreenUtil.dp2px(2.5f, context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSpark(canvas);
    }

    private void drawSpark(Canvas canvas) {
        float x, y;
        float nextFloat = (float) (Math.random() * 100);
        value = nextFloat;
        if (((int) nextFloat) % 2 == 0) {
            x = mPointF.x + nextFloat;
            y = mPointF.y + nextFloat;
        } else {
            x = mPointF.x - nextFloat;
            y = mPointF.y - nextFloat;
        }
        canvas.drawCircle(x, y, radius, mPaint);
    }
}

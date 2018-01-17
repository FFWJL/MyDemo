package com.zc.wjl.wjl;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Wjl.
 * @date 2018\1\17 0017
 */

public class ObjectAnimatorDemo extends View {
    private Paint mPaint;
    private float progress;

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public ObjectAnimatorDemo(Context context) {
        this(context, null);
    }

    public ObjectAnimatorDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ObjectAnimatorDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (progress == 0) {
            progress = 10;
            drawCircle(canvas);
            startAnim();
        } else {
            drawCircle(canvas);
        }
    }

    private void startAnim() {
        ObjectAnimator a = ObjectAnimator.ofFloat(this, "progress", 10, getMeasuredWidth() * 0.4f);
        a.setDuration(2000);
        a.start();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, progress, mPaint);
    }
}
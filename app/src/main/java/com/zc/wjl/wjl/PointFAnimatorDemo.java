package com.zc.wjl.wjl;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Wjl.
 * @date 2018\1\17 0017
 */

public class PointFAnimatorDemo extends View {
    private Paint mPaint;
    private PointF pos;

    public PointF getPos() {
        return pos;
    }

    public void setPos(PointF pos) {
        this.pos = pos;
        invalidate();
    }

    public PointFAnimatorDemo(Context context) {
        this(context, null);
    }

    public PointFAnimatorDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointFAnimatorDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pos == null) {
            pos = new PointF(50, 50);
            drawCircle(canvas);
            startAnim();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(pos.x, pos.y, 25, mPaint);
    }

    private void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "pos", new PointFEvaluator(), new PointF(50, 50), new PointF(400, 400));
        animator.setDuration(2000);
        animator.start();
    }

    class PointFEvaluator implements TypeEvaluator<PointF> {
        PointF mPointF = new PointF();

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            float x = startValue.x + fraction * (endValue.x - startValue.x);
            float y = startValue.y + fraction * (endValue.y - startValue.y);
            mPointF.set(x, y);
            return mPointF;
        }
    }
}

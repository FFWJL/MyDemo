package com.zc.wjl.wjl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
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
    /**
     * 新的小圆半径
     */
    private float newRadius;
    /**
     * 新的小圆的角度属性
     */
    private float newAngle;
    private PointF newPointF;
    private ObjectAnimator oa;

    public void setNewPointF(PointF newPointF) {
        this.newPointF = newPointF;
        invalidate();
    }

    public void setPointF(PointF pointF) {
        mPointF = pointF;
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
        newPointF = new PointF();
        radius = ScreenUtil.dp2px(0.5f, context);
        newRadius = ScreenUtil.dp2px(20f, context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSpark(canvas);
    }

    private void drawSpark(Canvas canvas) {
        float x, y;
//        int typeFloat = (int) (Math.random() * 100);
//        int nextFloatX = (int) (Math.random() * 100);
//        int nextFloatY = (int) (Math.random() * 100);
//        if (typeFloat < 25) {
//        } else if (typeFloat >= 25 && typeFloat < 50) {
//            nextFloatX = -nextFloatX;
//        } else if (typeFloat >= 50 && typeFloat < 75) {
//            nextFloatY = -nextFloatY;
//        } else {
//            nextFloatX = -nextFloatX;
//            nextFloatY = -nextFloatY;
//        }
//        x = mPointF.x + nextFloatX;
//        y = mPointF.y + nextFloatY;
        x = mPointF.x;
        y = mPointF.y;
        //随机角度
        newAngle = (float) (Math.random() * 361);
        //计算出新圆上的位置,即动画最终位置
        float endX = x + newRadius * (float) Math.cos(newAngle * Math.PI / 180);
        float endY = y + newRadius * (float) Math.sin(newAngle * Math.PI / 180);
        canvas.drawCircle(newPointF.x, newPointF.y, radius, mPaint);
        Log.d("noqnvosfa", "x=" + x + "----y=" + y + "----endX=" + endX + "----endY=" + endY);
        float resX = (float) (Math.random() * endX);
        float resY = (float) (Math.random() * endY);
        startAnim(x, y, resX, resY);
    }

    private void startAnim(float x, float y, float endX, float endY) {
        Log.d("noqnvosfa", "startAnim");
        oa = ObjectAnimator.ofObject(this, "newPointF", new PointFEvaluator(), new PointF(x, y), new PointF(endX, endY));
        oa.setDuration(3000);
        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                oa.start();
            }
        });
    }

    public ObjectAnimator anim() {
        return oa;
    }

    private class PointFEvaluator implements TypeEvaluator<PointF> {
        PointF pointF = new PointF();

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            float x = startValue.x + fraction * (endValue.x - startValue.x);
            float y = startValue.y + fraction * (endValue.y - startValue.y);
            pointF.set(x, y);
            return pointF;
        }
    }
}

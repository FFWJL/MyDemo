package com.zc.wjl.wjl;

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
import android.view.animation.AccelerateInterpolator;


/**
 * 随机颜色 全屏分布 最后收缩 全屏之后随机移动一些距离 类似星空效果
 * 能不能在SurfaceView中去做
 *
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
    private ObjectAnimator[] mAnimators = new ObjectAnimator[300];

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
        newRadius = ScreenUtil.dp2px(205f, context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 300; i++) {
            drawSpark(canvas, i);
        }
    }

    private void drawSpark(Canvas canvas, int i) {
        mPaint.setColor(randomColor());
        float x, y;
        x = mPointF.x;
        y = mPointF.y;
        //随机角度
        newAngle = (float) (Math.random() * 361);
        //计算出新圆上的位置,即动画最终位置
        float endX = x + newRadius * (float) Math.cos(newAngle * Math.PI / 180);
        float endY = y + newRadius * (float) Math.sin(newAngle * Math.PI / 180);
        canvas.drawCircle(newPointF.x, newPointF.y, radius, mPaint);
        float deltaX = endX - x;
        float deltaY = endY - y;
        float a = x + (float) (Math.random() * deltaX);
        float b = y + (float) (Math.random() * deltaY);
        setAnim(x, y, a, b, i);
    }

    private void setAnim(float x, float y, float endX, float endY, int i) {
        Log.d("animadhwqf", "startAnim: " + i + "endx====" + endX + "endy====" + endY);
        mAnimators[i] = ObjectAnimator.ofObject(this, "newPointF", new PointFEvaluator(), new PointF(x, y), new PointF(endX, endY), new PointF(x, y));
//      ObjectAnimator  oa = ObjectAnimator.ofObject(this, "newPointF", new PointFEvaluator(), new PointF(x, y), new PointF(endX, endY), new PointF(x, y));
        mAnimators[i].setDuration(3500);
        mAnimators[i].setInterpolator(new AccelerateInterpolator());
    }

    public void startAnim() {
        if (mAnimators.length == 0) {
            throw new NullPointerException("*****************动画集合为空***************");
        }
        for (ObjectAnimator animator : mAnimators) {
//            animator.start();
            Log.d("animadhfdsawqf", "startAnim: ");
        }
    }

//    public ObjectAnimator anim() {
//        return oa;
//    }

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

    private int randomColor() {
        return Color.argb(255, (int) ((Math.random() + 0.01) * 256), (int) ((Math.random() + 0.01) * 256), (int) ((Math.random() + 0.01) * 256));
    }
}

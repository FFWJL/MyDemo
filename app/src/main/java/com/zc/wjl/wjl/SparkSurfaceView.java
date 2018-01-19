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
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.animation.AccelerateInterpolator;


/**
 * @author Wjl.
 * @date 2018\1\19 0019
 */

public class SparkSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private boolean isRunning;
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
    private Context mContext;

    public void setNewPointF(PointF newPointF) {
        this.newPointF = newPointF;
        invalidate();
    }

    public void setPointF(PointF pointF) {
        mPointF = pointF;
    }

    public SparkSurfaceView(Context context) {
        this(context, null);
    }

    public SparkSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SparkSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        //获取SurfaceHolder实例
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPointF = new PointF();
        newPointF = new PointF();
        radius = ScreenUtil.dp2px(0.5f, context);
        newRadius = ScreenUtil.dp2px(205f, context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        new MyThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (isRunning) {
                try {
                    sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doSomething();
            }
        }
    }

    private void doSomething() {
        Canvas canvas = mSurfaceHolder.lockCanvas();
        try {
            drawView(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != canvas) {
                //提交视图
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    private void drawView(Canvas canvas) {
        mPaint.setColor(randomColor());
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
        float deltaX = endX - x;
        float deltaY = endY - y;
//        //求随机半径
//        float r = (float) Math.sqrt(((0.01f + Math.random()) * deltaX) * ((0.01f + Math.random()) * deltaX)
//                + ((0.01f + Math.random()) * deltaY) * ((0.01f + Math.random()) * deltaY));
//        float a = x + r * (float) Math.cos(newAngle * Math.PI / 180);
//        float b = y + r * (float) Math.sin(newAngle * Math.PI / 180);
        float a = x + (float) (Math.random() * deltaX);
        float b = y + (float) (Math.random() * deltaY);
        startAnim(x, y, a, b);
    }

    private void startAnim(float x, float y, float endX, float endY) {
        Log.d("noqnasgsdfdvosfa", "x=" + x + "----y=" + y + "----endX=" + endX + "----endY=" + endY);
        oa = ObjectAnimator.ofObject(this, "newPointF", new PointFEvaluator(), new PointF(x, y), new PointF(endX, endY), new PointF(x, y));
        oa.setDuration(3500);
        oa.setInterpolator(new AccelerateInterpolator());
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

    private int randomColor() {
//        alpha = Math.min(Math.max(1, alpha), 255);
        return Color.argb(255, (int) ((Math.random() + 0.01) * 256), (int) ((Math.random() + 0.01) * 256), (int) ((Math.random() + 0.01) * 256));
    }
}

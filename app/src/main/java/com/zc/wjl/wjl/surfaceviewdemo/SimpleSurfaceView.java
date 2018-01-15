package com.zc.wjl.wjl.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * @author Wjl.
 * @date 2018\1\12 0012
 */

public class SimpleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private boolean isRunning;
    private Paint mPaint;
    private Context mContext;

    public SimpleSurfaceView(Context context) {
        this(context, null);
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        //获取SurfaceHolder实例
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
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

    /**
     * 半径
     */
    private float radius = 1;

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
        radius += 3;
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, mPaint);
    }
}

package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Wjl.
 * @date 2018\1\17 0017
 */

public class CameraPractice extends View {
    private Paint mPaint;
    private Camera mCamera;
    private Bitmap mBitmap;

    public CameraPractice(Context context) {
        this(context, null);
    }

    public CameraPractice(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraPractice(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCamera = new Camera();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flip_board);
        //镜头距离放远
        mCamera.setLocation(0, 0, -getResources().getDisplayMetrics().density * 6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Bitmap的宽高
        int bitmapWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();
        //中心点
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        //Bitmap绘制的左上角点,保证图片绘制在屏幕正中间
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;

        //右半部分
        canvas.save();
        mCamera.save();
        //然后将图片移动回原中心点
        canvas.translate(centerX, centerY);
        mCamera.rotateX(40);
        mCamera.applyToCanvas(canvas);
        //坐标已经移动，裁剪只显示右半部分
        canvas.clipRect(-centerX, -centerY, centerX, 0);
        mCamera.restore();
        //先把图片中心点移动到坐标原点，执行旋转操作
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(mBitmap, x, y, mPaint);
        canvas.restore();

 //左半部分
        canvas.save();
                mCamera.save();
                //然后将图片移动回原中心点
                canvas.translate(centerX, centerY);
//        mCamera.rotateX(30);
//        mCamera.applyToCanvas(canvas);
                //坐标已经移动，裁剪只显示右半部分
                canvas.clipRect(-centerX, 0, centerX, centerY);
                mCamera.restore();
                //先把图片中心点移动到坐标原点，执行旋转操作
                canvas.translate(-centerX, -centerY);
                canvas.drawBitmap(mBitmap, x, y, mPaint);
                canvas.restore();
                }
                }

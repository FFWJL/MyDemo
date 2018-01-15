package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author Wjl.
 * @date 2018\1\15 0015
 */

public class Camera3DDemo extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Camera mCamera;
    private Matrix mMatrix;

    public Camera3DDemo(Context context) {
        this(context, null);
    }

    public Camera3DDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Camera3DDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCamera = new Camera();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mMatrix.reset();
        //Camera 和 Canvas 一样也需要保存和恢复状态才能正常绘制，不然在界面刷新之后绘制就会出现问题
        mCamera.save();
        //和正常坐标系正负一样，左负有正，下负上正，Z轴一样，负的表示向屏幕外移动，靠近镜头图片放大
        mCamera.translate(100, -300, -200);
        mCamera.rotateY(30);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);

        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        canvas.restore();
    }
}

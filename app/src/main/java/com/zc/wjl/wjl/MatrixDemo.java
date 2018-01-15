package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Matrix变换Demo
 *
 * @author Wjl.
 * @date 2018\1\15 0015
 */

public class MatrixDemo extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private Matrix mMatrix;
//    private float[] , pointDst;

    public MatrixDemo(Context context) {
        this(context, null);
    }

    public MatrixDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //left，top，right，top，left，bottom，right，bottom  左上，右上，左下，右下
        float[] pointSrc = {0, 0, getMeasuredWidth(), 0, 0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight()};
        float[] pointDst = {-80, 80, getMeasuredWidth() + 180, 180, 0, getMeasuredHeight(), getMeasuredWidth() + 180, getMeasuredHeight() + 180};
        mMatrix.reset();
//        mMatrix.postTranslate(400, 200);
//        mMatrix.postRotate(45);
        mMatrix.setPolyToPoly(pointSrc, 0, pointDst, 0, 4);
        canvas.translate(400, 200);
        canvas.concat(mMatrix);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();
    }
}

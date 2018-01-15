package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 错切Demo
 *
 * @author Wjl.
 * @date 2018\1\15 0015
 */

public class CanvasSkewDemo extends View {
    private Paint mPaint;
    private Bitmap mBitmap;

    public CanvasSkewDemo(Context context) {
        this(context, null);
    }

    public CanvasSkewDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasSkewDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.skew(0.5f, 0.5f);
        canvas.drawBitmap(mBitmap, 50, 50, mPaint);
        canvas.restore();
    }
}

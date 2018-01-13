package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Wjl.
 * @date 2018\1\12 0012
 */

public class WtfView extends View {
    private Paint mPaint;
    private Path mPath;
    private RectF mRect;

    public WtfView(Context context) {
        this(context, null);
    }

    public WtfView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WtfView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeWidth(30);
        mPath = new Path();
        mRect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(new LinearGradient(0, getMeasuredHeight() * 0.7f, getMeasuredWidth(), getMeasuredHeight() * 0.7f, Color.parseColor("#FF0000"), Color.parseColor("#00FF00"), Shader.TileMode.CLAMP));
//        mPath.moveTo(15,15);
//        mPath.lineTo(getMeasuredWidth() - 15, getMeasuredHeight() - 15);
//        canvas.drawPath(mPath, mPaint);
        mRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawArc(mRect, 30, 120, true, mPaint);
    }
}

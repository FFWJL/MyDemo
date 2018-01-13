package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 组合效果类的 PathEffect 。它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class ComposePathEffectDemo extends View {
    private Paint mPaint;
    private Path mPath;

    public ComposePathEffectDemo(Context context) {
        this(context, null);
    }

    public ComposePathEffectDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComposePathEffectDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPath = new Path();
        PathEffect pathEffect1 = new DashPathEffect(new float[]{20, 5, 10, 5}, 1);
        PathEffect pathEffect2 = new CornerPathEffect(25);
        PathEffect pathEffect = new ComposePathEffect(pathEffect1, pathEffect2);
        mPaint.setPathEffect(pathEffect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.lineTo(150, 150);
        mPath.lineTo(180, 5);
        mPath.lineTo(350, 110);
        mPath.lineTo(500, 20);
        mPath.lineTo(750, 520);
        canvas.drawPath(mPath, mPaint);
    }
}

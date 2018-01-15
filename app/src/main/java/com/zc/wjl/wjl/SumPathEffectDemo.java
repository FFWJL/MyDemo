package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Demo
 * 对不同的Path使用，组合效果类的 PathEffect 。分别按照两种 PathEffect 分别对目标进行绘制
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class SumPathEffectDemo extends View {
    private Paint mPaint;
    private Path mPath;

    public SumPathEffectDemo(Context context) {
        this(context, null);
    }

    public SumPathEffectDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SumPathEffectDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPath = new Path();
        PathEffect pathEffect1 = new DiscretePathEffect(20, 15);
        PathEffect pathEffect2 = new DashPathEffect(new float[]{20, 5, 10, 5}, 1);
        PathEffect sumPathEffect = new SumPathEffect(pathEffect1, pathEffect2);
        mPaint.setPathEffect(sumPathEffect);
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

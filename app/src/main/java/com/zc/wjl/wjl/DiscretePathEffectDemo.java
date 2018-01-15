package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 把线条进行随机偏离，让轮廓变得乱七八糟Demo
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class DiscretePathEffectDemo extends View {
    private Paint mPaint;
    private Path mPath;

    public DiscretePathEffectDemo(Context context) {
        this(context, null);
    }

    public DiscretePathEffectDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiscretePathEffectDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
        //参数一segmentLength 是用来拼接的每个线段的长度， 参数二deviation 是偏离量
        PathEffect pathEffect = new DiscretePathEffect(20, 5);
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

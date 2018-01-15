package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用虚线来绘制线条Demo
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class DashPathEffectDemo extends View {
    private Paint mPaint;
    private Path mPath;

    public DashPathEffectDemo(Context context) {
        this(context, null);
    }

    public DashPathEffectDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashPathEffectDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
        // 代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
        // 第二个参数 phase 是虚线的偏移量，可以是用偏移量来实现动画 参考http://blog.csdn.net/u014697083/article/details/70145173
        PathEffect pathEffect = new DashPathEffect(new float[]{20, 5, 10, 5}, 1);
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

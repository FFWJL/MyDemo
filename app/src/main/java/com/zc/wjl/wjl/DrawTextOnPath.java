package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 沿着Path绘制文字Demo
 *
 * @author Wjl.
 * @date 2018\1\15 0015
 */

public class DrawTextOnPath extends View {
    private Paint mPaint;
    private Path mPath;

    public DrawTextOnPath(Context context) {
        this(context, null);
    }

    public DrawTextOnPath(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextOnPath(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(35);
        //把拐角设置成圆角，避免文字难看
        PathEffect pathEffect = new CornerPathEffect(40);
        mPaint.setPathEffect(pathEffect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.lineTo(150, 250);
        mPath.lineTo(180, 100);
        mPath.lineTo(350, 410);
        mPath.lineTo(500, 20);
        mPath.lineTo(750, 520);
        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(Color.RED);
        //hOffset 和 vOffset。它们是文字相对于 Path 的水平偏移量和竖直偏移量，利用它们可以调整文字的位置
        canvas.drawTextOnPath("Hello World!抠门兔网络科技有限公司", mPath, 20, 30, mPaint);
    }
}

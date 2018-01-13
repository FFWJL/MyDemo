package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**获取文字的实际 Path ，指的就是 drawPath() 的绘制内容的轮廓，要算上线条宽度和设置的 PathEffect
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class GetTextPathDemo extends View {
    private Paint mPaint;
    private Path mPath;
    public GetTextPathDemo(Context context) {
        this(context, null);
    }

    public GetTextPathDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GetTextPathDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(100);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("王佳乐", 50, 100, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.getTextPath("王佳乐", 0, "王佳乐".length(), 50, 100, mPath);
        canvas.translate(50, 300);
        canvas.drawPath(mPath, mPaint);
    }
}

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

/**把Path的拐角变成圆角Demo
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class CornerPathEffectDemo extends View {
    private Paint mPaint;
    private Path mPath;

    public CornerPathEffectDemo(Context context) {
        this(context, null);
    }

    public CornerPathEffectDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerPathEffectDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
        mPaint.setStrokeWidth(6);
        PathEffect pathEffect = new CornerPathEffect(30);
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

package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**Demo
 * 获取实际 Path ，指的就是 drawPath() 的绘制内容的轮廓，要算上线条宽度和设置的 PathEffect
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class GetFillPathDemo extends View {
    private Paint mPaint, mDstPaint;
    private Path mPath;
    private Path mDstPath;

    public GetFillPathDemo(Context context) {
        this(context, null);
    }

    public GetFillPathDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GetFillPathDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
        Path shape = new Path();
        shape.addCircle(10, 10, 10, Path.Direction.CW);
        //使用圆来绘制虚线，shape是圆的path， 1是偏移量
        PathEffect pathEffect = new PathDashPathEffect(shape, 20, 1, PathDashPathEffect.Style.TRANSLATE);
        mPaint.setPathEffect(pathEffect);
        mDstPath = new Path();
        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(Color.RED);
        mDstPaint.setStyle(Paint.Style.STROKE);
        mDstPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.lineTo(150, 150);
        mPath.lineTo(180, 5);
        mPath.lineTo(350, 110);
        mPath.lineTo(500, 20);
        mPath.lineTo(750, 520);
        //第一个Path是原path，第二个是目标Path，需要将实际的Path保存到目标Path中
        mPaint.getFillPath(mPath, mDstPath);
        canvas.drawPath(mDstPath, mDstPaint);
    }
}

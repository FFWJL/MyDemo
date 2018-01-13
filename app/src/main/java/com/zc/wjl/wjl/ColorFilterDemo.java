package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 模拟简单的光照效果
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class ColorFilterDemo extends View {
    private Paint mPaint;
    private Bitmap bitmap;

    public ColorFilterDemo(Context context) {
        this(context, null);
    }

    public ColorFilterDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        ColorFilter colorFilter = new LightingColorFilter(0x00ffff, 0x000000);//去掉红色
//        ColorFilter colorFilter = new LightingColorFilter(0xffffff, 0x000000);//去掉绿色
//        ColorFilter colorFilter = new LightingColorFilter(0xffffff, 0x550000);//加强红色
        ColorFilter colorFilter = new LightingColorFilter(0xffffff, 0x006600);//加强绿色
        mPaint.setColorFilter(colorFilter);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }
}
